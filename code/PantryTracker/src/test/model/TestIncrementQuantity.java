package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import model.Food;

class TestIncrementQuantity {

	@Test
	void testIncrementsFromOriginalValue() {
		Food pumpkinPie = new Food("pumpkinPie", "Dessert");
		
		pumpkinPie.incrementQuantity();
		
		assertEquals(2, pumpkinPie.getQuantity());
	}

	@Test
	void testIncrementsMultipleTimesFromOriginalValue() {
		Food pumpkinPie = new Food("pumpkinPie", "Dessert");
		
		pumpkinPie.incrementQuantity();
		
		assertEquals(2, pumpkinPie.getQuantity());
		pumpkinPie.incrementQuantity();
		assertEquals(3, pumpkinPie.getQuantity());
		pumpkinPie.incrementQuantity();
		assertEquals(4, pumpkinPie.getQuantity());
		pumpkinPie.incrementQuantity();
		assertEquals(5, pumpkinPie.getQuantity());
	}
	
	@Test
	void testIncrementsFromSetValue() {
		Food pumpkinPie = new Food("pumpkinPie", "Dessert");
		
		pumpkinPie.setQuantity(33);
		pumpkinPie.incrementQuantity();
		
		assertEquals(34, pumpkinPie.getQuantity());
	}

}
