package model;

import javafx.collections.ObservableList;

/**
 * Represents a food item for the pantry with a name, type, and quantity. Each
 * item has methods for manipulation of quantity.
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
		this.quantity = 1;
	}

	/**
	 * Gets the name of the food item.
	 * 
	 * @return the name of the food item.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the type of the food item.
	 * 
	 * @return the type of the food item.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Gets the quantity of the food item.
	 * 
	 * @return the quantity of the food item.
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * Sets the quantity of the food item in the pantry. Must be 0 or greater or
	 * throws Exception.
	 * 
	 * @param quantity the quantity of the food item to be added to the pantry.
	 */
	public void setQuantity(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity can't be negative.");
		}
		this.quantity = quantity;
	}

	/**
	 * Increases the quantity of the food item in the pantry by 1 unit.
	 */
	public void incrementQuantity() {
		this.quantity++;
	}

	/**
	 * Decreases the quantity of the food item by 1 unit. Throws Exception if value
	 * is less than 0 minimum.
	 */
	public void decrementQuantity() {
		if (this.quantity > 0) {
			this.quantity--;
		} else {
			throw new IllegalStateException("Quantity can't be less than 0.");
		}

	}

	/**
	 * Adds food item
	 * 
	 * @param foodList The list of food items in the Pantry.
	 * @param newFood  The new food item to be added to the Pantry.
	 */
	public static void addFood(ObservableList<Food> foodList, Food newFood) {
		boolean wasFound = false;
		for (Food food : foodList) {
			if (food.getName().equals(newFood.getName()) && food.getType().equals(newFood.getType())) {
				food.incrementQuantity();
				wasFound = true;
				break;
			}
		}

		if (!wasFound) {
			foodList.add(newFood);
		}
	}

	/**
	 * Returns the string representation of the food item and displays it in
	 * ListView.
	 */
	@Override
	public String toString() {
		return this.name + " – " + this.quantity;
	}
}
