package edu.westga.cs1302.cms.view;

import edu.westga.cs1302.cms.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
	private Label averageGradeLabel;

	// private StudentController studentController;

	/**
	 * Updates and displays the average class grade level accordingly.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void updateStudentGradeAverage() {
		double classAverage = Student.calculateGradeAverage(this.students.getItems());
		this.averageGradeLabel.setText(String.format("Average Class Grade is: %.2f", classAverage));
	}

	/**
	 * Handles the addition of a new student to the class roster list. Triggered by
	 * ActionEvent from UI button press. Retrieves name/grade. Creates Student
	 * object, adds to class roster list, updates class average value accordingly
	 * and displays average on label. Regardless of outcome both name and grade
	 * fields clear.
	 * 
	 * @param event the button click that triggers this method.
	 * @preconditions grade field must contain value that can be parsed as integer.
	 * @precondition name field should have input to be populated.
	 * @postcondition a new student object should be added to the class roster list.
	 * @postcondition average class grade individually recalculated and displayed.
	 * @postconditions both name and text fields are to be cleared.
	 */
	@FXML
	void addStudent(ActionEvent event) {
		String studentName = this.name.getText();
		try {
			int grade = Integer.parseInt(this.grade.getText());
			Student student = new Student(studentName, grade);
			this.students.getItems().add(student);
			this.updateStudentGradeAverage();
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
			this.clearUI();
		}

	}

	private void clearUI() {
		this.name.clear();
		this.grade.clear();
	}

	/**
	 * Handles the removal of selected student name from class roster list.
	 * Triggered by ActionEvent from UI button press. Retrieves selected student and
	 * if selected, removes student from class roster list updating the average
	 * grade level and displaying on label. Regardless of outcome both name/grade
	 * fields clear.
	 * 
	 * @param event the button click that triggers this method.
	 * @preconditions a student's name from the class roster list must be selected
	 *                for removal in the student list view.
	 * @postconditions selected student is removed from the class roster list.
	 * @postconditions average class grade is individually recalculated and
	 *                 displayed.
	 * @postconditions both name and text fields are to be cleared.
	 */
	@FXML
	void removeStudent(ActionEvent event) {
		Student currentStudent = this.students.getSelectionModel().getSelectedItem();
		if (currentStudent != null) {
			this.students.getItems().remove(currentStudent);
			this.updateStudentGradeAverage();
			this.clearUI();
		}
	}

	/**
	 * Initializes controller. Called automatically from JavaFx framework. Sets up
	 * listener on selection model of list view of students. When selected, name and
	 * grade display on respective text fields. If none selected then fields remain
	 * empty.
	 * 
	 * @precondition The FXML file must correctly link the UI components to this
	 *               controller.
	 * @postcondition The text fields are updated with whichever selected student's
	 *                data when selection changes.
	 */
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