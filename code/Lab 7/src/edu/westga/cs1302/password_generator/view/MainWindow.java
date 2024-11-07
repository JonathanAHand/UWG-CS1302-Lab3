package edu.westga.cs1302.password_generator.view;

import edu.westga.cs1302.password_generator.viewModel.PasswordViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

/**
 * Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

	@FXML
	private TextField minimumLength;

	@FXML
	private CheckBox mustIncludeDigits;

	@FXML
	private CheckBox mustIncludeLowerCaseLetters;

	@FXML
	private CheckBox mustIncludeUpperCaseLetters;

	@FXML
	private Button generateButton;

	@FXML
	private Label passwordLabel;

	@FXML
	private TextArea errorText;

	private PasswordViewModel viewModel;

	/**
	 * Instantiates a new MainWindow.
	 */
	public MainWindow() {
		this.viewModel = new PasswordViewModel();
	}

	@FXML
	private void generatePassword() {
		this.viewModel.generatePassword();
	}

	@FXML
	void initialize() {
		this.bindComponentsToViewModel();
	}

	private void bindComponentsToViewModel() {
		
		this.passwordLabel.textProperty().bind(this.viewModel.getPasswordProperty());
		this.errorText.textProperty().bind(this.viewModel.getErrorTextProperty());
		this.minimumLength.textProperty().bindBidirectional(this.viewModel.getMinimumLength());
		this.mustIncludeDigits.selectedProperty().addListener(
				(obs, oldVal, newVal) -> this.viewModel.getMustHaveAtLeastOneDigit().set(newVal.toString()));
		this.mustIncludeUpperCaseLetters.selectedProperty().addListener(
				(obs, oldVal, newVal) -> this.viewModel.getMustHaveAtLeastOneUpperCaseLetter().set(newVal.toString()));
		this.mustIncludeLowerCaseLetters.selectedProperty().addListener(
				(obs, oldVal, newVal) -> this.viewModel.getMustHaveAtLeastOneLowerCaseLetter().set(newVal.toString()));
		
		this.generateButton.setOnAction(event -> this.viewModel.generatePassword());
	}

}