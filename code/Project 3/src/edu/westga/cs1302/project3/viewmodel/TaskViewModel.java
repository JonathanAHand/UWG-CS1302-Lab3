package edu.westga.cs1302.project3.viewmodel;

import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskManagerFileUtility;

import java.io.File;
import java.io.IOException;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import edu.westga.cs1302.project3.model.Task;
import javafx.collections.FXCollections;

/**
 * ViewModel for managing tasks and providing data bindings to the UI.
 *
 * @author jhand1
 * @version 1.0
 * 
 */
public class TaskViewModel {

	private TaskManager taskManager;
	private final ListProperty<String> taskTitles;

	/**
	 * Initializes the TaskViewModel with default tasks provided.
	 */
	public TaskViewModel() {
		this.taskManager = new TaskManager();

		this.taskManager.addTask(new Task("Chores", "Wash car."));
		this.taskManager.addTask(new Task("Adulting", "Renew insurance."));
		this.taskManager.addTask(new Task("Scheming", "Take over world."));

		this.taskTitles = new SimpleListProperty<>(FXCollections.observableArrayList());
		for (Task task : this.taskManager.getTasks()) {
			this.taskTitles.add(task.getTitle());
		}
	}

	/**
	 * Gets the task titles property for binding to the UI.
	 * 
	 * @return the task titles property
	 */
	public ListProperty<String> taskTitlesProperty() {
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

		// debug
		System.out.println("Stored tasks in TaskManager:");
		for (Task task : this.taskManager.getTasks()) {
			System.out.println(task.getTitle() + ": " + task.getDescription());
		}
	}

	/**
	 * Saves the current set of tasks in the TaskManager to the specified file.
	 * 
	 * @precondition file != null && taskManager != null
	 * @postcondition The specified file contains the tasks from the TaskManager in
	 *                the format: "title:description", with each task on a new line.
	 * 
	 * @param file the file to save the tasks to
	 * @throws IllegalArgumentException if the file is null or the TaskManager has
	 *                                  no tasks to save
	 * @throws IOException              if an I/O error occurs while writing to the
	 *                                  file
	 */
	public void saveVMTasks(File file) throws IOException {
		if (file == null) {
			throw new IllegalArgumentException("The file cannot be null.");
		}
		System.out.println("Saving tasks to file: " + file.getAbsolutePath());

		TaskManagerFileUtility.saveTasks(this.taskManager, file);
	}

}
