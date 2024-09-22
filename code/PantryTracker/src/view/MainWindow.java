package view;

/**
 * Code behind for the MainWindow of the application.
 * 
 * @author jhand1
 * @version 1.0
 */
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Food;

public class MainWindow {

	@FXML
	private TextField foodNameTextField;

	@FXML
	private ComboBox<String> foodTypeComboBox;

	@FXML
	private ListView<Food> foodListView;
	
	@FXML
	private void initialize() {
		foodTypeComboBox.getItems().addAll("Vegetable", "Meat", "Bread", "Fruit", "Dessert", "Ingredient");
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
//					if (food.getName().equals(newFood.getName()) && food.getType().equals(newFood.getType())) {
//						food.incrementQuantity();
//						wasFound = true;
//						break;
//					}
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
