package edu.westga.cs1302.project2.test.utility.recipe_loader;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.utility.RecipeLoader;

public class TestLoadRecipesWithIngredient {

	private static final String TEST_FILE_PATH = "test_recipes.txt";

	@Test
	public void testWithNoIngredientInAnyRecipe() throws IOException {
		createTestFile("Cake" + System.lineSeparator() + "Flour, Ingredient" + System.lineSeparator() + "Bread"
				+ System.lineSeparator() + "Wheat, Ingredient");

		RecipeLoader recipeLoader = new RecipeLoader(TEST_FILE_PATH);
		List<Recipe> recipesWithSugar = recipeLoader.loadRecipesWithIngredient("Sugar");

		assertTrue(recipesWithSugar.isEmpty());

		deleteTestFile();
	}

	@Test
	public void testWithIngredientInEmptyFile() throws IOException {
		createTestFile("");

		RecipeLoader recipeLoader = new RecipeLoader(TEST_FILE_PATH);
		List<Recipe> recipes = recipeLoader.loadRecipesWithIngredient("Flour");

		assertTrue(recipes.isEmpty());

		deleteTestFile();
	}

	@Test
	public void testWithIngredientButFileDoesNotExist() throws IOException {
		File file = new File(TEST_FILE_PATH);
		if (file.exists()) {
			file.delete();
		}

		RecipeLoader recipeLoader = new RecipeLoader(TEST_FILE_PATH);
		List<Recipe> recipes = recipeLoader.loadRecipesWithIngredient("Flour");

		assertTrue(recipes.isEmpty());

		deleteTestFile();

	}

	private void createTestFile(String content) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE_PATH))) {
			writer.write(content);
		}
		System.out.println("Created test file with content:\n" + content);
	}

	private void deleteTestFile() {
		File file = new File(TEST_FILE_PATH);
		if (file.exists()) {
			file.delete();
		}
	}

}
