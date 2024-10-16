package edu.westga.cs1302.bill.model;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
* Supports saving and loading bill data,
* 
* @author CS 1302
* @version Fall 2024
*/
public abstract class BillPersistenceManager {

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
	public abstract void saveBillData(Bill bill) throws IOException, IllegalArgumentException;

	/**
	 * Load the bill!
	 * 
	 * Reads from DATA_FILE File is assumed to use the same format as saveBillData
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the bill loaded if file is in valid format a new bill if file is not
	 *         in valid format or does not exist
	 */
	public abstract Bill loadBillData() throws IOException, FileNotFoundException;

}
