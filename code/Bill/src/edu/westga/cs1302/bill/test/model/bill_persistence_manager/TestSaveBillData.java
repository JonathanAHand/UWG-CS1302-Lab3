package edu.westga.cs1302.bill.test.model.bill_persistence_manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;

class TestSaveBillData {

	@Test
	void testWithNullBill() {
		try {
			BillPersistenceManager.saveBillData(null);
		} catch (IllegalArgumentException errorArg) {
			assertEquals("Bill cannot be null.", errorArg.getMessage());
		} catch (IOException error) {
			fail("IOException should not throw.");
		}
	}

	@Test
	void testValidBillWithMultipleItemsIsSaved() throws IOException {
		Bill bill = new Bill();

		bill.setServerName("Mary");

		BillItem firstItem = new BillItem("Shrimp", 28.99);
		BillItem secondItem = new BillItem("Modelo", 6.00);
		bill.addItem(firstItem);
		bill.addItem(secondItem);

		BillPersistenceManager.saveBillData(bill);
		java.io.File dataFile = new java.io.File(BillPersistenceManager.DATA_FILE);
		Scanner fileScanner = new Scanner(dataFile);

		if (fileScanner.hasNextLine()) {
			String dataLine = fileScanner.nextLine();
			String[] parts = dataLine.split(",");

			assertEquals("Mary", parts[0]);
			assertEquals("Shrimp", parts[1]);
			assertEquals("28.99", parts[2]);
			assertEquals("Modelo", parts[3]);
			assertEquals("6.0", parts[4]);
		} else {
			fail("File contains no data.");
		}

		fileScanner.close();
	}

	@Test
	void testBillWithOnlyServerSaves() throws IOException {
		Bill bill = new Bill();
		bill.setServerName("Mark");
		BillPersistenceManager.saveBillData(bill);
		java.io.File dataFile = new java.io.File(BillPersistenceManager.DATA_FILE);
		Scanner fileScanner = new Scanner(dataFile);

		if (fileScanner.hasNextLine()) {
			String dataLine = fileScanner.nextLine();
			String[] parts = dataLine.split(",");

			assertEquals("Mark", parts[0]);
			assertEquals(1, parts.length);
		} else {
			fail("File contains no data.");
		}

		fileScanner.close();
	}

	@Test
	void testNoItemsOrServer() throws IOException {
		Bill bill = new Bill();
		BillPersistenceManager.saveBillData(bill);

		File dataFile = new File(BillPersistenceManager.DATA_FILE);
		try (Scanner fileScanner = new Scanner(dataFile)) {
			assertTrue(fileScanner.hasNextLine(), "No Server Set,");
		}
	}

	@Test
	void testSingleItemSaved() throws IOException {
		Bill bill = new Bill();

		bill.setServerName("Mary");

		BillItem firstItem = new BillItem("Shrimp", 28.99);
		bill.addItem(firstItem);

		BillPersistenceManager.saveBillData(bill);
		java.io.File dataFile = new java.io.File(BillPersistenceManager.DATA_FILE);
		Scanner fileScanner = new Scanner(dataFile);

		if (fileScanner.hasNextLine()) {
			String dataLine = fileScanner.nextLine();
			String[] parts = dataLine.split(",");

			assertEquals("Mary", parts[0]);
			assertEquals("Shrimp", parts[1]);
			assertEquals("28.99", parts[2]);

		} else {
			fail("File contains no data.");
		}

		fileScanner.close();
	}

}
