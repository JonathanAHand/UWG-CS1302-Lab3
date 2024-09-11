package edu.westga.cs1302.cms.test.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.cms.model.Student;

class TestCalculateGradeAverage {

	@Test
	public void testWhenZeroStudentsInList() {
		List<Student> students = new ArrayList<>();
		double grade = Student.calculateGradeAverage(students);
		assertEquals(0.0, grade, 0.01);
	}

	@Test
	public void testWhenSingleStudentInList() {
		List<Student> students = new ArrayList<>();
		students.add(new Student("Joe", 99));
		double grade = Student.calculateGradeAverage(students);
		assertEquals(99.0, grade, 0.01);
	}

	@Test
	public void testGradesForceDoubleResultFromDivision() {
		List<Student> students = new ArrayList<>();
		students.add(new Student("Joe", 97));
		students.add(new Student("Jon", 87));
		students.add(new Student("Jim", 75));
		double grade = Student.calculateGradeAverage(students);
		assertEquals(86.34, grade, 0.01);
	}

	@Test
	public void testWhenMultipleStudentsSameGradesInList() {
		List<Student> students = new ArrayList<>();
		students.add(new Student("Ted", 88));
		students.add(new Student("Tom", 88));
		students.add(new Student("Tim", 88));
		double grade = Student.calculateGradeAverage(students);
		assertEquals(88.0, grade, 0.01);
	}

	@Test
	public void testValidUpperAndLowerValueBoundaryGradesInList() {
		List<Student> students = new ArrayList<>();
		students.add(new Student("Sal", 0));
		students.add(new Student("Sue", 100));
		double grade = Student.calculateGradeAverage(students);
		assertEquals(50.0, grade, 0.01);
	}

}
