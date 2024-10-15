package edu.westga.cs1302.bill.model;

import java.util.Comparator;

/**
 * Handles comparing costs to determine higher/lower value.
 * 
 * @author jHand1
 * @version 1.0
 */
public class BillDescendingCostComparator implements Comparator<BillItem> {
	
	/**
	 * Compares two items and determines which cost is higher.
	 * 
	 * @param o1 the first object
	 * @param o2 the second object 
	 * 
	 * @return the value for the cost determined 
	 */
	public int compare(BillItem o1, BillItem o2) {
		return Double.compare(o2.getAmount(), o1.getAmount());
	}
	
	@Override
	public String toString() {
		return "Ascending";
	}
}
