package edu.westga.cs1302.project3.test.viewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.viewmodel.TaskViewModel;

public class TestTaskViewModelRemoveTask {
	
	@Test
    void testRemoveExistingTask() {
        TaskViewModel viewModel = new TaskViewModel();

        String taskToRemove = "Chores";
        assertTrue(viewModel.taskTitlesProperty().contains(taskToRemove), "Task should exist before removal.");

        viewModel.removeTask(taskToRemove);

        assertFalse(viewModel.taskTitlesProperty().contains(taskToRemove), "Task should be removed from taskTitles.");
    }

    @Test
    void testRemoveTaskThrowsExceptionForNullTitle() {
        TaskViewModel viewModel = new TaskViewModel();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> viewModel.removeTask(null), "Removing a task with a null title should throw an exception.");
        assertEquals("Task title cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testRemoveTaskThrowsExceptionForEmptyTitle() {
        TaskViewModel viewModel = new TaskViewModel();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> viewModel.removeTask(""), "Removing a task with an empty title should throw an exception.");
        assertEquals("Task title cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testRemoveTaskThrowsExceptionIfTaskNotFound() {
        TaskViewModel viewModel = new TaskViewModel();

        String nonExistentTask = "Nonexistent Task";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> viewModel.removeTask(nonExistentTask), "Removing a non-existent task should throw an exception.");
        assertEquals("Task not found: Nonexistent Task", exception.getMessage());
    }

    @Test
    void testRemoveTaskDoesNotAffectOtherTasks() {
        TaskViewModel viewModel = new TaskViewModel();

        int originalSize = viewModel.taskTitlesProperty().size();
        String taskToRemove = "Chores";

        viewModel.removeTask(taskToRemove);

        assertEquals(originalSize - 1, viewModel.taskTitlesProperty().size(), "The number of tasks should decrease by 1.");
        assertFalse(viewModel.taskTitlesProperty().contains(taskToRemove), "Removed task should not exist in taskTitles.");
        assertTrue(viewModel.taskTitlesProperty().contains("Adulting"), "Other tasks should remain unaffected.");
    }

}
