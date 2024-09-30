package edu.westga.cs1302.bill.test.model.bill;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;

class TestSetServerName {

	@Test
	void testNullName() {
		Bill bill = new Bill();
		
		assertThrows(IllegalArgumentException.class, ()->{bill.setServerName(null);});
	}

	@Test
	void testEmptyName() {
		Bill bill = new Bill();
		
		assertThrows(IllegalArgumentException.class, ()->{bill.setServerName("");});
	}

	@Test
	void testValidName() {
		Bill bill = new Bill();

		bill.setServerName("bob");
		
		assertEquals("bob", bill.getServerName());
	}

}
