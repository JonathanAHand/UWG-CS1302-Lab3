package edu.westga.cs1302.project2.test.utility.recipe_file_writer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeUtility;

class TestConvertRecipeToString {

	@Test
	public void testWithNullRecipe() {
		assertThrows(IllegalArgumentException.class, () -> {
			RecipeUtility.convertRecipeToString(null);
		});
	}

	@Test
	public void testWithNullIngredientsList() {
		Recipe recipe = new Recipe("Test Recipe") {
			@Override
			public List<Ingredient> getIngredients() {
				return null;
			}
		};

		assertThrows(IllegalArgumentException.class, () -> {
			RecipeUtility.convertRecipeToString(recipe);
		});
	}

	@Test
	public void testWithMultipleIngredients() {
		Recipe recipe = new Recipe("Burrito");
		recipe.addIngredient(new Ingredient("Tortilla", "Bread"));
		recipe.addIngredient(new Ingredient("Chicken", "Meat"));
		recipe.addIngredient(new Ingredient("Salsa", "Vegetable"));

		String expected = "Burrito" + System.lineSeparator() + "Tortilla, Chicken, Salsa";
		String actual = RecipeUtility.convertRecipeToString(recipe);

		assertEquals(expected, actual);
	}

	@Test
	public void testWithSingleIngredient() {
		Recipe recipe = new Recipe("Steak and Eggs");
		recipe.addIngredient(new Ingredient("Eggs", "Meat"));

		String expected = "Steak and Eggs" + System.lineSeparator() + "Eggs";
		String actual = RecipeUtility.convertRecipeToString(recipe);

		assertEquals(expected, actual);
	}

}
