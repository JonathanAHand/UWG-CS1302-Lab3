package edu.westga.cs1302.project2.test.model.recipe;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.Ingredient;

public class TestConstructor {
	@Test
	public void testWithEmptyName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Recipe("");
		});
	}

	@Test
	public void testWithNullName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Recipe(null);
		});
	}

	@Test
	public void testAddsSingleIngredient() {
		Recipe recipe = new Recipe("Spaghetti");
		Ingredient ingredient = new Ingredient("Tomato", "Vegetable");

		recipe.addIngredient(ingredient);
		assertEquals(1, recipe.getIngredients().size());
		assertTrue(recipe.getIngredients().contains(ingredient));
	}

	@Test
	public void testAddsMultipleIngredients() {
		Recipe recipe = new Recipe("Spaghetti");
		Ingredient ingredient1 = new Ingredient("Tomato", "Vegetable");
		Ingredient ingredient2 = new Ingredient("Mushrooms", "Vegetable");
		Ingredient ingredient3 = new Ingredient("Beef", "Meat");

		recipe.addIngredient(ingredient1);
		recipe.addIngredient(ingredient2);
		recipe.addIngredient(ingredient3);

		assertEquals(3, recipe.getIngredients().size());
		assertTrue(recipe.getIngredients().contains(ingredient1));
		assertTrue(recipe.getIngredients().contains(ingredient2));
		assertTrue(recipe.getIngredients().contains(ingredient3));
	}

}
