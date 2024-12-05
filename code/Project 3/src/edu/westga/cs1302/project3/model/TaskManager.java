package edu.westga.cs1302.project3.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of tasks and allows for adding and removing them.
 * 
 * @author jhand1
 * @version 1.0
 */
public class TaskManager {
	private List<Task> tasks;
	private Map<String, Task> taskLookupTable;

	/**
	 * Creates a new TaskManager with an empty List of tasks.
	 * 
	 * @precondition none
	 * @postcondition getTasks().isEmpty()
	 */
	public TaskManager() {
		this.tasks = new ArrayList<>();
		this.taskLookupTable = new HashMap<>();
	}

	/**
	 * Returns the list of tasks in the TaskManager.
	 * 
	 * @return new list of tasks in the TaskManager.
	 */
	public List<Task> getTasks() {
		return new ArrayList<>(this.tasks);
	}

	/**
	 * Adds a new task to the TaskManager.
	 * 
	 * @precondition task != null
	 * @postcondition this.getTasks().contains(task)
	 * 
	 * @param task the task being added
	 * @throws IllegalArgumentException if the task is null or if a task with the
	 *                                  same title and description already exists
	 *                                  and cannot be added.
	 */
	public void addTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Task cannot be null");
		}
		
		Task existingTask = this.taskLookupTable.get(task.getTitle());
		if (existingTask != null && existingTask.getDescription().equals(task.getDescription())) {
            throw new IllegalArgumentException("A task with the same title and description already exists.");
        }
		this.tasks.add(task);
	    this.taskLookupTable.put(task.getTitle(), task);

	}

	/**
	 * Removes a task from the TaskManager.
	 * 
	 * @precondition task != null
	 * @postcondition !this.getTasks().contains(task)
	 * 
	 * @param task the task to remove
	 * @throws IllegalArgumentException if the task is null or not in the list
	 */
	public void removeTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Task cannot be null.");
		}
		if (!this.tasks.remove(task)) {
			throw new IllegalArgumentException("Task was not found within the TaskManager.");
		}
	}

}
