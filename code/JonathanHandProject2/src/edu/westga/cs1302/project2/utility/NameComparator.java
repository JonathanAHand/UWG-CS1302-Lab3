package edu.westga.cs1302.project2.utility;

import java.util.Comparator;

import edu.westga.cs1302.project2.model.Ingredient;

/**
 * Comparator to compare Ingredient objects based on their name.
 * 
 * @author jhand1
 * @version 1.0
 * 
 */
public class NameComparator implements Comparator<Ingredient> {

	/**
	 * Compares two Ingredient objects based on their name.
	 * 
	 * @param o1 the first Ingredient to compare, cannot be null.
	 * @param o2 the second Ingredient to compare, cannot be null.
	 * @return a negative integer, zero, or a positive integer if the name of the
	 *         first ingredient is less than, equal to, or greater than the name of
	 *         the second ingredient.
	 * @throws IllegalArgumentException if o1 is null or o2 is null.
	 */
	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		if (o1 == null || o2 == null) {
			throw new IllegalArgumentException("Ingredients cannot be null.");
		}
		return o1.getName().compareTo(o2.getName());
	}

	@Override
	public String toString() {
		return "Name";
	}

}
