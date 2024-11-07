package edu.westga.cs1302.password_generator.test.password_viewModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewModel.PasswordViewModel;

public class TestSetMinimumLength {
	@Test
	void testSetMinimumLengthValidValue() {
		PasswordViewModel viewModel = new PasswordViewModel();
		viewModel.getMinimumLength().set("3");

		assertEquals("3", viewModel.getMinimumLength().get());
	}

	@Test
	void testSetMinimumLengthInvalidValue() {
		PasswordViewModel viewModel = new PasswordViewModel();
		viewModel.getMinimumLength().set("0");

		viewModel.generatePassword();

		assertEquals("Error: Invalid minimum length", viewModel.getErrorTextProperty().get(),
				"Error text will diplay invalid minimum length");
	}

}