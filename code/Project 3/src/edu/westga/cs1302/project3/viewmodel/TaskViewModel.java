package edu.westga.cs1302.project3.viewmodel;

import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskManagerFileUtility;

import java.io.File;
import java.io.IOException;

import edu.westga.cs1302.project3.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * ViewModel for managing tasks and providing data bindings to the UI.
 *
 * @author jhand1
 * @version 1.0
 * 
 */
public class TaskViewModel {

	private TaskManager taskManager;
	private ObservableList<String> taskTitles;

	/**
	 * Initializes the TaskViewModel with default tasks provided.
	 */
	public TaskViewModel() {
		this.taskManager = new TaskManager();

		this.taskManager.addTask(new Task("Chores", "Wash car."));
		this.taskManager.addTask(new Task("Adulting", "Renew insurance."));
		this.taskManager.addTask(new Task("Scheming", "Take over world."));

		this.taskTitles = FXCollections.observableArrayList();
		for (Task task : this.taskManager.getTasks()) {
			this.taskTitles.add(task.getTitle());
		}
	}

	/**
	 * Returns the observable list of task titles for binding with the UI.
	 * 
	 * @return the observable list of task titles
	 */
	public ObservableList<String> getTaskTitles() {
		return this.taskTitles;
	}

	/**
	 * Loads tasks from a specified file using TaskManagerFileUtility.
	 * 
	 * @param file the file containing tasks
	 * @throws IOException              if an I/O error occurs
	 * @throws IllegalArgumentException if the file format is invalid
	 */
	public void loadVMTasks(File file) throws IOException {
		if (file == null || !file.exists()) {
			throw new IllegalArgumentException("The file does not exist or is invalid.");
		}

		TaskManager loadedManager = TaskManagerFileUtility.loadTasks(file);

		this.taskManager = loadedManager;
		this.taskTitles.clear();
		
		for (Task task : this.taskManager.getTasks()) {
			this.taskTitles.add(task.getTitle());
		}
		
		System.out.println("Stored tasks in TaskManager:");
	    for (Task task : this.taskManager.getTasks()) {
	        System.out.println(task.getTitle() + ": " + task.getDescription());
	    }
	}

}
