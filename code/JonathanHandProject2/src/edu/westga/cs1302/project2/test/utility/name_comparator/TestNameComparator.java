package edu.westga.cs1302.project2.test.utility.name_comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.utility.NameComparator;

public class TestNameComparator {

	@Test
	public void testSortingFunction() {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Blueberry", "Fruit"));
		ingredients.add(new Ingredient("Chopped Steak", "Meat"));
		ingredients.add(new Ingredient("Avacodo", "Fruit"));

		Collections.sort(ingredients, new NameComparator());

		assertEquals("Avacodo", ingredients.get(0).getName());
		assertEquals("Blueberry", ingredients.get(1).getName());
		assertEquals("Chopped Steak", ingredients.get(2).getName());
	}

	@Test
	public void testEitherThrowsWithNullIngredient() {
		NameComparator comparator = new NameComparator();

		Ingredient ingredient = new Ingredient("Carrot", "Vegetable");

		assertThrows(IllegalArgumentException.class, () -> {
			comparator.compare(ingredient, null);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			comparator.compare(null, ingredient);
		});
	}

	@Test
	public void testWithEmptyList() {
		List<Ingredient> ingredients = new ArrayList<>();
		Collections.sort(ingredients, new NameComparator());

		assertTrue(ingredients.isEmpty());
	}

	@Test
	public void testWithDifferentNameSameType() {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Lettuce", "Vegetable"));
		ingredients.add(new Ingredient("Cucumber", "Vegetable"));
		ingredients.add(new Ingredient("Chicken", "Meat"));
		ingredients.add(new Ingredient("Veal", "Meat"));


		Collections.sort(ingredients, new NameComparator());

		assertEquals("Chicken", ingredients.get(0).getName());
		assertEquals("Cucumber", ingredients.get(1).getName());
		assertEquals("Lettuce", ingredients.get(2).getName());
		assertEquals("Veal", ingredients.get(3).getName());

	}

	@Test
	public void testWithSameNameSameType() {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Lettuce", "Vegetable"));
		ingredients.add(new Ingredient("Lettuce", "Vegetable"));
		ingredients.add(new Ingredient("Chicken", "Meat"));

		Collections.sort(ingredients, new NameComparator());

		assertEquals("Chicken", ingredients.get(0).getName());
		assertEquals("Lettuce", ingredients.get(1).getName());
		assertEquals("Lettuce", ingredients.get(2).getName());
	}
}
