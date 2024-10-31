package edu.westga.cs1302.project2.utility;

import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeDataPersistenceManager;
import edu.westga.cs1302.project2.model.Ingredient;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of RecipeDataPersistenceManager for handling
 * file-based persistence.
 * 
 * @author jhand1
 * @version 1.0
 */
public class RecipeFilePersistenceManager extends RecipeDataPersistenceManager {

	private String filePath;

	/**
	 * Manages loading and saving recipe data from a specified file.
	 *
	 * @param filePath the path to the file where the recipes are stored.
	 */
	public RecipeFilePersistenceManager(String filePath) {
		if (filePath == null || filePath.isEmpty()) {
			throw new IllegalArgumentException("File path cannot be null or empty.");
		}
		this.filePath = filePath;
	}

	@Override
	public List<Recipe> loadRecipes() throws IOException {
		File file = new File(this.filePath);
		if (!file.exists()) {
			return new ArrayList<>();
		}
		List<Recipe> recipes = new ArrayList<>();

		return recipes;
	}

	@Override
	public List<Recipe> loadRecipesWithIngredient(String ingredientName) throws IOException {
		List<Recipe> allRecipes = this.loadRecipes();
		List<Recipe> sameRecipes = new ArrayList<>();

		for (Recipe recipe : allRecipes) {
			if (this.recipeHasIngredient(recipe, ingredientName)) {
				sameRecipes.add(recipe);
			}
		}

		return sameRecipes;
	}

	private boolean recipeHasIngredient(Recipe recipe, String ingredientName) {
		for (Ingredient ingredient : recipe.getIngredients()) {
			if (ingredient.getName().equals(ingredientName)) {
				return true;
			}
		}
		return false;
	}

}