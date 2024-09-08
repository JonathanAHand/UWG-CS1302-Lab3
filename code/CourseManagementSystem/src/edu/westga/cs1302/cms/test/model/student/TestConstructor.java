package edu.westga.cs1302.cms.test.model.student;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import edu.westga.cs1302.cms.model.Student;

class TestConstructor {

	@Test
	void testNullName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Student(null, 99);
		});
	}

	@Test
	void testNameTooShort() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Student("Bo", 99);
		});
	}

	@Test
	void testMinimumLengthName() {
		Student result = new Student("Billy Bob Thornton", 99);

		assertEquals("Billy Bob Thornton", result.getName(), "Checking the minimum name length of the student.");
	}

	@Test
	public void testWhenGradeAtMinimumBoundary() {
		Student result = new Student("Billy Madison", 0);

		assertEquals(0, result.getGrade(), "Checking the minimum grade for the student.");
	}
	
	@Test
	public void testWhenGradeAtMaximumBoundary() {
		Student result = new Student("Billy the Kid",100);

		assertEquals(100, result.getGrade(), "Checking the maximum grade for the student.");
	}

	@Test
	public void testWhenGradeOneBelowMinimumBoundary() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Student("Billy Idol", -1);
		});
	}

	@Test
	public void testWhenGradeOneAboveMaximumBoundary() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Student("Billy Corgan", 101);
		});
	}
}
