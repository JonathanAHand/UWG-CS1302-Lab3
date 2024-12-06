package edu.westga.cs1302.project3.test.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.TaskManagerFileUtility;
import edu.westga.cs1302.project3.viewmodel.TaskViewModel;

public class TestTaskViewModelLoadVMTasks {

	@Test
	public void testLoadVMTasksLoadsTasksFromFile() throws IOException {
		TaskViewModel viewModel = new TaskViewModel();
		File testFile = new File("testLoadTasks.txt");
		TaskManagerFileUtility.saveTasks(viewModel.getTaskManager(), testFile);

		viewModel.loadVMTasks(testFile);

		assertEquals(3, viewModel.taskTitlesProperty().size());
		testFile.delete(); // Cleanup
	}

	@Test
	public void testLoadVMTasksThrowsWithInvalidFile() {
		TaskViewModel viewModel = new TaskViewModel();
		File nonExistentFile = new File("nonexistent.txt");

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> viewModel.loadVMTasks(nonExistentFile));
		assertEquals("The file does not exist or is invalid.", exception.getMessage());
	}

}
