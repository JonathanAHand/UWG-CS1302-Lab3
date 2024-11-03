package edu.westga.cs1302.project2.utility;

import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.Ingredient;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages loading recipes from a file. Provides methods to load all recipes or
 * recipes containing a specific ingredient.
 * 
 * @author jhand1
 * @version 1.0
 */
public class RecipeLoader {

	/**
	 * Creates a RecipeLoader for the specified file path.
	 * 
	 * @param filePath the path to the file containing recipes
	 */
	public RecipeLoader(String filePath) {
	}

	/**
	 * Loads all recipes from the file.
	 * 
	 * @param filePath the path to the file.
	 * @return a list of recipes, or an empty list if the file is empty or not found
	 * @throws IOException if an I/O error occurs
	 */
	public static List<Recipe> loadRecipes(String filePath) throws IOException {
		List<Recipe> recipes = new ArrayList<>();
		File file = new File(filePath);

		if (!file.exists()) {
			return recipes;
		}

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String recipeName = scanner.nextLine().trim();

				if (recipeName.isEmpty() || !scanner.hasNextLine()) {
					continue;
				}

				String ingredientsLine = scanner.nextLine().trim();
				String[] ingredients = ingredientsLine.split(", ");
				Recipe recipe = new Recipe(recipeName);

				for (int index = 0; index < ingredients.length - 1; index += 2) {
					String ingredientName = ingredients[index].trim();
					String ingredientType = ingredients[index + 1].trim();

					if (!ingredientName.isEmpty()) {
						recipe.addIngredient(new Ingredient(ingredientName, ingredientType));
					}
				}

				recipes.add(recipe);
			}
		}
		return recipes;

	}

	/**
	 * Loads recipes containing the specified ingredient.
	 * 
	 * @param ingredientName the ingredient name to filter recipes by
	 * @return a list of recipes with the specified ingredient, or an empty list if
	 *         no matches
	 * @throws IOException if an I/O error occurs
	 */
	public List<Recipe> loadRecipesWithIngredient(String ingredientName) throws IOException {
		List<Recipe> currRecipes = new ArrayList<>();
		List<Recipe> allRecipes = RecipeLoader.loadRecipes(ingredientName);

		for (Recipe recipe : allRecipes) {
			for (Ingredient ingredient : recipe.getIngredients()) {
				if (ingredient.getName().equals(ingredientName)) {
					currRecipes.add(recipe);
					break;
				}
			}
		}
		return currRecipes;
	}

}
