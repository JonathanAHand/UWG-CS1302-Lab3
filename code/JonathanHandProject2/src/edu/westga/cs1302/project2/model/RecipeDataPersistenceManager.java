package edu.westga.cs1302.project2.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Abstract class for managing recipe data persistence operations. Provides
 * method definitions for loading recipes from a file.
 * 
 * @author jhand1
 * @version 1.0
 */
public abstract class RecipeDataPersistenceManager {

	/**
	 * Loads all recipes from a file.
	 * 
	 * @return a list of recipes loaded from the file.
	 * @throws FileNotFoundException if the file does not exist.
	 * @throws IOException           if an I/O error occurs.
	 */
	public abstract List<Recipe> loadRecipes() throws FileNotFoundException, IOException;

	/**
	 * Loads recipes that contain a specified ingredient from a file.
	 * 
	 * @param ingredientName the name of the ingredient to search for
	 * @return a list of recipes that include the specified ingredient
	 * @throws FileNotFoundException if the file does not exist
	 * @throws IOException           if an I/O error occurs
	 */
	public abstract List<Recipe> loadRecipesWithIngredient(String ingredientName)
			throws FileNotFoundException, IOException;
}