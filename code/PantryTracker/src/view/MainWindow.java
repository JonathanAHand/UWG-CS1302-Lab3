package view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainWindow {

	@FXML
	private TextField foodNameTextField;

	@FXML
	private ComboBox<String> foodTypeComboBox;

	@FXML
	private ListView<String> foodListView;

	@FXML
	public void handleAddFood() {
		String foodName = foodNameTextField.getText();
		String foodType = foodTypeComboBox.getValue();
		if (foodName != null && !foodName.isEmpty() && foodType != null) {
			String foodItem = foodName + " (" + foodType + ")";
			foodListView.getItems().add(foodItem);
			foodNameTextField.clear();
		}
	}
}
