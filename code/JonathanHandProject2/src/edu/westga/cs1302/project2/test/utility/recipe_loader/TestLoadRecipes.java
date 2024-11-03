package edu.westga.cs1302.project2.test.utility.recipe_loader;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.utility.RecipeLoader;

public class TestLoadRecipes {
	private static final String TEST_FILE_PATH = "test_recipes.txt";

	@Test
	public void testWithValidFile() throws IOException {
		createTestFile("Cake" + System.lineSeparator() + "Flour, Ingredient" + System.lineSeparator() + "Bread"
				+ System.lineSeparator() + "Wheat, Ingredient");

		List<Recipe> recipes = RecipeLoader.loadRecipes(TEST_FILE_PATH);

		assertEquals(2, recipes.size());
		assertEquals("Cake", recipes.get(0).getName());
		assertEquals("Bread", recipes.get(1).getName());
		assertEquals("Flour", recipes.get(0).getIngredients().get(0).getName());
		assertEquals("Ingredient", recipes.get(0).getIngredients().get(0).getType());
		assertEquals("Wheat", recipes.get(1).getIngredients().get(0).getName());
		assertEquals("Ingredient", recipes.get(1).getIngredients().get(0).getType());

		deleteTestFile();
	}

	@Test
	public void testWithEmptyFile() throws IOException {
		createTestFile("");

		List<Recipe> recipes = RecipeLoader.loadRecipes(TEST_FILE_PATH);

		assertTrue(recipes.isEmpty());

		deleteTestFile();
	}

	@Test
	public void testWithNoFile() throws IOException {
		File file = new File(TEST_FILE_PATH);
		if (file.exists()) {
			file.delete();
		}

		List<Recipe> recipes = RecipeLoader.loadRecipes(TEST_FILE_PATH);

		assertTrue(recipes.isEmpty());
	}

	@Test
	public void testWithBadFormatData() throws IOException {
		createTestFile("Pizza" + System.lineSeparator() + "Tomato");

		List<Recipe> recipes = RecipeLoader.loadRecipes(TEST_FILE_PATH);

		assertEquals(1, recipes.size());
		assertEquals("Pizza", recipes.get(0).getName());
		assertTrue(recipes.get(0).getIngredients().isEmpty());

		deleteTestFile();
	}

	@Test
	public void testWithEmptyRecipeNameAndIngredients() throws IOException {
		createTestFile(System.lineSeparator() + System.lineSeparator() + System.lineSeparator());

		List<Recipe> recipes = RecipeLoader.loadRecipes(TEST_FILE_PATH);

		assertTrue(recipes.isEmpty());

		deleteTestFile();
	}

	@Test
	public void testWithExtraWhitespace() throws IOException {
		createTestFile("   Soup  " + System.lineSeparator() + "  Water , Liquid   ");

		List<Recipe> recipes = RecipeLoader.loadRecipes(TEST_FILE_PATH);

		assertEquals(1, recipes.size());
		assertEquals("Soup", recipes.get(0).getName());
		assertEquals("Water", recipes.get(0).getIngredients().get(0).getName());
		assertEquals("Liquid", recipes.get(0).getIngredients().get(0).getType());

		deleteTestFile();
	}

	private void createTestFile(String recipeContent) throws IOException {
		FileWriter writer = new FileWriter(TEST_FILE_PATH);
		writer.write(recipeContent);
		writer.close();
	}

	private void deleteTestFile() {
		File file = new File(TEST_FILE_PATH);
		if (file.exists()) {
			file.delete();
		}
	}
}
