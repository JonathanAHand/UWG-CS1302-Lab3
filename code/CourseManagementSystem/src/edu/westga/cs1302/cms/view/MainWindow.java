package edu.westga.cs1302.cms.view;

import edu.westga.cs1302.cms.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Code behind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML
	private TextField name;
	@FXML
	private TextField grade;
	@FXML
	private ListView<Student> students;

	@FXML
	void addStudent(ActionEvent event) {
		String studentName = this.name.getText();
		try {
			int grade = Integer.parseInt(this.grade.getText());
			Student student = new Student(studentName, grade);
			this.students.getItems().add(student);
		} catch (NumberFormatException errorThing) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText("Grade must be a valid number. Please enter a valid grade");
			errorPopup.showAndWait();
		} catch (IllegalArgumentException errorObject) {
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText("Unable to create student: " + errorObject.getMessage()
					+ ". Please re-enter student name and try again.");
			errorPopup.showAndWait();
		} finally {
			this.name.clear();
			this.grade.clear();
		}

	}

	@FXML
	void removeStudent(ActionEvent event) {
		Student currentStudent = this.students.getSelectionModel().getSelectedItem();
		if (currentStudent != null) {
			this.students.getItems().remove(currentStudent);
			this.name.clear();
			this.grade.clear();
		}
	}

	@FXML
	void initialize() {
		assert this.name != null : "fx:id=\"name\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.students != null : "fx:id=\"students\" was not injected: check your FXML file 'MainWindow.fxml'.";
		
		this.students.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, currentValue) -> {
	        if (currentValue != null) {
	            this.name.setText(currentValue.getName());
	            this.grade.setText(Integer.toString(currentValue.getGrade()));

	        }
		});
	}
}