package edu.westga.cs1302.project2.test.utility.recipe_file_writer;

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

class TestRecipeFileWriter {

	private static final String TEST_FILE = "data.txt";

	@Test
	public void testWithValidRecipe() throws IOException {
		Recipe recipe = new Recipe("Pasta");
		recipe.addIngredient(new Ingredient("Noodles", "Grain"));
		recipe.addIngredient(new Ingredient("Tomato Sauce", "Sauce"));

		deleteTestFile();

		RecipeFileWriter.appendRecipeToFile(recipe, TEST_FILE);

		try (Scanner scanner = new Scanner(new FileReader(TEST_FILE))) {
			String firstLine = scanner.nextLine();
			String secondLine = scanner.nextLine();
			String thirdLine = scanner.nextLine();

			assertTrue(firstLine.contains("Pasta"));
			assertTrue(secondLine.contains("Ingredients:"));
			assertTrue(thirdLine.contains("Noodles (Grain)"));
		}

		deleteTestFile();
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
	public void testWithNoIngredients() throws IOException {
		Recipe recipe = new Recipe("Plain Water");

		deleteTestFile();

		RecipeFileWriter.appendRecipeToFile(recipe, TEST_FILE);

		try (Scanner scanner = new Scanner(new FileReader(TEST_FILE))) {
			String firstLine = scanner.nextLine();
			String secondLine = scanner.nextLine();

			assertTrue(firstLine.contains("Plain Water"));
			assertTrue(secondLine.contains("Ingredients:"));
		}

		deleteTestFile();
	}

	private void deleteTestFile() {
		File file = new File(TEST_FILE);
		if (file.exists()) {
			file.delete();
		}
	}

}
