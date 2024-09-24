package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Code behind for the MainWindow of the application.
 * 
 * @author jhand1
 * @version 1.0
 */

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Food;
import utility.PantryUtility;

public class MainWindow {

	@FXML
	private TextField foodNameTextField;

	@FXML
	private ComboBox<String> foodTypeComboBox;

	@FXML
	private ListView<Food> foodListView;

	@FXML
	private TextField setQuantityTextField;

	@FXML
	private void initialize() {
		foodTypeComboBox.setItems(
				FXCollections.observableArrayList("Vegetable", "Meat", "Bread", "Fruit", "Dessert", "Ingredient"));
	}

	/**
	 * Adds a single food item to the pantry list. Retrieves the food name and food
	 * type, checking if the item already exists in the list. Increases the quantity
	 * or adds it as new if not already found. Throws exception if name or type
	 * input fields are invalid due to being empty.
	 */
	@FXML
	public void handleAddFood() {
		try {
			String foodName = foodNameTextField.getText();
			String foodType = foodTypeComboBox.getValue();
			if (foodName != null && !foodName.isEmpty() && foodType != null) {
				Food newFood = new Food(foodName, foodType);

				boolean wasFound = false;
				for (Food food : foodListView.getItems()) {
					if (food.getName().equals(newFood.getName()) && food.getType().equals(newFood.getType())) {
						food.incrementQuantity();
						wasFound = true;
						break;
					}
				}

				if (!wasFound) {
					foodListView.getItems().add(newFood);
				}

				foodListView.refresh();
				foodNameTextField.clear();
			} else {
				throw new IllegalArgumentException("Food name and type must not be empty.");
			}
		} catch (IllegalArgumentException errorObject) {
			showErrorAlert("Invalid Input", errorObject.getMessage());
		} catch (Exception errorGeneral) {
			showErrorAlert("Unexpected Error", "An unexpected error has occurred. Please check inputs.");
		}
	}

	/**
	 * Sets the quantity of the selected food item to the user's input.
	 */
	@FXML
	public void handleSetQuantity() {
		try {
			Food selectedFood = foodListView.getSelectionModel().getSelectedItem();
			int newQuantity = Integer.parseInt(setQuantityTextField.getText());

			if (newQuantity < 0) {
				throw new IllegalArgumentException("Quantity cannot be negative.");
			}

			if (selectedFood != null) {
				selectedFood.setQuantity(newQuantity);
				foodListView.refresh();
				setQuantityTextField.clear();
			} else {
				throw new NullPointerException("No food item selected.");
			}
		} catch (NumberFormatException e) {
			showErrorAlert("Invalid Input", "Please enter a valid number for quantity.");
		} catch (NullPointerException e) {
			showErrorAlert("No Item Selected", "Please select a food item from the list.");
		} catch (IllegalArgumentException e) {
			showErrorAlert("Invalid Quantity", e.getMessage());
		}
	}

	/**
	 * Increases the quantity of the selected food item by 1 unit.
	 */
	@FXML
	public void handleIncrementQuantity() {
		try {
			Food selectedFood = foodListView.getSelectionModel().getSelectedItem();

			if (selectedFood != null) {
				selectedFood.setQuantity(selectedFood.getQuantity() + 1);
				foodListView.refresh();

			} else {
				throw new NullPointerException("No food was selected.");
			}
		} catch (NullPointerException e) {
			showErrorAlert("No Item Selected", "Please select a food item from the list.");
		}
	}

	/**
	 * Decreases the quantity of the selected food item by 1 unit.
	 */
	@FXML
	public void handleDecrementQuantity() {
		try {
			Food selectedFood = foodListView.getSelectionModel().getSelectedItem();

			if (selectedFood != null) {
				if (selectedFood.getQuantity() > 0) {
					selectedFood.setQuantity(selectedFood.getQuantity() - 1);

				} else {
					throw new IllegalStateException("Quantity cannot be less than 0.");
				}

				foodListView.refresh();
			} else {
				throw new NullPointerException("No food item selected.");
			}
		} catch (IllegalStateException e) {
			showErrorAlert("Invalid Action", "Quantity cannot be less than 0.");
		} catch (NullPointerException e) {
			showErrorAlert("No Item Selected", "Please select a food item from the list.");
		}
	}

	/**
	 * Removes a selected food item from the pantry.
	 */
	@FXML
	public void handleRemoveFood() {
		try {
			Food selectedFood = foodListView.getSelectionModel().getSelectedItem();

			if (selectedFood != null) {
				foodListView.getItems().remove(selectedFood);
			} else {
				throw new NullPointerException("No food item was selected.");
			}
		} catch (NullPointerException e) {
			showErrorAlert("No item was selected.", "PLease select a food item for removal.");
		}
	}

	/**
	 * Displays total item count of food items in the Pantry within a pop-up window.
	 */
	public void handleViewItemCount() {
		ObservableList<Food> foodList = foodListView.getItems();
		
		int totalQuantity = PantryUtility.calculateTotalQuantity(foodList);
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Total Pantry Item Count");
		alert.setHeaderText(null);
		alert.setContentText("The total quantity of food items currently in the Pantry is: " + totalQuantity);
		alert.showAndWait();
	}

	/**
	 * Displays an error alert message to the user with given title and message.
	 * 
	 * @param title   the alert title displayed.
	 * @param message the alert message displayed.
	 */
	private void showErrorAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
