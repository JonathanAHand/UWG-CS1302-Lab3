package edu.westga.cs1302.password_generator.test.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;

class TestGeneratePassword {

	@Test
	void testMinimumLengthNotANumber() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("apple");

		vm.generatePassword();

		assertEquals("", vm.getPassword().getValue(), "checking the password property");
		assertEquals("Invalid Minimum Length: must be a positive integer, but was apple", vm.getErrorText().getValue(),
				"checking the error text property");
	}

	@Test
	void testMinimumLengthNotAValidNumber() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("-2");

		vm.generatePassword();

		assertEquals("", vm.getPassword().getValue(), "checking the password property");
		assertEquals("Invalid Minimum Length: minimum length must be at least 1", vm.getErrorText().getValue(),
				"checking the error text property");
	}

	@Test
	void testValidInputProvided() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("2");

		vm.generatePassword();
		assertFalse(vm.getPasswords().isEmpty(), "checking that the passwords list is not empty");
		String mostRecentPassword = vm.getPasswords().get(vm.getPasswords().size() - 1);

		assertTrue(mostRecentPassword.length() >= 2,
				"checking the most recent password has an appropriate number of characters");
		assertEquals("", vm.getErrorText().getValue(), "checking the error text property");
	}

	@Test
	void testMultiplePasswordsAreAdded() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("3");

		vm.generatePassword();
		vm.generatePassword();

		assertEquals(2, vm.getPasswords().size(), "checking that multiple passwords are added to the list");
	}

	@Test
	void testPasswordListInitiallyEmpty() {
		ViewModel vm = new ViewModel();
		assertTrue(vm.getPasswords().isEmpty(), "checking that the password list is initially empty");
	}

	@Test
	void testGeneratedPasswordsMeetRequirements() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("4");
		vm.generatePassword();

		String password = vm.getPasswords().get(0);
		assertTrue(password.length() >= 4, "checking the generated password meets the minimum length");
	}

	@Test
	void testDuplicatePasswordsAllowedNoGenerator() {
		ViewModel vm = new ViewModel();

		vm.getPasswords().add("password123");
		vm.getPasswords().add("password123");

		assertEquals(2, vm.getPasswords().size(), "checking the list contains two entries");
		assertEquals(vm.getPasswords().get(0), vm.getPasswords().get(1), "checking the passwords are duplicates");
	}

	@Test
	void testVerifyMinimumLength() {
		String alphanumericPattern = "^[a-zA-Z0-9][a-zA-Z0-9]*$";

		assertTrue("abc123".matches(alphanumericPattern), "checking valid alphanumeric input");
		assertTrue("12345".matches(alphanumericPattern), "checking valid numeric input");
		assertTrue("A1b2C3".matches(alphanumericPattern), "checking mixed alphanumeric input");

		assertFalse("".matches(alphanumericPattern), "checking empty input");
		assertFalse("abc@123".matches(alphanumericPattern), "checking input with special characters");
		assertFalse(" 123".matches(alphanumericPattern), "checking input with leading space");
		assertFalse("abc 123".matches(alphanumericPattern), "checking input with embedded space");
	}

}
