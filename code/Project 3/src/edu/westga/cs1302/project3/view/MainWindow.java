package edu.westga.cs1302.project3.view;

import java.io.File;
import java.io.IOException;
import edu.westga.cs1302.project3.viewmodel.TaskViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

	@FXML
	private ListView<String> taskListView;

	@FXML
	private MenuItem loadTasksMenuItem;

	@FXML
	private MenuItem saveTasksMenuItem;

	private TaskViewModel vm;

	@FXML
	void initialize() {
		this.vm = new TaskViewModel();
		this.bindToViewModel();
		this.setupEventHandlers();
	}

	private void bindToViewModel() {
		this.taskListView.itemsProperty().bind(this.vm.taskTitlesProperty());
	}

	private void setupEventHandlers() {
		this.loadTasksMenuItem.setOnAction(event -> this.handleLoadTasks());
		this.saveTasksMenuItem.setOnAction(event -> this.handleSaveTasks());
	}

	private void handleLoadTasks() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Task File");

		File selectedFile = fileChooser.showOpenDialog(null);

		if (selectedFile == null) {
			return;
		}

		try {
			this.vm.loadVMTasks(selectedFile);
		} catch (IllegalArgumentException | IOException error) {
			this.showErrorPopup("Error Loading Tasks", error.getMessage());
		}
	}

	private void showErrorPopup(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@FXML
	private void handleSaveTasks() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Task File");

		File selectedFile = fileChooser.showSaveDialog(null);

		if (selectedFile == null) {
			return;
		}

		try {
			this.vm.saveVMTasks(selectedFile);
		} catch (IllegalArgumentException | IOException error) {
			this.showErrorPopup("Error Saving Tasks", error.getMessage());
		}
	}
}
