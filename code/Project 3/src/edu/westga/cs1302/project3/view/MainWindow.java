package edu.westga.cs1302.project3.view;

import java.io.File;
import java.io.IOException;

import edu.westga.cs1302.project3.Main;
import edu.westga.cs1302.project3.viewmodel.TaskViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

	@FXML
	private Button removeButton;

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

		this.removeButton.setOnAction(event -> this.handleRemoveTask());

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

	@FXML
	private void handleAddTask() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(Main.ADD_TASK_WINDOW));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);

			Stage addTaskStage = new Stage();
			addTaskStage.setTitle(Main.ADD_TASK_WINDOW_TITLE);
			addTaskStage.setScene(scene);
			addTaskStage.initModality(Modality.APPLICATION_MODAL);

			AddTaskWindow addTaskController = loader.getController();
			addTaskController.bindToViewModel(this.vm);

			Stage primaryStage = (Stage) this.taskListView.getScene().getWindow();
			addTaskStage.setX(primaryStage.getX() + primaryStage.getWidth() + 10);
			addTaskStage.setY(primaryStage.getY());
			addTaskStage.showAndWait();

		} catch (IOException error) {
			this.showErrorPopup("Error Opening Add Task Window", "Unable to load the Add Task Window.");
		}
	}

	@FXML
	private void handleRemoveTask() {
		String selectedTask = this.taskListView.getSelectionModel().getSelectedItem();

		if (selectedTask == null) {
			this.showErrorPopup("Error", "No task selected to remove.");
			return;
		}

		try {
			this.vm.removeTask(selectedTask);
		} catch (IllegalArgumentException error) {
			this.showErrorPopup("Error Removing Task", error.getMessage());
		}
	}

}
