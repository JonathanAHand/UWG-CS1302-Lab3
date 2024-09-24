package utility;

import javafx.collections.ObservableList;
import model.Food;

/**
 * Utility class for Pantry-related operations.
 * 
 * @author jhand1
 * @version 1.0
 */
public class PantryUtility {

	/**
	 * Calculates the total quantity of all the food items listed in the Pantry.
	 * 
	 * @param foodList the list of all food items in the Pantry.
	 * @return the sum of the quantity of all the food items in the Pantry list.
	 */
	public static int calculateTotalQuantity(ObservableList<Food> foodList) {
		int totalQuantity = 0;
		for (Food foodItems : foodList) {
			totalQuantity += foodItems.getQuantity();
		}
		return totalQuantity;
	}
}
