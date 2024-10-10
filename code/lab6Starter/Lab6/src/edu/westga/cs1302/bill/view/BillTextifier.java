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
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param bill the bill to be summarized
	 * 
	 * @return a String summarizing the bill
	 */
	public static String getText(Bill bill) {
		if (bill == null) {
			throw new IllegalArgumentException("Must provide a valid Bill");
		}
		String text = "SERVER " + bill.getServerName() + System.lineSeparator();
		text += "ITEMS" + System.lineSeparator();
		for (BillItem item : bill.getItems()) {
			if (item != null) {
				text += item.getName() + " - " + BillTextifier.formatAsCurrency(item.getAmount())
						+ System.lineSeparator();
			}
		}

		text += System.lineSeparator();
		text += "SUBTOTAL - " + BillTextifier.formatAsCurrency(BillCalculator.getSubTotal(bill.getItems())) + System.lineSeparator();
		text += "TAX - " + BillTextifier.formatAsCurrency(BillCalculator.getTax(bill.getItems())) + System.lineSeparator();
		text += "TIP - " + BillTextifier.formatAsCurrency(BillCalculator.getTip(bill.getItems())) + System.lineSeparator();
		text += "TOTAL - " + BillTextifier.formatAsCurrency(BillCalculator.getTotal(bill.getItems()));

		return text;
	}

	private static String formatAsCurrency(double amount) {
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
