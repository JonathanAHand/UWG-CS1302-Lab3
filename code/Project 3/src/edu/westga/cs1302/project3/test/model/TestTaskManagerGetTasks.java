package edu.westga.cs1302.project3.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

public class TestTaskManagerGetTasks {

	@Test
	void testGetTasksFromEmptyManager() {
		TaskManager manager = new TaskManager();
		List<Task> tasks = manager.getTasks();

		assertNotNull(tasks, "getTasks() should not return null");
		assertTrue(tasks.isEmpty(), "getTasks() should return an empty list for a new manager");
	}

	@Test
	void testGetTasksReturnsAllAddedTasks() {
		TaskManager manager = new TaskManager();
		Task task1 = new Task("Task1", "Description1");
		Task task2 = new Task("Task2", "Description2");

		manager.addTask(task1);
		manager.addTask(task2);

		List<Task> tasks = manager.getTasks();

		assertEquals(2, tasks.size(), "getTasks() should return the correct number of tasks");
		assertTrue(tasks.contains(task1), "getTasks() should contain all added tasks");
		assertTrue(tasks.contains(task2), "getTasks() should contain all added tasks");
	}

	@Test
	void testGetTasksReturnsNewList() {
		TaskManager manager = new TaskManager();
		Task task1 = new Task("Task1", "Description1");

		manager.addTask(task1);
		List<Task> tasks = manager.getTasks();

		tasks.add(new Task("Task2", "Description2"));

		assertEquals(1, manager.getTasks().size(), "Modifying the returned list should not affect the internal list");
	}

}
