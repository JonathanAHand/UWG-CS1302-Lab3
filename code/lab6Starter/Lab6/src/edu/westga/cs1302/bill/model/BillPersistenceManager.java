package edu.westga.cs1302.bill.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** Supports saving and loading bill data,
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class BillPersistenceManager {
	
	public static final String DATA_FILE = "data.txt";
	
	/** Save the bill!
	 * 
	 * Writes all bill data to DATA_FILE
	 * 
	 * @precondition bill != null
	 * @postcondition none
	 * 
	 * @param bill the bill to save
	 * @throws IOException 
	 */
	public static void saveBillData(Bill bill) throws IOException, IllegalArgumentException {
		if (bill == null) {
			throw new IllegalArgumentException("Must provide a valid bill");
		}
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write(bill.getServerName() + System.lineSeparator());
			for (BillItem item : bill.getItems()) {
				writer.write(item.getName() + "," + item.getAmount() + System.lineSeparator());
			}
		}
		
	}

	/** Load the bill!
	 * 
	 * Reads from DATA_FILE
	 * File is assumed to use the same format as saveBillData
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the bill loaded 	if file is in valid format
	 * 		   a new bill 		if file is not in valid format or does not exist
	 */
	public static Bill loadBillData() {
		Bill bill = new Bill();
		File inputFile = new File(DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			bill.setServerName(reader.nextLine());
			while (reader.hasNextLine()) {
				String[] itemData = reader.nextLine().strip().split(",");
				bill.addItem(new BillItem(itemData[0], Double.parseDouble(itemData[1])));
			}
		} catch (Exception error) {
			bill = new Bill();
		}
		return bill;
	}

}
