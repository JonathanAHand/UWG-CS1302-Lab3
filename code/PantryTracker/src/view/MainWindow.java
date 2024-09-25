package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Food;
import utility.PantryUtility;

/**
 * Code behind for the MainWindow of the application.
 * 
 * @author jhand1
 * @version 1.0
 */
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
		this.foodTypeComboBox.setItems(
				FXCollections.observableArrayList("Vegetable", "Meat", "Bread", "Fruit", "Dessert", "Ingredient"));
	}

	/**
	 * Adds a single food item to the pantry list. Retrieves the food name and food
	 * type, checking if the item already exists in the list. Increases the quantity
	 * or adds it as new if not already found. Throws exception if name or type
	 * input fields are invalid due to being empty.
	 * 
	 * @throws IllegalArgumentException if food name or type is empty
	 * @throws Exception                if any other unexpected errors happen during
	 *                                  execution.
	 */
	@FXML
	public void handleAddFood() throws IllegalArgumentException, Exception {
		try {
			String foodName = this.foodNameTextField.getText();
			String foodType = this.foodTypeComboBox.getValue();
			if (foodName != null && !foodName.isEmpty() && foodType != null) {
				Food newFood = new Food(foodName, foodType);
				Food.addFood(this.foodListView.getItems(), newFood);
				this.foodListView.refresh();
				this.foodNameTextField.clear();
			} else {
				throw new IllegalArgumentException("Food name and type must not be empty.");
			}

		} catch (IllegalArgumentException errorObject) {
			this.showErrorAlert("Invalid Input", errorObject.getMessage());
		}
	}

	/**
	 * Sets the quantity of the selected food item to the user's input.
	 * 
	 * @throws NumberFormatException    if the quantity input is not a valid number.
	 * @throws IllegalArgumentException if the quantity is less than 0.
	 * @throws NullPointerException     if no food item had been selected to handle.
	 */
	@FXML
	public void handleSetQuantity() throws NumberFormatException, IllegalArgumentException, NullPointerException {
		try {
			Food selectedFood = this.foodListView.getSelectionModel().getSelectedItem();
			int newQuantity = Integer.parseInt(this.setQuantityTextField.getText());

			if (selectedFood != null) {
				selectedFood.setQuantity(newQuantity);
				this.foodListView.refresh();
				this.setQuantityTextField.clear();
			} else {
				throw new NullPointerException("No food item selected.");
			}
		} catch (NumberFormatException numberFormatException) {
			this.showErrorAlert("Invalid Input", "Please enter a valid number for quantity.");
		} catch (NullPointerException nullPointerException) {
			this.showErrorAlert("No Item Selected", "Please select a food item from the list.");
		} catch (IllegalArgumentException illegalArgumentException) {
			this.showErrorAlert("Invalid Quantity", illegalArgumentException.getMessage());
		}
	}

	/**
	 * Increases the quantity of the selected food item by 1 unit.
	 * 
	 * @throws NullPointerException if no food item was selected to increment.
	 */
	@FXML
	public void handleIncrementQuantity() throws NullPointerException {
		try {
			Food selectedFood = this.foodListView.getSelectionModel().getSelectedItem();

			if (selectedFood != null) {
				selectedFood.incrementQuantity();
				this.foodListView.refresh();

			} else {
				throw new NullPointerException("No food was selected.");
			}
		} catch (NullPointerException nullPointerException) {
			this.showErrorAlert("No Item Selected", "Please select a food item from the list.");
		}
	}

	/**
	 * Decreases the quantity of the selected food item by 1 unit.
	 * 
	 * @throws IllegalStateException if the quantity is already 0.
	 * @throws NullPointerException  if no food item has been selected to decrement.
	 */
	@FXML
	public void handleDecrementQuantity() throws IllegalStateException, NullPointerException {
		try {
			Food selectedFood = this.foodListView.getSelectionModel().getSelectedItem();

			if (selectedFood != null) {
				selectedFood.decrementQuantity();
				this.foodListView.refresh();

			} else {
				throw new NullPointerException("No food item selected.");
			}

		} catch (NullPointerException nullPointerException) {
			this.showErrorAlert("No Item Selected", "Please select a food item from the list.");
		}
	}

	/**
	 * Removes a selected food item from the pantry.
	 * 
	 * @throws NullPointerException if no food item was selected to remove.
	 */
	@FXML
	public void handleRemoveFood() throws NullPointerException {
		try {
			Food selectedFood = this.foodListView.getSelectionModel().getSelectedItem();

			if (selectedFood != null) {
				this.foodListView.getItems().remove(selectedFood);
			} else {
				throw new NullPointerException("No food item was selected.");
			}
		} catch (NullPointerException nullPointerException) {
			this.showErrorAlert("No item was selected.", "PLease select a food item for removal.");
		}
	}

	/**
	 * Displays total item count of food items in the Pantry within a pop-up window.
	 * 
	 * @throws NullPointerException if no food items are selected to count and view
	 *                              total of.
	 */
	public void handleViewItemCount() throws NullPointerException {
		ObservableList<Food> foodList = this.foodListView.getItems();

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
