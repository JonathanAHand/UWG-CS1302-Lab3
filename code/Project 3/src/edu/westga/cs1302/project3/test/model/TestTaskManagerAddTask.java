package edu.westga.cs1302.project3.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

public class TestTaskManagerAddTask {

	@Test
	void testAddTaskWithValidTask() {
		TaskManager taskManager = new TaskManager();
		Task task = new Task("Title1", "Description1");
		taskManager.addTask(task);
		assertTrue(taskManager.getTasks().contains(task));
	}

	@Test
	void testAddTaskThrowsWithNullTask() {
		TaskManager taskManager = new TaskManager();

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			taskManager.addTask(null);
		});
		assertEquals("Task cannot be null", exception.getMessage());
	}

	@Test
	void testAddTaskThrowsWithDuplicatedTitle() {
		TaskManager taskManager = new TaskManager();
		Task task1 = new Task("Title1", "Description1");
		Task task2 = new Task("Title1", "Different Description");

		taskManager.addTask(task1);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			taskManager.addTask(task2);
		});
		assertEquals("A task with the same title already exists.", exception.getMessage());
	}

	@Test
	void testAddTaskWithDifferentTitles() {
		TaskManager taskManager = new TaskManager();
		Task task1 = new Task("Title1", "Description1");
		Task task2 = new Task("Title2", "Description1");

		taskManager.addTask(task1);
		taskManager.addTask(task2);

		assertTrue(taskManager.getTasks().contains(task1));
		assertTrue(taskManager.getTasks().contains(task2));
	}

	@Test
	void testAddTaskUpdatesLookupTable() {
		TaskManager taskManager = new TaskManager();
		Task task = new Task("Title1", "Description1");

		taskManager.addTask(task);

		assertTrue(taskManager.getTasks().contains(task), "Task should be in the task list");
		assertEquals(task, taskManager.getTasks().get(0), "Task should match the one added");
	}

}
