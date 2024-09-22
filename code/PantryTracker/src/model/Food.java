package model;

/**
 * Represents a food item for the pantry with a name, type, and quantity.
 * 
 * @author jhand1
 * @version 1.0
 */
public class Food {
	private final String name;
	private final String type;
	private int quantity;

	/**
	 * Creates a new Food object with given name and type and sets default quantity
	 * to 0 to increment.
	 * 
	 * @preconditions name != null && name !isEmpty() && type != null && type
	 *                !isEmpty().
	 * @postconditions getName() == name && getType() == type && getQuanity() =
	 *                 quantity.
	 * @param name the name of the food in the pantry.
	 * @param type the type of the food in the pantry.
	 */
	public Food(String name, String type) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name must be neither null nor empty.");
		}
		if (type == null || type.isEmpty()) {
			throw new IllegalArgumentException("Type must be neither null nor empty.");
		}
		this.name = name;
		this.type = type;
		this.quantity = 0;
	}

	/**
	 * Gets the name of the food item.
	 * 
	 * @return name of the food item.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the type of the food item.
	 * 
	 * @return the type of the food item.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets the quantity of the food item.
	 * 
	 * @return the quantity of the food item.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity of the food item. Must be 0 or greater or throws.
	 * 
	 * @param quantity the quantity of the food item.
	 */
	public void setQuantity(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity can't be negative.");
		}
		this.quantity = quantity;
	}

	/**
	 * Returns the string representation of the food item and displays it in
	 * ListView.
	 * 
	 */
	@Override
	public String toString() {
		return name + " – " + quantity;
	}
}
