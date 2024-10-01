package edu.westga.cs1302.bill.test.model.bill_persistence_manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;

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
	void testValidBillIsSaved() throws IOException {
		Bill bill = new Bill();
		
		bill.setServerName("Mary");
		
		BillItem firstItem = new BillItem("Shrimp", 28.99);
		BillItem secondItem = new BillItem("Modelo", 6.00);
		bill.addItem(firstItem);
		bill.addItem(secondItem);
		
		BillPersistenceManager.saveBillData(bill);
		
	
	}
	
}
