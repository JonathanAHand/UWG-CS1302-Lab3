package edu.westga.cs1302.project2.model;

import java.util.List;

/**
 * Manages the operations related to Recipe.
 * 
 * @author jhand1
 * @version 1.0
 */
public class RecipeUtility {

	/**
	 * Converts the given recipe to a string. The first line contains the recipe
	 * name, and the second line contains a comma-separated list of ingredient
	 * names.
	 *
	 * @param recipe the recipe to convert.
	 * @return a formatted string representation of the recipe.
	 * @throws IllegalArgumentException if the recipe or its ingredients are null.
	 */
	public static String convertRecipeToString(Recipe recipe) {
		if (recipe == null || recipe.getIngredients() == null) {
			throw new IllegalArgumentException("Recipe or ingredients cannot be null.");
		}

		String result = recipe.getName() + System.lineSeparator();

		List<Ingredient> ingredients = recipe.getIngredients();
		String ingredientsLine = "";

		for (int index = 0; index < ingredients.size(); index++) {
			ingredientsLine += ingredients.get(index).getName();
			if (index < ingredients.size() - 1) {
				ingredientsLine += ", ";
			}
		}

		result += ingredientsLine;

		return result;
	}

}
