package test.utility;

import static org.junit.jupiter.api.Assertions.*;
import utility.PantryUtility;


import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Food;

class TestCalculateTotalQuantity {

	@Test
	void testNullListThrowsException() {
		Exception exception = assertThrows(NullPointerException.class, () -> {
            PantryUtility.calculateTotalQuantity(null);
		});
        
		assertEquals("The food list cannot be null.", exception.getMessage());
    }
	
	@Test
	void testWithEmptyList() {
		ObservableList<Food> emptyList = FXCollections.observableArrayList();
        
		int totalQuantity = PantryUtility.calculateTotalQuantity(emptyList);
        
		assertEquals(0, totalQuantity);
	}
	
	@Test
	void testTotalWithSingleItem() {
		ObservableList<Food> foodList = FXCollections.observableArrayList();
		Food cinnamon = new Food("Cinnamon", "Ingredient");
		
		cinnamon.setQuantity(4);
		foodList.add(cinnamon);
		
		int totalQuantity = PantryUtility.calculateTotalQuantity(foodList);
        assertEquals(4, totalQuantity);
    }
	
	@Test
	void testTotalWithMultipleItems() {
		ObservableList<Food> foodList = FXCollections.observableArrayList();
		Food cinnamon = new Food("Cinnamon", "Ingredient");
		Food sourDough = new Food("SourDough", "Bread"); 
		
		cinnamon.setQuantity(4);
		sourDough.setQuantity(11);
		foodList.add(cinnamon);
		foodList.add(sourDough);
		
		int totalQuantity = PantryUtility.calculateTotalQuantity(foodList);
        assertEquals(15, totalQuantity);
    }
		
}
