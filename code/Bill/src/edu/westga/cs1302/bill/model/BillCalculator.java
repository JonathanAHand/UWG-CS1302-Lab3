package edu.westga.cs1302.bill.model;

/**
 * Provides static methods to handle the separate calculation of the BillItem
 * sub total, tip amount, tax amount, and the total combined amount for all
 * categories.
 * 
 * @author jhand1
 * @version 1.0
 * 
 */
public class BillCalculator {
	private static final double TAX_RATE = 0.1;
	private static final double TIP_RATE = 0.2;

	/**
	 * Calculates the sub total amount for an array of BillItem items.
	 *
	 * @param items the items in the BillItems array.
	 * @return the sub total amount of all the items.
	 */
	public static double getSubTotal(BillItem[] items) {
		if (items == null) {
			throw new IllegalArgumentException("List must contain at least one valid item.");
		}
		double subTotal = 0.0;
		for (BillItem item : items) {
			if (items != null) {
				subTotal += item.getAmount();
			}
		}
		return subTotal;
	}

	/**
	 * Calculates the tax amount for an array of BillItem items.
	 * 
	 * @param items the array of BillItem items.
	 * @return the tax amount on the items.
	 */
	public static double getTax(BillItem[] items) {
		double subTotal = getSubTotal(items);
		return subTotal * TAX_RATE;
	}

	/**
	 * Calculates the tip amount for an array of BillItem items.
	 * 
	 * @param items the array of BillItem items.
	 * @return the tip amount on the items.
	 */
	public static double getTip(BillItem[] items) {
		double subTotal = getSubTotal(items);
		return subTotal * TIP_RATE;
	}

	/**
	 * Calculates the total amount for an array of BillItem items.
	 * 
	 * @param items the array of BillItem items.
	 * @return the total combined amount for the items.
	 */
	public static double getTotal(BillItem[] items) {
		double subTotal = getSubTotal(items);
		double tax = getTax(items);
		double tip = getTip(items);
		return subTotal + tax + tip;

	}

}
