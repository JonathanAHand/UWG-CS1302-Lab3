package edu.westga.cs1302.project3.test.model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskManagerFileUtility;

public class TestTaskManagerFileUtilitySaveTasks {

	@Test
	public void testSaveTasksToFile() throws IOException {
		TaskManager manager = new TaskManager();
		manager.addTask(new Task("Task 1", "Description 1"));

		File testFile = new File("testSaveTasks.txt");
		TaskManagerFileUtility.saveTasks(manager, testFile);

		assertTrue(testFile.exists());
		assertTrue(testFile.length() > 0);

		testFile.delete();
	}

	@Test
	public void testSaveThrowsExceptionForNullFile() {
		TaskManager manager = new TaskManager();
		manager.addTask(new Task("Task 1", "Description 1"));

		assertThrows(IllegalArgumentException.class, () -> {
			TaskManagerFileUtility.saveTasks(manager, null);
		});
	}

	@Test
	public void testSaveThrowsExceptionForNullTaskManager() {
		File testFile = new File("testSaveNullManager.txt");

		assertThrows(IllegalArgumentException.class, () -> {
			TaskManagerFileUtility.saveTasks(null, testFile);
		});

		testFile.delete();
	}

	@Test
	public void testSaveEmptyTaskManager() throws IOException {
		TaskManager manager = new TaskManager();
		File testFile = new File("testSaveEmptyManager.txt");

		TaskManagerFileUtility.saveTasks(manager, testFile);

		assertTrue(testFile.exists());
		assertEquals(0, testFile.length());

		testFile.delete();
	}

	@Test
	public void testSaveMultipleTasks() throws IOException {
		TaskManager manager = new TaskManager();
		manager.addTask(new Task("Task 1", "Description 1"));
		manager.addTask(new Task("Task 2", "Description 2"));
		manager.addTask(new Task("Task 3", "Description 3"));

		File testFile = new File("testSaveMultipleTasks.txt");

		TaskManagerFileUtility.saveTasks(manager, testFile);

		assertTrue(testFile.exists());
		assertTrue(testFile.length() > 0);

		testFile.delete();
	}

	@Test
	public void testSaveFileIsNotWritable() {
		TaskManager manager = new TaskManager();
		manager.addTask(new Task("Task 1", "Description 1"));

		File testFile = new File("readonlyFile.txt");
		try {
			if (testFile.createNewFile()) {
				testFile.setWritable(false);
			}

			assertThrows(IOException.class, () -> {
				TaskManagerFileUtility.saveTasks(manager, testFile);
			});
		} catch (IOException e) {
			fail("Setup for read-only file failed.");
		} finally {
			testFile.setWritable(true);
			testFile.delete();
		}
	}
}
