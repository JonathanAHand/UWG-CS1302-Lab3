package edu.westga.cs1302.bill.test.model.bill_calculator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;

class TestGetTotal {

	@Test
	void testWithAllValidItems() {
		BillItem[] items = new BillItem[3];
		items[0] = new BillItem("Item 1", 17.00);
		items[1] = new BillItem("Item 2", 3.99);
		items[2] = new BillItem("Item 3", 164.50);

		double expectedTotal = 241.13;
		double actualTotal = BillCalculator.getTotal(items);
		assertEquals(expectedTotal, actualTotal, .01);
	}

	@Test
	void testWithNoItems() {
		BillItem[] items = new BillItem[0];

		double expectedTotal = 0.0;
		double actualTotal = BillCalculator.getTotal(items);
		assertEquals(expectedTotal, actualTotal, .01);
	}

	@Test
	void testWithNullItems() {
		assertThrows(IllegalArgumentException.class, () -> BillCalculator.getTotal(null));
	}

}
