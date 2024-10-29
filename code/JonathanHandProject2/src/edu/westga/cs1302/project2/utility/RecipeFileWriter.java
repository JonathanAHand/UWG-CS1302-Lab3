package edu.westga.cs1302.project2.utility;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Writes a Recipe to a file.
 * 
 * @author jhand1
 * @version 1.0
 */
public class RecipeFileWriter {
	
    /**
     * Writes the specified recipe to the given file.
     * 
     * @param recipe the recipe to write to the file.
     * @param filePath the path to the file where the recipe will be saved.
     * @throws IOException if an I/O error occurs.
     * @throws IllegalArgumentException if the recipe or filePath is null.
     */
    public static void writeRecipeToFile(Recipe recipe, String filePath) throws IOException {
        if (recipe == null) {
            throw new IllegalArgumentException("Recipe cannot be null.");
        }
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Recipe Name: " + recipe.getName() + System.lineSeparator());
            writer.write("Ingredients: " + System.lineSeparator());
     
            List<Ingredient> ingredients = recipe.getIngredients();

            for (Ingredient ingredient : ingredients) {
                writer.write(" - " + ingredient.getName() + " (" + ingredient.getType() + ")" + System.lineSeparator());
            }
        }
    }
    
    /**
     * Converts the given recipe to a string.
     * The first line contains the recipe name, and the second line
     * contains a comma-separated list of ingredient names.
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
