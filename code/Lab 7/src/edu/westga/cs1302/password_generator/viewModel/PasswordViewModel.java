package edu.westga.cs1302.password_generator.viewModel;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The ViewModel class for Password Generator.
 * 
 * @author jhand1
 * @version 1.0
 */
public class PasswordViewModel {

	private StringProperty password;
	private StringProperty errorText;
	private StringProperty minimumLength;
	private StringProperty mustHaveAtLeastOneDigit;
	private StringProperty mustHaveAtLeastOneUpperCaseLetter;
	private StringProperty mustHaveAtLeastOneLowerCaseLetter;

	/**
	 * Instantiates a new PasswordViewModel.
	 */
	public PasswordViewModel() {
		this.password = new SimpleStringProperty("");
		this.errorText = new SimpleStringProperty("");
		this.minimumLength = new SimpleStringProperty();
		this.mustHaveAtLeastOneDigit = new SimpleStringProperty("false");
		this.mustHaveAtLeastOneUpperCaseLetter = new SimpleStringProperty("false");
		this.mustHaveAtLeastOneLowerCaseLetter = new SimpleStringProperty("false");
	}

	/**
	 * Gets the password property.
	 *
	 * @return the password Property.
	 */
	public StringProperty getPasswordProperty() {
		return this.password;
	}
	
	/**
	 * Gets the errorText property.
	 *
	 * @return the errorText Property.
	 */
	public StringProperty getErrorTextProperty() {
		return this.errorText;
	}

	/**
	 * Gets the minimumLength property.
	 *
	 * @return the minimumLength Property.
	 */
	public StringProperty getMinimumLength() {
		return this.minimumLength;
	}

	/**
	 * Gets the mustHaveAtLeastOneDigit property.
	 *
	 * @return the mustHaveAtLeastOneDigit Property.
	 */
	public StringProperty getMustHaveAtLeastOneDigit() {
		return this.mustHaveAtLeastOneDigit;
	}

	/**
	 * Gets the mustHaveAtLeastOneUpperCaseLetter property.
	 *
	 * @return the mustHaveAtLeastOneUpperCaseLetter Property.
	 */
	public StringProperty getMustHaveAtLeastOneUpperCaseLetter() {
		return this.mustHaveAtLeastOneUpperCaseLetter;
	}

	/**
	 * Gets the mustHaveAtLeastOneLowerCaseLetter property.
	 *
	 * @return the mustHaveAtLeastOneLowerCaseLetter Property.
	 */
	public StringProperty getMustHaveAtLeastOneLowerCaseLetter() {
		return this.mustHaveAtLeastOneLowerCaseLetter;
	}

	/**
	 * Generates a password based on the current settings.
	 */
	public void generatePassword() {
		int minLength;
		try {
			minLength = Integer.parseInt(this.minimumLength.get());
			if (minLength < 1) {
				this.errorText.set("Error: Invalid minimum length");
				this.password.set("");
				return;
			}
		} catch (NumberFormatException numberError) {
			this.errorText.set("Error: Minimum length must be at least 1.");
			this.password.set("");
			return;
		}

		boolean includeDigit = Boolean.parseBoolean(this.mustHaveAtLeastOneDigit.get());
		boolean includeUppercase = Boolean.parseBoolean(this.mustHaveAtLeastOneUpperCaseLetter.get());
		boolean includeLowercase = Boolean.parseBoolean(this.mustHaveAtLeastOneLowerCaseLetter.get());

		this.errorText.set("");

		PasswordGenerator generator = new PasswordGenerator(12345);
		generator.setMinimumLength(minLength);
		generator.setMustHaveAtLeastOneDigit(includeDigit);
		generator.setMustHaveAtLeastOneUpperCaseLetter(includeUppercase);
		generator.setMustHaveAtLeastOneLowerCaseLetter(includeLowercase);

		String generatedPassword = generator.generatePassword();

		this.password.set(generatedPassword);
	}
	
}