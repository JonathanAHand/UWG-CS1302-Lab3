package edu.westga.cs1302.cms.view;

import edu.westga.cs1302.cms.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/** Code behind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
    @FXML private TextField name;
    @FXML private ListView<Student> students;

    @FXML
    void addStudent(ActionEvent event) {
    	String studentName = this.name.getText();
    	
    	Student student = new Student(studentName);
    	
    	this.students.getItems().add(student);
    }

    @FXML
    void removeStudent(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert this.name != null : "fx:id=\"name\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.students != null : "fx:id=\"students\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }

}
