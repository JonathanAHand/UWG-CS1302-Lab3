package edu.westga.cs1302.project2.test.utility.recipe_file_writer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.utility.RecipeFileWriter;

class TestAppendRecipeToFile {

	private static final String TEST_FILE = "data.txt";

	@Test
	public void testWithValidIngredients() throws IOException {
		Recipe recipe = new Recipe("Chocolate Cake");
		recipe.addIngredient(new Ingredient("Flour", "Wheat"));
		recipe.addIngredient(new Ingredient("Eggs", "Meat"));
		recipe.addIngredient(new Ingredient("Sugar", "Spice"));

		File file = new File(TEST_FILE);
		if (file.exists()) {
			file.delete();
		}

		RecipeFileWriter.appendRecipeToFile(recipe, TEST_FILE);

		try (Scanner scanner = new Scanner(new FileReader(TEST_FILE))) {
			String firstLine = scanner.nextLine();
			String secondLine = scanner.nextLine();

			assertTrue(firstLine.contains("Chocolate Cake"));
			assertTrue(secondLine.contains("Flour, Wheat"));
			assertTrue(secondLine.contains("Eggs, Meat"));
			assertTrue(secondLine.contains("Sugar, Spice"));
		}

		file.delete();
	}

	@Test
	public void testWithNullRecipe() {
		assertThrows(IllegalArgumentException.class, () -> {
			RecipeFileWriter.appendRecipeToFile(null, TEST_FILE);
		});

		deleteTestFile();
	}

	@Test
	public void testWithEmptyFilePath() {
		Recipe recipe = new Recipe("Salad");

		assertThrows(IllegalArgumentException.class, () -> {
			RecipeFileWriter.appendRecipeToFile(recipe, "");
		});

		deleteTestFile();
	}

	@Test
	public void testWithNullFilePath() {
		Recipe recipe = new Recipe("Salad");

		assertThrows(IllegalArgumentException.class, () -> {
			RecipeFileWriter.appendRecipeToFile(recipe, null);
		});

		deleteTestFile();
	}

	@Test
    public void testAppendRecipeToFileWithNoIngredients() throws IOException {
        Recipe recipe = new Recipe("Plain Water");

        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }

        RecipeFileWriter.appendRecipeToFile(recipe, TEST_FILE);

        try (Scanner scanner = new Scanner(new FileReader(TEST_FILE))) {
            String firstLine = scanner.nextLine();
            String secondLine = scanner.nextLine();

            assertEquals("Plain Water", firstLine);
            assertEquals("", secondLine);
        }

        file.delete();
    }

	private void deleteTestFile() {
		File file = new File(TEST_FILE);
		if (file.exists()) {
			file.delete();
		}
	}

}
