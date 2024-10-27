package edu.westga.cs1302.project2.utility;

import java.util.Comparator;
import java.util.List;
import edu.westga.cs1302.project2.model.Ingredient;

/**
 * Provides utility methods for logical operations relating to the management of
 * pantry ingredients.
 * 
 * @author jhand1
 * @version 1.0
 */

public class PantryUtility {
	/**
	 * Sorts the given list of ingredients using the chosen comparator.
	 *
	 * @param ingredients the list of ingredients to sort
	 * @param comparator  the comparator to determine the order of sorting
	 * @throws IllegalArgumentException if the ingredients list or comparator is
	 *                                  null
	 */
	public static void sortIngredients(List<Ingredient> ingredients, Comparator<Ingredient> comparator) {
		if (ingredients == null || comparator == null) {
			throw new IllegalArgumentException("Ingredients list and comparator cannot be null.");
		}
		ingredients.sort(comparator);
	}

}
