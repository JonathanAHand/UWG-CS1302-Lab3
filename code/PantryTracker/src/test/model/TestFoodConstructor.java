package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import model.Food;

class TestFoodConstructor {

	@Test
	void testValidInputsCreateValidObject() {
		Food steak = new Food("steak", "Meat");

		assertEquals("steak", steak.getName());
		assertEquals("Meat", steak.getType());
		assertEquals(1, steak.getQuantity());
	}

	@Test
	void testNullNameThrowsException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Food(null, "Ingredient");
		});
		assertEquals("Name must be neither null nor empty.", exception.getMessage());
	}

	@Test
	void testNullTypeThrowsException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Food("Rocky Mountain Oysters", null);
		});
		assertEquals("Type must be neither null nor empty.", exception.getMessage());
	}

	@Test
	void testEmptyTypeThrowsException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Food("Rocky Mountain Oysters", "");
		});
		assertEquals("Type must be neither null nor empty.", exception.getMessage());
	}

	@Test
	void testEmptyNameThrowsException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Food("", "Fruit");
		});
		assertEquals("Name must be neither null nor empty.", exception.getMessage());
	}
}
