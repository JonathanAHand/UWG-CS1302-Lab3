package edu.westga.cs1302.project3.test.viewmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.viewmodel.TaskViewModel;

public class TestTaskViewModelSaveVMTasks {

	@Test
	void testSaveToNullFile() {
		TaskViewModel viewModel = new TaskViewModel();
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			viewModel.saveVMTasks(null);
		});
		assertEquals("The file cannot be null.", exception.getMessage());
	}

	@Test
	void testSaveToValidFile() throws IOException {
		TaskViewModel viewModel = new TaskViewModel();
		File testFile = new File("testSaveTasks.txt");

		try {
			viewModel.saveVMTasks(testFile);

			TaskViewModel loadedViewModel = new TaskViewModel();
			loadedViewModel.loadVMTasks(testFile);

			assertEquals(viewModel.taskTitlesProperty().get(), loadedViewModel.taskTitlesProperty().get());
		} finally {
			if (testFile.exists()) {
				testFile.delete();
			}
		}
	}

	@Test
	void testSaveTasksThrowsIOExceptionForLockedFile() throws IOException {
		TaskViewModel viewModel = new TaskViewModel();
		File testFile = new File("lockedFile.txt");

		testFile.createNewFile();
		testFile.setReadOnly();

		try {
			Exception exception = assertThrows(IOException.class, () -> {
				viewModel.saveVMTasks(testFile);
			});
			assertTrue(exception.getMessage().contains("The file cannot be written to"));
		} finally {
			testFile.setWritable(true);
			testFile.delete();
		}
	}

	@Test
	void testSaveEmptyTaskManager() throws IOException {
		TaskViewModel viewModel = new TaskViewModel();
		viewModel.removeTask("Chores");
		viewModel.removeTask("Adulting");
		viewModel.removeTask("Scheming");

		File testFile = new File("testEmptySaveTasks.txt");

		try {
			viewModel.saveVMTasks(testFile);

			TaskViewModel loadedViewModel = new TaskViewModel();
			loadedViewModel.loadVMTasks(testFile);

			assertTrue(loadedViewModel.taskTitlesProperty().isEmpty());
		} finally {
			if (testFile.exists()) {
				testFile.delete();
			}
		}
	}

}
