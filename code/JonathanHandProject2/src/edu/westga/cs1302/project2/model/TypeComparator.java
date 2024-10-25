package edu.westga.cs1302.project2.model;

import java.util.Comparator;

/**
 * Comparator to compare two Ingredient objects based on their type.
 * 
 * @author jhand1
 * @version 1.0
 */
public class TypeComparator implements Comparator<Ingredient> {
	
	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		if (o1 == null || o2 == null) {
			throw new IllegalArgumentException("Ingredients cannot be null.");
		}
		return o1.getType().compareTo(o2.getType());
	}
}
