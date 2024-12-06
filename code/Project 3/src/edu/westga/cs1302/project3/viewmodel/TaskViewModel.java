package edu.westga.cs1302.project3.viewmodel;

import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskManagerFileUtility;

import java.io.File;
import java.io.IOException;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
	private final StringProperty addTaskTitle;
	private final StringProperty addTaskDescription;

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

		this.addTaskTitle = new SimpleStringProperty("");
		this.addTaskDescription = new SimpleStringProperty("");
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
	 * Gets the task title property for binding to the INPUT FIELD.
	 * 
	 * @return the task title property
	 */
	public StringProperty addTaskTitleProperty() {
		return this.addTaskTitle;
	}

	/**
	 * Gets the task description property for binding to the input field.
	 * 
	 * @return the task description property
	 */
	public StringProperty addTaskDescriptionProperty() {
		return this.addTaskDescription;
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
		TaskManagerFileUtility.saveTasks(this.taskManager, file);
	}

	/**
	 * Adds a new task to the TaskManager using the provided title and description.
	 * 
	 * @precondition !this.taskTitle.get().isEmpty() &&
	 *               !this.taskDescription.get().isEmpty()
	 * @postcondition The task is added to the TaskManager, and taskTitles is
	 *                updated.
	 */
	public void addTask() {
		if (this.addTaskTitle.get().isEmpty()) {
			throw new IllegalArgumentException("Task title cannot be empty.");
		}
		if (this.addTaskDescription.get().isEmpty()) {
			throw new IllegalArgumentException("Task description cannot be empty.");
		}

		Task newTask = new Task(this.addTaskTitle.get(), this.addTaskDescription.get());
		this.taskManager.addTask(newTask);
		this.updateTaskTitles();
	}

	/**
	 * Updates the taskTitles property to reflect the current tasks in the
	 * TaskManager.
	 */
	private void updateTaskTitles() {
		this.taskTitles.clear();
		for (Task task : this.taskManager.getTasks()) {
			this.taskTitles.add(task.getTitle());
		}
		
	}

	/**
	 * Removes the currently selected task from the TaskManager.
	 * 
	 * @param taskTitle the title of the task to remove
	 * @precondition taskTitle != null && !taskTitle.isEmpty()
	 * @postcondition The task matching the title is removed from the TaskManager,
	 *                and taskTitles is updated.
	 */
	public void removeTask(String taskTitle) {
		if (taskTitle == null || taskTitle.isEmpty()) {
			throw new IllegalArgumentException("Task title cannot be null or empty.");
		}

		Task taskToRemove = null;

		for (Task task : this.taskManager.getTasks()) {
			if (task.getTitle().equals(taskTitle)) {
				taskToRemove = task;
				break;
			}
		}

		if (taskToRemove != null) {
			this.taskManager.removeTask(taskToRemove);
			this.updateTaskTitles();
		} else {
			throw new IllegalArgumentException("Task not found: " + taskTitle);
		}
	}

}
