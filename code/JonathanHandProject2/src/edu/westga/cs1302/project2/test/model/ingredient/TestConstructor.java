package edu.westga.cs1302.project2.test.model.ingredient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;

class TestConstructor {

	@Test
	void testNullName() {
		assertThrows(IllegalArgumentException.class, ()->{new Ingredient(null, "b");});
	}

	@Test
	void testEmptyName() {
		assertThrows(IllegalArgumentException.class, ()->{new Ingredient("", "b");});
	}

	@Test
	void testNullType() {
		assertThrows(IllegalArgumentException.class, ()->{new Ingredient("a", null);});
	}

	@Test
	void testEmptyType() {
		assertThrows(IllegalArgumentException.class, ()->{new Ingredient("a", "");});
	}
	
	@Test
	void testValidNameAndType() {
		Ingredient result = new Ingredient("a", "b");
		
		assertEquals("a", result.getName(), "checking the name of the ingredient");
		assertEquals("b", result.getType(), "checking the type of the ingredient");
	}

}
