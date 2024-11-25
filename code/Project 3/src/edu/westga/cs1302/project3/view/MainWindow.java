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

	private TaskViewModel vm;

	@FXML
	void initialize() {
		this.vm = new TaskViewModel();
		this.taskListView.setItems(this.vm.getTaskTitles());
		this.loadTasksMenuItem.setOnAction(event -> this.handleLoadTasks());
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

}
