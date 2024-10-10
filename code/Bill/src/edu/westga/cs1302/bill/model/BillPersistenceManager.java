package edu.westga.cs1302.bill.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Supports saving and loading bill data,
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class BillPersistenceManager {

	public static final String DATA_FILE = "data.txt";

	/**
	 * Save the bill!
	 * 
	 * Writes all bill data to DATA_FILE
	 * 
	 * @precondition bill != null
	 * @postcondition none
	 * 
	 * @param bill the bill to save
	 * @throws IOException
	 */
	public static void saveBillData(Bill bill) throws IOException {
		if (bill == null) {
			throw new IllegalArgumentException("Bill cannot be null.");
		}

		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write(bill.getServerName() + ",");

			for (BillItem item : bill.getItems()) {
				if (item != null) {
				writer.write(item.getName() + "," + item.getAmount() + ",");
				}
			}
		}
	}

	/**
	 * Load the bill!
	 * 
	 * Reads from DATA_FILE File is assumed to use the same format as saveBillData
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the bill loaded
	 */
	public static Bill loadBillData() throws FileNotFoundException, IOException {
		File inputFile = new File(DATA_FILE);
		/*if (inputFile.exists() || inputFile.length() == 0) {
			return new Bill();
		}*/
		Bill bill = new Bill();
		try (Scanner reader = new Scanner(inputFile)) {

			String baseLine = reader.nextLine();
			String strippedLine = baseLine.strip();
			String[] parts = strippedLine.split(",");

			try {
				String serverName = parts[0];
				bill.setServerName(serverName);
				for (int index = 1; index < parts.length; index += 2) {
					String itemName = parts[index];
					double itemAmount = Double.parseDouble(parts[index + 1]);
					bill.addItem(new BillItem(itemName, itemAmount));
				}
			} catch (NumberFormatException numError) {
				throw new IOException("Unable to read bill on line " + " : " + strippedLine);
			} catch (IllegalArgumentException studentDataError) {
				throw new IOException("Unable to create billitem, bad values on line " + " : " + strippedLine);
			} catch (ArrayIndexOutOfBoundsException boundsError) {
				throw new IOException("Expected item name and item amount in pairs and only had name.");
			}

		}
		return bill;
	}

}
