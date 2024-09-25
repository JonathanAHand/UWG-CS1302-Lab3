package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import model.Food;

class TestSetQuantity {

	@Test
	void testSetsWithValidQuantity() {
		Food chicken = new Food("Chicken", "Meat");
		chicken.setQuantity(37);
		assertEquals(37, chicken.getQuantity());
	}

	@Test
	void testSetsWithQuantityAtMinimumBorder() {
		Food chicken = new Food("Chicken", "Meat");
		chicken.setQuantity(0);
		assertEquals(0, chicken.getQuantity());
	}

	@Test
	void testThrowsWithNegativeSetNumber() {
		Food chicken = new Food("Chicken", "Meat");
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			chicken.setQuantity(-1);
		});
		assertEquals("Quantity can't be negative.", exception.getMessage());
	}

	@Test
	void testSetsMultipleTimesOverWritesValues() {
		Food chicken = new Food("Chicken", "Meat");
		chicken.setQuantity(35);
		assertEquals(35, chicken.getQuantity());
		chicken.setQuantity(17);
		assertEquals(17, chicken.getQuantity());
		chicken.setQuantity(23);
		assertEquals(23, chicken.getQuantity());
	}
}
