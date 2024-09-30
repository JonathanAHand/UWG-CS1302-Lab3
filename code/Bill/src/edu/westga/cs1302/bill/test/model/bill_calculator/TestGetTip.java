package edu.westga.cs1302.bill.test.model.bill_calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.test.TestingConstants;

class TestGetTip {

	@Test
	void testNullItems() {
		assertThrows(IllegalArgumentException.class, ()->{BillCalculator.getTip(null);});
	}

	@Test
	void testEmptyItems() {
		double result = BillCalculator.getTip(new BillItem[0]);
		
		assertEquals(0, result, TestingConstants.DELTA);
	}

	@Test
	void testOneItemNotNull() {
		BillItem[] items = new BillItem[1];
		items[0] = new BillItem("a", 1);
		
		double result = BillCalculator.getTip(items);
		
		assertEquals(0.2, result, TestingConstants.DELTA);
	}

	@Test
	void testOneItemAllNull() {
		BillItem[] items = new BillItem[1];
		items[0] = null;
		
		double result = BillCalculator.getTip(items);
		
		assertEquals(0, result, TestingConstants.DELTA);
	}

	@Test
	void testMultipleItemsNotNull() {
		BillItem[] items = new BillItem[2];
		items[0] = new BillItem("a", 1);
		items[1] = new BillItem("b", 2);
		
		double result = BillCalculator.getTip(items);
		
		assertEquals(0.6, result, TestingConstants.DELTA);
	}

	@Test
	void testMultipleItemsSomeNull() {
		BillItem[] items = new BillItem[2];
		items[0] = new BillItem("a", 1);
		items[1] = null;
		
		double result = BillCalculator.getTip(items);
		
		assertEquals(0.2, result, TestingConstants.DELTA);
	}

	@Test
	void testMultipleItemsAllNull() {
		BillItem[] items = new BillItem[2];
		items[0] = null;
		items[1] = null;
		
		double result = BillCalculator.getTip(items);
		
		assertEquals(0, result, TestingConstants.DELTA);
	}

}
