package edu.westga.cs1302.cms.model;

import java.util.List;

/**
 * Stores and manages information for a single student.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class Student {
	private String name;
	private int grade;

	/**
	 * Create a new student with the specified name
	 * 
	 * @precondition name != null && name.length() >= 3
	 * @postcondition getName() == name
	 * @param grade the grade for the student
	 * @param name  the name of the new student
	 */
	public Student(String name, int grade) {
		if (name == null) {
			throw new IllegalArgumentException("Name must be provided.");
		}
		if (name.length() < 3) {
			throw new IllegalArgumentException("Name must have at least 3 characters.");
		}
		if (grade < 0 || grade > 100) {
			throw new IllegalArgumentException("Grade must be between 0 and 100 inclusive.");
		}
		this.name = name;
		this.grade = grade;
	}

	/**
	 * Return the name of the student
	 * 
	 * @return the name of the student
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the grade for the student
	 * 
	 * @return the grade
	 */
	public int getGrade() {
		return this.grade;
	}

	/**
	 * Sets student grade
	 * 
	 * @param grade the grade to set
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return this.name + " --- " + this.grade;
	}

	/**
	 * Calculates average grade of class roster list
	 * 
	 * @param students the list of students in class roster
	 * @return returns the average grade of the students
	 */
	public static double calculateGradeAverage(List<Student> students) {
		if (students.isEmpty()) {
			return 0;
		}
		double sumGrades = 0;
		for (Student student : students) {
			sumGrades += student.getGrade();
		}
		return sumGrades / students.size();
	}

}
