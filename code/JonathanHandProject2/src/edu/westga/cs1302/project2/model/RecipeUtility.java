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
			Ingredient ingredient = ingredients.get(index);
			ingredientsLine += ingredient.getName() + ", " + ingredient.getType();
			if (index < ingredients.size() - 1) {
				ingredientsLine += ", ";
			}
		}

		result += ingredientsLine;

		return result;
	}

	/**
	 * Converts a list of recipes to a string representation with each recipe
	 * separated by a blank line.
	 * 
	 * @param recipes the list of recipes to convert.
	 * @return a formatted string representation of the list of recipes.
	 * @throws IllegalArgumentException if the recipes list is null.
	 */
	public static String recipeListConverter(List<Recipe> recipes) {
		if (recipes == null) {
			throw new IllegalArgumentException("Recipes list cannot be null.");
		}

		String result = "";

		for (int index = 0; index < recipes.size(); index++) {
			result += convertRecipeToString(recipes.get(index));
			if (index < recipes.size() - 1) {
				result += System.lineSeparator() + System.lineSeparator();
			}
		}

		return result;
	}

}
