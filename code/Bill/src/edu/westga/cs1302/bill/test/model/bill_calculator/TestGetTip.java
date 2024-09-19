package edu.westga.cs1302.bill.test.model.bill_calculator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;

class TestGetTip {

	@Test
	void testWithAllValidItems() {
		BillItem[] items = new BillItem[3];
		items[0] = new BillItem("Item 1", 17.00);
		items[1] = new BillItem("Item 2", 3.99);
		items[2] = new BillItem("Item 3", 164.50);

		double expectedTip = 37.09;
		double actualTip = BillCalculator.getTip(items);
		assertEquals(expectedTip, actualTip, .01);
	}

	@Test
	void testWithNoItems() {
		BillItem[] items = new BillItem[0];

		double expectedTip = 0.0;
		double actualTip = BillCalculator.getTip(items);
		assertEquals(expectedTip, actualTip, .01);
	}

	@Test
	void testWithNullItems() {
		assertThrows(IllegalArgumentException.class, () -> BillCalculator.getTip(null));
	}
}