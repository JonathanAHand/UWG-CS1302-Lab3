package edu.westga.cs1302.project2.utility.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.*;
import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.utility.NameComparator;
import edu.westga.cs1302.project2.utility.PantryUtility;
import edu.westga.cs1302.project2.utility.TypeComparator;

class TestSortIngredient {

	@Test
	void testSortFunctionByName() {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Banana", "Fruit"));
		ingredients.add(new Ingredient("Apple", "Fruit"));
		ingredients.add(new Ingredient("Squash", "Vegetable"));

		PantryUtility.sortIngredients(ingredients, new NameComparator());

		assertEquals("Apple", ingredients.get(0).getName());
		assertEquals("Banana", ingredients.get(1).getName());
		assertEquals("Squash", ingredients.get(2).getName());
	}

	@Test
	public void testSortFunctionByType() {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("Banana", "Fruit"));
		ingredients.add(new Ingredient("Apple", "Fruit"));

		PantryUtility.sortIngredients(ingredients, new TypeComparator());

		assertEquals("Fruit", ingredients.get(0).getType());
		assertEquals("Fruit", ingredients.get(1).getType());
	}

	@Test
	public void testSortsWithEmptyList() {
		List<Ingredient> ingredients = new ArrayList<>();
		Comparator<Ingredient> comparator = new NameComparator();

		PantryUtility.sortIngredients(ingredients, comparator);

		assertEquals(0, ingredients.size());
	}

	@Test
	public void testSortsWithSingleItem() {
		List<Ingredient> ingredients = Arrays.asList(new Ingredient("Apple", "Fruit"));

		Comparator<Ingredient> comparator = new NameComparator();
		PantryUtility.sortIngredients(ingredients, comparator);

		assertEquals("Apple", ingredients.get(0).getName());
	}

}
