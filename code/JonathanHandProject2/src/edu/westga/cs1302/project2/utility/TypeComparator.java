package edu.westga.cs1302.project2.utility;

import java.util.Comparator;

import edu.westga.cs1302.project2.model.Ingredient;

/**
 * Comparator to compare Ingredient objects based on their type.
 * 
 * @author jhand1
 * @version 1.0
 * 
 */
public class TypeComparator implements Comparator<Ingredient> {

	/**
	 * Compares two Ingredient objects based on their type.
	 * 
	 * @param o1 the first Ingredient to compare, cannot be null.
	 * @param o2 the second Ingredient to compare, cannot be null.
	 * @return a negative integer if the type of o1 is less than the type of o2, or
	 *         zero if the types are the same, or a positive integer if the type of
	 *         o1 is greater than the type of o2.
	 * @throws IllegalArgumentException if either o1 or o2 is null.
	 * 
	 */
	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		if (o1 == null || o2 == null) {
			throw new IllegalArgumentException("Ingredients cannot be null.");
		}
		return o1.getType().compareTo(o2.getType());
	}
	
	@Override
	public String toString() {
		return "Type";
	}
}
