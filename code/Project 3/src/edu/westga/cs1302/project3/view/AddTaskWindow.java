package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.viewmodel.TaskViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Codebehind for the Add Task Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class AddTaskWindow {

	@FXML
	private Button cancelButton;

	@FXML
	private TextField taskTitleField;

	@FXML
	private TextArea taskDescriptionArea;

	@FXML
	private Button addTaskButton;

	private TaskViewModel vm;

	/**
	 * Binds UI controls to the TaskViewModel properties.
	 * 
	 * @param viewModel the TaskViewModel to bind to
	 */
	public void bindToViewModel(TaskViewModel viewModel) {
		this.vm = viewModel;

		this.taskTitleField.textProperty().bindBidirectional(this.vm.addTaskTitleProperty());
		this.taskDescriptionArea.textProperty().bindBidirectional(this.vm.addTaskDescriptionProperty());
		
	}

	/**
	 * Handles the Add Task button action.
	 */
	@FXML
	private void handleAddTask() {
		try {
			this.vm.addTask();
			this.closeWindow();
		} catch (IllegalArgumentException error) {
			this.showErrorPopup("Error Adding Task", error.getMessage());
		}
	}

	/**
	 * Handles the Cancel button click.
	 * 
	 * @precondition none
	 * @postcondition the AddTaskWindow is closed
	 */

	@FXML
	private void handleCancel() {
		this.taskTitleField.clear();
	    this.taskDescriptionArea.clear();
		Stage stage = (Stage) this.cancelButton.getScene().getWindow();
		stage.close();
	}

	/**
	 * Closes the AddTaskWindow.
	 */
	private void closeWindow() {
		this.taskTitleField.clear();
	    this.taskDescriptionArea.clear();
		Stage stage = (Stage) this.addTaskButton.getScene().getWindow();
		stage.close();
	}

	/**
	 * Displays an error popup.
	 * 
	 * @param title   the title of the error popup
	 * @param message the error message
	 */
	private void showErrorPopup(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
