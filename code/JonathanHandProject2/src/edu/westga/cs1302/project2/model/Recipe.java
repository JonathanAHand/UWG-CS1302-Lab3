package edu.westga.cs1302.project2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores and manages the information for a recipe, includes a name and a list
 * of ingredients that make up the recipe.
 * 
 * @author jhand1
 * @version 1.0
 */

public class Recipe {
	private String name;
	private List<Ingredient> ingredients;

	/**
	 * Creates a new Recipe with the specified name.
	 *
	 * @param name the name of the recipe.
	 * @throws IllegalArgumentException if the name is null or empty
	 */
	public Recipe(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Recipe name cannot be null or empty.");
		}
		this.name = name;
		this.ingredients = new ArrayList<>();
	}

	/**
	 * Gets the list of ingredients in the selected recipe.
	 *
	 * @return the list of ingredients in the selected recipe.
	 */
	public List<Ingredient> getIngredients() {
		return new ArrayList<>(this.ingredients);
	}

	/**
	 * Gets the name of the selected recipe.
	 * 
	 * @return the name of the selected recipe.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the selected recipe.
	 * 
	 * @param name the name to set to the selected recipe.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Adds an ingredient to the recipe.
	 *
	 * @param ingredient the ingredient to add to the recipe.
	 * @throws IllegalArgumentException if the ingredient is null.
	 */
	public void addIngredient(Ingredient ingredient) {
		if (ingredient == null) {
			throw new IllegalArgumentException("Ingredient cannot be null.");
		}
		this.ingredients.add(ingredient);
	}

	/**
	 * Removes an ingredient from the recipe.
	 *
	 * @param ingredient the ingredient to remove from the recipe.
	 * @throws IllegalArgumentException if the ingredient is not listed in the
	 *                                  recipe.
	 */
	public void removeIngredient(Ingredient ingredient) {
		if (!this.ingredients.remove(ingredient)) {
			throw new IllegalArgumentException("Ingredient not found in the recipe.");
		}
	}
}
