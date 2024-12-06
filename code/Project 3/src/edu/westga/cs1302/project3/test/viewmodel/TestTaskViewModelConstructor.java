package edu.westga.cs1302.project3.test.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.viewmodel.TaskViewModel;

public class TestTaskViewModelConstructor {

	@Test
	public void testDefaultTasksInTaskManager() {
		TaskViewModel viewModel = new TaskViewModel();

		assertEquals(3, viewModel.taskTitlesProperty().size(), "TaskTitles should contain 3 default tasks.");
		assertTrue(viewModel.taskTitlesProperty().contains("Chores"), "TaskTitles should include 'Chores'.");
		assertTrue(viewModel.taskTitlesProperty().contains("Adulting"), "TaskTitles should include 'Adulting'.");
		assertTrue(viewModel.taskTitlesProperty().contains("Scheming"), "TaskTitles should include 'Scheming'.");
	}

	@Test
	public void testAddTaskTitleInitializedAsEmptyString() {
		TaskViewModel viewModel = new TaskViewModel();

		assertEquals("", viewModel.addTaskTitleProperty().get(),
				"addTaskTitle should be initialized to an empty string.");
	}

	@Test
	public void testAddTaskDescriptionInitializedAsEmptyString() {
		TaskViewModel viewModel = new TaskViewModel();

		assertEquals("", viewModel.addTaskDescriptionProperty().get(),
				"addTaskDescription should be initialized to an empty string.");
	}

}
