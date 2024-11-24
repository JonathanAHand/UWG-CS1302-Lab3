package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.viewmodel.TaskViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

	@FXML
	private ListView<String> taskListView;

	private TaskViewModel vm;

	@FXML
	void initialize() {
		this.vm = new TaskViewModel();

		this.taskListView.setItems(this.vm.getTaskTitles());
	}

}
