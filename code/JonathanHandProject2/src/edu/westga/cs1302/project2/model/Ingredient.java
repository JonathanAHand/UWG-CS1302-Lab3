package edu.westga.cs1302.project2.model;

/** Store information for a single Ingredient
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class Ingredient {
	private String name;
	private String type;
	
	/** Create a new ingredient with the specified name and type
	 * 
	 * @precondition name != null && !name.isEmpty() &&
	 * 				 type != null && !type.isEmpty()
	 * @postcondition getName() == name &&
	 * 				  getType() == type
	 * 
	 * @param name the name of the ingredient
	 * @param type the type for the ingredient
	 */
	public Ingredient(String name, String type) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Must provide a valid name.");
		}
		if (type == null || type.isEmpty()) {
			throw new IllegalArgumentException("Must provide a valid type.");
		}
		this.name = name;
		this.type = type;
	}
	
	/** Return the name of the ingredient
	 * 
	 * @return the name of the ingredient
	 */
	public String getName() {
		return this.name;
	}
	
	/** Return the type of the ingredient
	 * 
	 * @return the type of the ingredient
	 */
	public String getType() {
		return this.type;
	}
	
	@Override
	public String toString() {
		return this.name + "-" + this.type;
	}
	
}
