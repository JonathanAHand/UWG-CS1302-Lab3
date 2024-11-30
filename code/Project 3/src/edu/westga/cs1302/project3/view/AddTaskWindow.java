package edu.westga.cs1302.project3.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

	/**
	 * Handles the Cancel button click.
	 * 
	 * @precondition none
	 * @postcondition the AddTaskWindow is closed
	 */

	@FXML
	private void handleCancel() {
		Stage stage = (Stage) this.cancelButton.getScene().getWindow();
		stage.close();
	}

}
