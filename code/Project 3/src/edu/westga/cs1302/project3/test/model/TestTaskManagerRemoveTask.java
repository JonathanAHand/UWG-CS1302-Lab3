package edu.westga.cs1302.project3.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

public class TestTaskManagerRemoveTask {

	@Test
	void testRemoveTaskWithValidTask() {
		TaskManager taskManager = new TaskManager();
		Task task = new Task("Title1", "Description1");
		taskManager.addTask(task);

		taskManager.removeTask(task);
		assertFalse(taskManager.getTasks().contains(task));
	}

	@Test
	void testRemoveTaskThrowsWithNullTask() {
		TaskManager taskManager = new TaskManager();

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			taskManager.removeTask(null);
		});
		assertEquals("Task cannot be null.", exception.getMessage());
	}

	@Test
	void testRemoveTaskThrowsWithTaskNotFound() {
		TaskManager taskManager = new TaskManager();
		Task task = new Task("Title1", "Description1");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			taskManager.removeTask(task);
		});
		assertEquals("Task was not found within the TaskManager.", exception.getMessage());
	}

	@Test
	void testRemoveTaskUpdatesLookupTable() {
		TaskManager taskManager = new TaskManager();
		Task task = new Task("Title1", "Description1");

		taskManager.addTask(task);
		taskManager.removeTask(task);

		assertFalse(taskManager.getTasks().contains(task), "Task should not be in the task list after removal");
	}

}
