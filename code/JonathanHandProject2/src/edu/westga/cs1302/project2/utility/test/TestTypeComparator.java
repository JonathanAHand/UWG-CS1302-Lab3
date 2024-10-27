package edu.westga.cs1302.project2.utility.test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.utility.TypeComparator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestTypeComparator {

	@Test
	public void testSortingFunction() {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Carrot", "Vegetable"));
		ingredients.add(new Ingredient("Chicken", "Meat"));
		ingredients.add(new Ingredient("Apple", "Fruit"));

		Collections.sort(ingredients, new TypeComparator());

		assertEquals("Apple", ingredients.get(0).getName());
		assertEquals("Chicken", ingredients.get(1).getName());
		assertEquals("Carrot", ingredients.get(2).getName());
	}

	@Test
	public void testEitherThrowsWithNullIngredient() {
		TypeComparator comparator = new TypeComparator();

		Ingredient ingredient = new Ingredient("Carrot", "Vegetable");

		assertThrows(IllegalArgumentException.class, () -> {
			comparator.compare(ingredient, null);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			comparator.compare(null, ingredient);
		});
	}

	@Test
	public void testWithDifferentNameSameType() {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Lettuce", "Vegetable"));
		ingredients.add(new Ingredient("Cucumber", "Vegetable"));
		ingredients.add(new Ingredient("Chicken", "Meat"));

		Collections.sort(ingredients, new TypeComparator());

		assertEquals("Chicken", ingredients.get(0).getName());
		assertEquals("Lettuce", ingredients.get(1).getName());
		assertEquals("Cucumber", ingredients.get(2).getName());
	}

	@Test
	public void testWithEmptyList() {
		List<Ingredient> ingredients = new ArrayList<>();
		Collections.sort(ingredients, new TypeComparator());

		assertTrue(ingredients.isEmpty());
	}

}