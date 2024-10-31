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

	private String filePath;

	/**
	 * Creates a RecipeLoader for the specified file path.
	 * 
	 * @param filePath the path to the file containing recipes
	 */
	public RecipeLoader(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Loads all recipes from the file.
	 * 
	 * @return a list of recipes, or an empty list if the file is empty or not found
	 * @throws IOException if an I/O error occurs
	 */
	public List<Recipe> loadRecipes() throws IOException {
		List<Recipe> recipes = new ArrayList<>();
		File file = new File(this.filePath);

		if (!file.exists()) {
			return recipes;
		}

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String recipeName = scanner.nextLine();
				String[] ingredientData = scanner.nextLine().split(", ");
				Recipe recipe = new Recipe(recipeName);

				for (String ingredientEntry : ingredientData) {
					String[] parts = ingredientEntry.split("-");
					if (parts.length == 2) {
						recipe.addIngredient(new Ingredient(parts[0], parts[1]));
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
		List<Recipe> allRecipes = this.loadRecipes();

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
