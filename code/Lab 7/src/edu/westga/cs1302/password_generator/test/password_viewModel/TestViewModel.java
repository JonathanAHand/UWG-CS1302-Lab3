package edu.westga.cs1302.password_generator.test.password_viewModel;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewModel.PasswordViewModel;

public class TestViewModel {
// for the love of Pete why couldn't we just use regular expressions
	@Test
	void testMinimumLength1NoOtherRestriction() {
		PasswordViewModel viewModel = new PasswordViewModel();
		viewModel.getMinimumLength().set("1");
		viewModel.getMustHaveAtLeastOneUpperCaseLetter().set("false");
		viewModel.getMustHaveAtLeastOneLowerCaseLetter().set("false");
		viewModel.getMustHaveAtLeastOneDigit().set("false");

		viewModel.generatePassword();

		String result = viewModel.getPasswordProperty().get();
		assertTrue(result.length() >= 1, "Password should be at least 1 character long");
	}

	@Test
	void testMinimumLength3NoOtherRestriction() {
		PasswordViewModel viewModel = new PasswordViewModel();
		viewModel.getMinimumLength().set("3");
		viewModel.getMustHaveAtLeastOneUpperCaseLetter().set("false");
		viewModel.getMustHaveAtLeastOneLowerCaseLetter().set("false");
		viewModel.getMustHaveAtLeastOneDigit().set("false");

		viewModel.generatePassword();

		String result = viewModel.getPasswordProperty().get();
		assertTrue(result.length() >= 3, "Password should be at least 3 characters long");
	}

	@Test
	void testMinimumLength3AtLeastOneUpperCaseLetterNoOtherRestriction() {
		PasswordViewModel viewModel = new PasswordViewModel();
		viewModel.getMinimumLength().set("3");
		viewModel.getMustHaveAtLeastOneUpperCaseLetter().set("true");
		viewModel.getMustHaveAtLeastOneLowerCaseLetter().set("false");
		viewModel.getMustHaveAtLeastOneDigit().set("false");

		viewModel.generatePassword();

		String result = viewModel.getPasswordProperty().get();
		if (result.length() < 3) {
			throw new AssertionError("Password should be at least 3 characters long, but was " + result.length());
		}

		boolean hasUppercase = false;
		for (char c : result.toCharArray()) {
			if (c >= 65 && c <= 90) {
				hasUppercase = true;
				break;
			}
		}
		if (!hasUppercase) {
			throw new AssertionError("Password should contain at least one uppercase letter");
		}
	}

	@Test
	void testMinimumLength3AtLeastOneDigitNoOtherRestriction() {
		PasswordViewModel viewModel = new PasswordViewModel();
		viewModel.getMinimumLength().set("3");
		viewModel.getMustHaveAtLeastOneUpperCaseLetter().set("false");
		viewModel.getMustHaveAtLeastOneLowerCaseLetter().set("false");
		viewModel.getMustHaveAtLeastOneDigit().set("true");

		viewModel.generatePassword();

		String result = viewModel.getPasswordProperty().get();
		if (result.length() < 3) {
			throw new AssertionError("Password must be at least 3 characters long, but was " + result.length());
		}

		boolean hasDigit = false;
		for (char c : result.toCharArray()) {
			if (c >= 48 && c <= 57) {
				hasDigit = true;
				break;
			}
		}
		if (!hasDigit) {
			throw new AssertionError("Password must contain at least one digit");
		}
	}

	@Test
	void testMinimumLength3AtLeastOneLowerCaseLetterNoOtherRestriction() {
		PasswordViewModel viewModel = new PasswordViewModel();
		viewModel.getMinimumLength().set("3");
		viewModel.getMustHaveAtLeastOneUpperCaseLetter().set("false");
		viewModel.getMustHaveAtLeastOneLowerCaseLetter().set("true");
		viewModel.getMustHaveAtLeastOneDigit().set("false");

		viewModel.generatePassword();

		String result = viewModel.getPasswordProperty().get();
		if (result.length() < 3) {
			throw new AssertionError("Password should be at least 3 characters long, but was " + result.length());
		}

		boolean hasLowercase = false;
		for (char c : result.toCharArray()) {
			if (c >= 97 && c <= 122) {
				hasLowercase = true;
				break;
			}
		}
		if (!hasLowercase) {
			throw new AssertionError("Password should contain at least one lowercase letter");
		}
	}

	@Test
	void testMinimumLength3AtLeastOneLowerCaseLetterAtLeastOneUpperCaseLetterNoOtherRestriction() {
		PasswordViewModel viewModel = new PasswordViewModel();
		viewModel.getMinimumLength().set("3");
		viewModel.getMustHaveAtLeastOneUpperCaseLetter().set("true");
		viewModel.getMustHaveAtLeastOneLowerCaseLetter().set("true");
		viewModel.getMustHaveAtLeastOneDigit().set("false");

		viewModel.generatePassword();

		String result = viewModel.getPasswordProperty().get();
		if (result.length() < 3) {
			throw new AssertionError("Password has to have at least 3 characters, but only had " + result.length());
		}

		boolean hasUppercase = false;
		boolean hasLowercase = false;
		for (char c : result.toCharArray()) {
			if (c >= 65 && c <= 90) {
				hasUppercase = true;
			}
			if (c >= 97 && c <= 122) {
				hasLowercase = true;
			}
		}
		if (!hasUppercase) {
			throw new AssertionError("Password must have at least one uppercase letter");
		}
		if (!hasLowercase) {
			throw new AssertionError("Password must have at least one lowercase letter");
		}
	}

	@Test
	void testMinimumLength3AtLeastOneLowerCaseLetterAtLeastOneDigitNoOtherRestriction() {
		PasswordViewModel viewModel = new PasswordViewModel();
		viewModel.getMinimumLength().set("3");
		viewModel.getMustHaveAtLeastOneUpperCaseLetter().set("false");
		viewModel.getMustHaveAtLeastOneLowerCaseLetter().set("true");
		viewModel.getMustHaveAtLeastOneDigit().set("true");

		viewModel.generatePassword();

		String result = viewModel.getPasswordProperty().get();
		if (result.length() < 3) {
			throw new AssertionError("Password should be at least 3 characters long, but was " + result.length());
		}

		boolean hasLowercase = false;
		boolean hasDigit = false;
		for (char c : result.toCharArray()) {
			if (c >= 97 && c <= 122) {
				hasLowercase = true;
			}
			if (c >= 48 && c <= 57) {
				hasDigit = true;
			}
		}
		if (!hasLowercase) {
			throw new AssertionError("Password should contain at least one lowercase letter");
		}
		if (!hasDigit) {
			throw new AssertionError("Password should contain at least one digit");
		}
	}

	@Test
	void testMinimumLength3AtLeastOneUpperCaseLetterAtLeastOneDigitNoOtherRestriction() {
		PasswordViewModel viewModel = new PasswordViewModel();
		viewModel.getMinimumLength().set("3");
		viewModel.getMustHaveAtLeastOneUpperCaseLetter().set("true");
		viewModel.getMustHaveAtLeastOneLowerCaseLetter().set("false");
		viewModel.getMustHaveAtLeastOneDigit().set("true");

		viewModel.generatePassword();

		String result = viewModel.getPasswordProperty().get();
		if (result.length() < 3) {
			throw new AssertionError("Password should be at least 3 characters long, but was " + result.length());
		}

		boolean hasUppercase = false;
		boolean hasDigit = false;
		for (char c : result.toCharArray()) {
			if (c >= 65 && c <= 90) {
				hasUppercase = true;
			}
			if (c >= 48 && c <= 57) {
				hasDigit = true;
			}
		}
		if (!hasUppercase) {
			throw new AssertionError("Password should contain at least one uppercase letter");
		}
		if (!hasDigit) {
			throw new AssertionError("Password should contain at least one digit");
		}
	}

	@Test
	void testMinimumLength3AtLeastOneUpperCaseLetterAtLeastOneLowerCaseLetterAtLeastOneDigit() {
		PasswordViewModel viewModel = new PasswordViewModel();
		viewModel.getMinimumLength().set("3");
		viewModel.getMustHaveAtLeastOneUpperCaseLetter().set("true");
		viewModel.getMustHaveAtLeastOneLowerCaseLetter().set("true");
		viewModel.getMustHaveAtLeastOneDigit().set("true");

		viewModel.generatePassword();

		String result = viewModel.getPasswordProperty().get();
		if (result.length() < 3) {
			throw new AssertionError("Password should be at least 3 characters long, but was " + result.length());
		}

		boolean hasUppercase = false;
		boolean hasLowercase = false;
		boolean hasDigit = false;
		for (char c : result.toCharArray()) {
			if (c >= 65 && c <= 90) {
				hasUppercase = true;
			}
			if (c >= 97 && c <= 122) {
				hasLowercase = true;
			}
			if (c >= 48 && c <= 57) {
				hasDigit = true;
			}
		}
		if (!hasUppercase) {
			throw new AssertionError("Password should contain at least one uppercase letter");
		}
		if (!hasLowercase) {
			throw new AssertionError("Password should contain at least one lowercase letter");
		}
		if (!hasDigit) {
			throw new AssertionError("Password should contain at least one digit");
		}
	}

}
