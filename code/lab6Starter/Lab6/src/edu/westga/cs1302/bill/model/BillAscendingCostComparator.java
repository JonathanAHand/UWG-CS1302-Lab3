package edu.westga.cs1302.bill.model;

import java.util.Comparator;

/**
 * A comparator for sorting BillItem objects according to their coat and in
 * ascending order.
 * 
 * @author jHand1
 * @version 1.0
 */
public class BillAscendingCostComparator implements Comparator<BillItem> {

	/**
	 * Compares the cost amount of two items and determines their order.
	 * 
	 * @param o1 the first BillItem object to compare.
	 * @param o2 the second BillItem object to compare.
	 * 
	 * @return a negative integer, zero, or positive integer based on whether the
	 *         amount for o1 is less than or equal to or greater than that of o2.
	 */
	public int compare(BillItem o1, BillItem o2) {
		return Double.compare(o1.getAmount(), o2.getAmount());
	}

	/**
	 * Returns a string representation of this comparator.
	 * 
	 * @return a string that describes the comparator which is "Ascending".
	 */
	@Override
	public String toString() {
		return "Ascending";
	}
}