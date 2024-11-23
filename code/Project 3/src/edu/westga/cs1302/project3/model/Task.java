package edu.westga.cs1302.project3.model;

/**
 * Represents a single task with a title and description.
 * 
 * @author jhand1
 * @version 1.0
 */
public class Task {
	private String title;
	private String description;

	/**
	 * Creates a new task with the specified title and description.
	 * 
	 * @precondition title != null && !title.isEmpty() && description != null &&
	 *               !description.isEmpty()
	 * @postcondition getTitle() == title && getDescription() == description
	 * 
	 * @param title       the title summarizing the task.
	 * @param description the detailed description of the task.
	 */
	public Task(String title, String description) {
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("A valid title must be provided.");
		}

		if (description == null || description.isEmpty()) {
			throw new IllegalArgumentException("A valid description must be provided.");
		}

		this.title = title;
		this.description = description;
	}

	/**
	 * Returns the title of the task.
	 * 
	 * @return the title of the task.
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Returns the description details of the task.
	 * 
	 * @return the description details of the task.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the title of the task.
	 * 
	 * @precondition title != null && !title.isEmpty()
	 * @postcondition getTitle() == title
	 * 
	 * @param title the new title for the task.
	 */
	public void setTitle(String title) {
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("A valid title must be provided.");
		}
		this.title = title;
	}

	/**
	 * Sets the description of the task.
	 * 
	 * @precondition description != null && !description.isEmpty()
	 * @postcondition getDescription() == description
	 * 
	 * @param description the new description for the task.
	 */
	public void setDescription(String description) {
		if (description == null || description.isEmpty()) {
			throw new IllegalArgumentException("A valid description must be provided.");
		}
		this.description = description;
	}

	@Override
	/**
	 * Returns a string representation of the task.
	 * 
	 * @return a string containing the task's title and description in the format
	 *         "title: description"
	 */
	public String toString() {
		return this.title + ": " + this.description;
	}

}
