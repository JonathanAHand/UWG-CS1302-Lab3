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
    
}
