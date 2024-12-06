package edu.westga.cs1302.project3.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;

public class TestTaskToString {

	@Test
	public void testToStringWithValidTitleAndDescription() {
		Task task = new Task("Grocery Shopping", "Buy milk, eggs, and bread");
		assertEquals("Grocery Shopping: Buy milk, eggs, and bread", task.toString());
	}

	@Test
	public void testToStringWithSpecialCharacters() {
		Task task = new Task("Title@123", "Description with #$%");
		assertEquals("Title@123: Description with #$%", task.toString());
	}

	@Test
	public void testToStringWithWhitespaceTitleAndDescription() {
		Task task = new Task("    ", "   ");
		assertEquals("    :    ", task.toString());
	}

}
