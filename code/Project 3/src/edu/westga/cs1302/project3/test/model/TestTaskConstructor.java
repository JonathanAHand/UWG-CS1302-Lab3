package edu.westga.cs1302.project3.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;

public class TestTaskConstructor {

	@Test
	void testWithValidTitleAndDescription() {
		Task task = new Task("Homework", "Complete math assignment.");
		assertEquals("Homework", task.getTitle());
		assertEquals("Complete math assignment.", task.getDescription());
	}

	@Test
	void testThrowsWithNullTitle() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Task(null, "Complete math assignment.");
		});
		assertEquals("A valid title must be provided.", exception.getMessage());
	}

	@Test
	void testThrowsWithEmptyTitle() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Task("", "Complete math assignment.");
		});
		assertEquals("A valid title must be provided.", exception.getMessage());
	}

	@Test
	void testThrowsWithNullDescription() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Task("Homework", null);
		});
		assertEquals("A valid description must be provided.", exception.getMessage());
	}

	@Test
	void testWithEmptyDescription() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Task("Homework", "");
		});
		assertEquals("A valid description must be provided.", exception.getMessage());
	}

}
