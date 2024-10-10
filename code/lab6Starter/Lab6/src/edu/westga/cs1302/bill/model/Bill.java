package edu.westga.cs1302.bill.model;

import java.util.ArrayList;

/**
 * Manages a set of BillItems.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class Bill {
	public static final int MAX_NUMBER_OF_ITEMS = 3;
	private ArrayList<BillItem> items;
	private int size;
	private String serverName;

	/**
	 * Create a new empty Bill with no server set
	 */
	public Bill() {
		this.serverName = "No Server Set";
		this.items = new ArrayList<BillItem>();
	}
	
	/** Return the server name for the bill
	 * 
	 * @return the server name for the bill
	 */
	public String getServerName() {
		return this.serverName;
	}

	/** Sets the server name for the bill
	 * 
	 * @precondition name != null && !name.isEmpty()
	 * @postcondition getServerName() == name
	 * 
	 * @param name the name of the server for the bill
	 */
	public void setServerName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name must not be null.");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name must not be empty.");
		}
		this.serverName = name;
	}

	/**
	 * Adds the item to the bill
	 * 
	 * @precondition item != null && size < 5
	 * @postcondition item is added to the list of items in the bill
	 * 
	 * @param item the item to be added to the bill
	 */
	public void addItem(BillItem item) {
		if (item == null) {
			throw new IllegalArgumentException("item must not be null.");
		}
		if (this.size == Bill.MAX_NUMBER_OF_ITEMS) {
			throw new IllegalStateException("bill items list is full");
		}
		this.items.add(item);
	}
	
	/** Return the number of items in the bill
	 * 
	 * @return the number of items in the bill
	 */
	public int getSize() {
		return this.items.size();
	}

	/**
	 * Return the items in the bill
	 * 
	 * @return the items in the bill
	 */
	public BillItem[] getItems() {
		return this.items.toArray(new BillItem[this.items.size()]);
	}

}
