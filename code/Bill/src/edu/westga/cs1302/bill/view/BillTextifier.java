package edu.westga.cs1302.bill.view;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;

/**
 * Generates text to summarize a Bill
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class BillTextifier {

	/**
	 * Return a String summarizing the bill.
	 * 
	 * @param bill the bill object containing the items to summarize.
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return a String summarizing the bill
	 */
	public static String getText(Bill bill) {
		if (bill == null) {
			throw new IllegalArgumentException("Must provide a valid Bill");
		}

		BillItem[] items = bill.getItems();

		String text = "ITEMS" + System.lineSeparator();
		for (BillItem item : bill.getItems()) {
			if (item != null) {
				text += item.getName() + " - " + BillTextifier.convertToCurrency(item.getAmount())
						+ System.lineSeparator();
			}
		}

		text += System.lineSeparator();
		text += "SUBTOTAL - " + BillTextifier.convertToCurrency(BillCalculator.getSubTotal(items))
				+ System.lineSeparator();
		text += "TAX - " + BillTextifier.convertToCurrency(BillCalculator.getTax(items)) + System.lineSeparator();
		text += "TIP - " + BillTextifier.convertToCurrency(BillCalculator.getTip(items)) + System.lineSeparator();
		text += "TOTAL - " + BillTextifier.convertToCurrency(BillCalculator.getTotal(items));

		return text;
	}

	private static String convertToCurrency(double amount) {
		String result = "$";
		int number = ((int) (amount * 100));
		int dollars = number / 100;
		result += dollars;
		result += ".";
		int pennies = number - (dollars * 100);
		result += pennies;
		if (pennies == 0) {
			result += "0";
		}
		return result;
	}

}
