package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import model.Food;

class TestDecrementQuantity {

	@Test
	void testDecrementsFromOriginalValue() {
		Food mangoSalad = new Food("mangoSalad", "Fruit");
		
		mangoSalad.decrementQuantity();
		
		assertEquals(0, mangoSalad.getQuantity());
	}

	@Test
	void testDecrementsMultipleTimesFromSetValue() {
		Food mangoSalad = new Food("mangoSalad", "Fruit");
		
		mangoSalad.setQuantity(143);

		mangoSalad.decrementQuantity();
		assertEquals(142, mangoSalad.getQuantity());
		mangoSalad.decrementQuantity();
		assertEquals(141, mangoSalad.getQuantity());
		mangoSalad.decrementQuantity();
		assertEquals(140, mangoSalad.getQuantity());
	}

	@Test
	void testDecrementsFromValidToBelowZeroValueThrows() {
		Food mangoSalad = new Food("mangoSalad", "Fruit");

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			mangoSalad.decrementQuantity();
			mangoSalad.decrementQuantity();
		});

		assertEquals("Quantity can't be less than 0.", exception.getMessage());
	}
}
