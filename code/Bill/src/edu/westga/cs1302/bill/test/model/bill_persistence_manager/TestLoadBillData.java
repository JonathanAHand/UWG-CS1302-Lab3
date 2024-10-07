package edu.westga.cs1302.bill.test.model.bill_persistence_manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;

class TestLoadBillData {
	@Test
	void testWithSingleItem() throws IOException {
		try (PrintWriter writer = new PrintWriter(new FileWriter(BillPersistenceManager.DATA_FILE))) {
			writer.println("Mary,Shrimp,28.99");
		}
		Bill testBill = BillPersistenceManager.loadBillData();
		assertNotNull(testBill);
		assertEquals("Mary", testBill.getServerName());

		BillItem[] billItems = testBill.getItems();
		assertEquals(1, billItems.length);

		BillItem firstBillItem = billItems[0];
		assertEquals("Shrimp", firstBillItem.getName());
		assertEquals(28.99, firstBillItem.getAmount(), 0.01);
	}

	@Test
	void testWithMultipleItems() throws IOException {
		try (PrintWriter writer = new PrintWriter(new FileWriter(BillPersistenceManager.DATA_FILE))) {
			writer.println("Mary,Shrimp,28.99,Modelo,6.00");
		}
		Bill testBill = BillPersistenceManager.loadBillData();

		assertNotNull(testBill);
		assertEquals("Mary", testBill.getServerName());

		BillItem[] billItems = testBill.getItems();
		assertEquals(2, billItems.length);

		BillItem firstBillItem = billItems[0];
		assertEquals("Shrimp", firstBillItem.getName());
		assertEquals(28.99, firstBillItem.getAmount(), 0.01);

		BillItem secondBillItem = billItems[1];
		assertEquals("Modelo", secondBillItem.getName());
		assertEquals(6.00, secondBillItem.getAmount(), 0.01);
	}

	@Test
	void testWithServerAndNameButMissingAmount() throws IOException {
		try (PrintWriter writer = new PrintWriter(new FileWriter(BillPersistenceManager.DATA_FILE))) {
			writer.println("Mary,Shrimp");

			NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
				BillPersistenceManager.loadBillData();
			});

			assertNotNull(thrown.getMessage());
		}
	}

	@Test
	void testWithEmptyFile() throws IOException {
		try (PrintWriter writer = new PrintWriter(new FileWriter(BillPersistenceManager.DATA_FILE))) {
		}
		NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
			BillPersistenceManager.loadBillData();
		});

		assertNotNull(thrown.getMessage());
	}

	@Test
	void testWithWrongValueInAmount() throws IOException {
		try (PrintWriter writer = new PrintWriter(new FileWriter(BillPersistenceManager.DATA_FILE))) {
			writer.println(
					"Mary,Shrimp,Coconuts");
		}

		IOException thrown = assertThrows(IOException.class, () -> {
			BillPersistenceManager.loadBillData();
		});
		assertEquals(
				"Unable to read bill on line  : Mary,Shrimp,Coconuts",
				thrown.getMessage());
	}

	@Test
	void testWithMissingServerName() throws IOException {
		try (PrintWriter writer = new PrintWriter(new FileWriter(BillPersistenceManager.DATA_FILE))) {
			writer.println(",Shrimp,28.99");
		}
		IOException thrown = assertThrows(IOException.class, () -> {
			BillPersistenceManager.loadBillData();
		});

		assertEquals("The data file is empty.", thrown.getMessage());
	}

}
