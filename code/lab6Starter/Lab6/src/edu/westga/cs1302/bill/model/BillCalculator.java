package edu.westga.cs1302.bill.model;

/** Calculates amounts for bills
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class BillCalculator {

	private static final double TIP_PERCENT = 0.2;
	private static final double TAX_PERCENT = 0.1;

	/**
	 * Return the subtotal for the bill
	 * 
	 * @precondition items != null
	 * 
	 * @param items the set of items to generate the subtotal for
	 * 
	 * @return the subtotal for the bill
	 * 
	 * @throws IllegalArgumentException if precondition is violated
	 */
	public static double getSubTotal(BillItem[] items) throws IllegalArgumentException {
		if (items == null) {
			throw new IllegalArgumentException("items must not be null");
		}
		double subTotal = 0.0;
		for (BillItem item : items) {
			if (item != null) {
				subTotal += item.getAmount();
			}
		}
		return subTotal;
	}

	/**
	 * Return the tax for the bill
	 * 
	 * @precondition items != null
	 * 
	 * @param items the set of items to generate the subtotal for
	 * 
	 * @return the tax for the bill
	 */
	public static double getTax(BillItem[] items) {
		return BillCalculator.getSubTotal(items) * TAX_PERCENT;
	}

	/**
	 * Return the tip for the bill
	 * 
	 * @precondition items != null
	 * 
	 * @param items the set of items to generate the subtotal for
	 * 
	 * @return the tip for the bill
	 */
	public static double getTip(BillItem[] items) {
		return BillCalculator.getSubTotal(items) * TIP_PERCENT;
	}

	/**
	 * Return the total for the bill
	 * 
	 * @precondition items != null
	 * 
	 * @param items the set of items to generate the subtotal for
	 * 
	 * @return the total for the bill
	 */
	public static double getTotal(BillItem[] items) {
		return BillCalculator.getSubTotal(items) + BillCalculator.getTax(items) + BillCalculator.getTip(items);
	}

}
