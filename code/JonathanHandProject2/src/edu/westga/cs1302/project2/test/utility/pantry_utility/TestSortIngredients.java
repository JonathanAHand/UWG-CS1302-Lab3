package edu.westga.cs1302.project2.test.utility.pantry_utility;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.utility.PantryUtility;

public class TestSortIngredients {
	@Test
	public void testWithValidComparator() {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Carrot", "Vegetable"));
		ingredients.add(new Ingredient("Apple", "Fruit"));
		ingredients.add(new Ingredient("Banana", "Fruit"));

		PantryUtility.sortIngredients(ingredients, Comparator.comparing(Ingredient::getName));

		assertEquals("Apple", ingredients.get(0).getName());
		assertEquals("Banana", ingredients.get(1).getName());
		assertEquals("Carrot", ingredients.get(2).getName());
	}

	@Test
	public void testWithTypeComparator() {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Chicken", "Meat"));
		ingredients.add(new Ingredient("Carrot", "Vegetable"));
		ingredients.add(new Ingredient("Apple", "Fruit"));

		PantryUtility.sortIngredients(ingredients, Comparator.comparing(Ingredient::getType));

		assertEquals("Apple", ingredients.get(0).getName());
		assertEquals("Chicken", ingredients.get(1).getName());
		assertEquals("Carrot", ingredients.get(2).getName());
	}

	@Test
	public void testSortIngredientsWithEmptyList() {
		List<Ingredient> ingredients = new ArrayList<>();

		PantryUtility.sortIngredients(ingredients, Comparator.comparing(Ingredient::getName));

		assertTrue(ingredients.isEmpty());
	}

	@Test
	public void testWithSingleIngredient() {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Egg", "Protein"));

		PantryUtility.sortIngredients(ingredients, Comparator.comparing(Ingredient::getName));

		assertEquals("Egg", ingredients.get(0).getName());
	}

	@Test
	public void testWithDuplicateNames() {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Apple", "Fruit"));
		ingredients.add(new Ingredient("Apple", "Fruit"));
		ingredients.add(new Ingredient("Banana", "Fruit"));

		PantryUtility.sortIngredients(ingredients, Comparator.comparing(Ingredient::getName));

		assertEquals("Apple", ingredients.get(0).getName());
		assertEquals("Apple", ingredients.get(1).getName());
		assertEquals("Banana", ingredients.get(2).getName());
	}

	@Test
	public void testWithNullList() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			PantryUtility.sortIngredients(null, Comparator.comparing(Ingredient::getName));
		});

		assertEquals("Ingredients list and comparator cannot be null.", exception.getMessage());
	}

	@Test
	public void testWithNullComparator() {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Apple", "Fruit"));

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			PantryUtility.sortIngredients(ingredients, null);
		});

		assertEquals("Ingredients list and comparator cannot be null.", exception.getMessage());
	}

}
