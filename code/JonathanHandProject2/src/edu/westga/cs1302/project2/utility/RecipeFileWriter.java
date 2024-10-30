package edu.westga.cs1302.project2.utility;

import edu.westga.cs1302.project2.model.Recipe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Writes a Recipe to a file.
 * 
 * @author jhand1
 * @version 1.0
 */
public class RecipeFileWriter {

	/**
	 * Appends a recipe to the end of the file if it does not already exist.
	 * 
	 * @param recipe   the recipe to append to the file
	 * @param filePath the path to the file
	 * @throws IOException              if an I/O error occurs or the file cannot be
	 *                                  written
	 * @throws IllegalStateException    if the recipe already exists in the file
	 * @throws IllegalArgumentException if recipe or filePath is null or empty
	 */
	public static void appendRecipeToFile(Recipe recipe, String filePath) throws IOException {
		if (recipe == null || filePath == null || filePath.isEmpty()) {
			throw new IllegalArgumentException("Recipe and file path must not be null or empty.");
		}

		File file = new File(filePath);

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String recipeLine = scanner.nextLine();
				if (recipeLine.equals(recipe.getName())) {
					throw new IllegalStateException("Recipe already exists in the file.");
				}
			}
		}

		try (FileWriter writer = new FileWriter(file, true)) {
			writer.write(recipe.getName() + System.lineSeparator());

			for (int index = 0; index < recipe.getIngredients().size(); index++) {
				writer.write(recipe.getIngredients().get(index).getName());
				if (index < recipe.getIngredients().size() - 1) {
					writer.write(", ");
				}
			}
			writer.write(System.lineSeparator());
		}
	}

}