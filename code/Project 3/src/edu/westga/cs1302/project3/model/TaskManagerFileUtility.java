package edu.westga.cs1302.project3.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Provides utility methods to save and load tasks to and from files.
 * 
 * @author jhand1
 * @version 1.0
 * 
 */
public class TaskManagerFileUtility {

	/**
	 * Saves the tasks in the given TaskManager to the specified file.
	 * 
	 * @precondition manager != null && filePath != null
	 * @postcondition none
	 * @param manager the TasManager containing the tasks to save.
	 * @param file    the tasks where the files will be saved to.
	 * @throws IOException if an error happens while writing to the file.
	 */
	public static void saveTasks(TaskManager manager, File file) throws IOException {
		if (manager == null) {
			throw new IllegalArgumentException("TaskManager cannot be null.");
		}

		if (file == null) {
			throw new IllegalArgumentException("File cannot be null.");
		}

		try (FileWriter writer = new FileWriter(file)) {
			for (Task task : manager.getTasks()) {
				writer.write(task.getTitle() + ":" + task.getDescription() + System.lineSeparator());

			}
		} catch (IOException error) {
	        throw new IOException("The file cannot be written to. Please check permissions or if the file is locked.", error);
		}
	}

	/**
	 * Loads tasks from the specified file into a new TaskManager.
	 * 
	 * @precondition filePath != null
	 * @postcondition none
	 * 
	 * @param filePath the file to load the tasks from
	 * @return a new TaskManager containing the tasks from the file
	 * @throws IOException              if an I/O error occurs while reading the
	 *                                  file
	 * @throws IllegalArgumentException if the file contains malformed task data
	 */
	public static TaskManager loadTasks(File filePath) throws IOException {
		if (filePath == null) {
			throw new IllegalArgumentException("File path cannot be null.");
		}

		TaskManager manager = new TaskManager();

		try (Scanner reader = new Scanner(filePath)) {
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] parts = line.split(":", 2);

				if (parts.length != 2) {
					throw new IllegalArgumentException(
							"Malformed task data: " + line + ". Please use format (Task:Description).");
				}
				String title = parts[0];
				String description = parts[1];
				if (title.isEmpty() || description.isEmpty()) {
	                throw new IllegalArgumentException("Malformed task data (empty fields): " + line);
	            }
				manager.addTask(new Task(title, description));
			}
			
		} catch (IOException error) {
	        throw new IOException("An error occurred while reading the file.", error);
		}
		return manager;
	}
}
