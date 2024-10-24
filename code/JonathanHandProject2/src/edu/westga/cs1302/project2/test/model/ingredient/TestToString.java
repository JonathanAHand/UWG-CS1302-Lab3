package edu.westga.cs1302.project2.test.model.ingredient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;

class TestToString {

	@Test
	void test() {
		Ingredient ingredient = new Ingredient("name", "type");
		
		String result = ingredient.toString();
		
		assertEquals("name-type", result, "checking result of the Ingredient::toString()");
	}

}
