package edu.westga.cs1302.project3.test.viewmodel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.viewmodel.TaskViewModel;

public class TestTaskViewModelAddTask {

	@Test
	void testAddTaskWithValidTitleAndDescription() {
		TaskViewModel viewModel = new TaskViewModel();

		viewModel.addTaskTitleProperty().set("Homework");
		viewModel.addTaskDescriptionProperty().set("Finish math homework.");

		assertDoesNotThrow(() -> viewModel.addTask(), "Adding a valid task should not throw an exception.");
		assertTrue(viewModel.taskTitlesProperty().contains("Homework"), "Task title should be added to taskTitles.");
	}

	@Test
	void testAddTaskThrowsExceptionForEmptyTitle() {
		TaskViewModel viewModel = new TaskViewModel();

		viewModel.addTaskTitleProperty().set("");
		viewModel.addTaskDescriptionProperty().set("Some description.");

		Exception exception = assertThrows(IllegalArgumentException.class, viewModel::addTask,
				"Adding a task with an empty title should throw an exception.");
		assertEquals("Task title cannot be empty.", exception.getMessage());
	}

	@Test
	void testAddTaskThrowsExceptionForEmptyDescription() {
		TaskViewModel viewModel = new TaskViewModel();

		viewModel.addTaskTitleProperty().set("Valid Title");
		viewModel.addTaskDescriptionProperty().set("");

		Exception exception = assertThrows(IllegalArgumentException.class, viewModel::addTask,
				"Adding a task with an empty description should throw an exception.");
		assertEquals("Task description cannot be empty.", exception.getMessage());
	}

	@Test
	void testAddTaskUpdatesTaskTitlesAndTaskManager() {
		TaskViewModel viewModel = new TaskViewModel();

		viewModel.addTaskTitleProperty().set("Project");
		viewModel.addTaskDescriptionProperty().set("Complete JavaFX project.");

		viewModel.addTask();

		assertTrue(viewModel.taskTitlesProperty().contains("Project"), "Task title should be added to taskTitles.");
		assertTrue(viewModel.taskTitlesProperty().contains("Project"), "TaskManager should contain the new task.");
	}

	@Test
	void testAddTaskDoesNotModifyOtherTasks() {
		TaskViewModel viewModel = new TaskViewModel();

		int originalSize = viewModel.taskTitlesProperty().size();

		viewModel.addTaskTitleProperty().set("New Task");
		viewModel.addTaskDescriptionProperty().set("This is a new task.");

		viewModel.addTask();

		assertEquals(originalSize + 1, viewModel.taskTitlesProperty().size(),
				"The number of tasks should increase by 1.");
		assertTrue(viewModel.taskTitlesProperty().contains("New Task"), "New task should be added.");
	}

}
