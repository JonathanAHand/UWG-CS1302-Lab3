package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Food;

class TestAddFood {

	@Test
	void testAddNewItemToList() {
		ObservableList<Food> foodList = FXCollections.observableArrayList();
        Food meatLoaf = new Food("meatLoaf", "Meat");
        
        Food.addFood(foodList, meatLoaf);
        
        assertEquals(1, foodList.size());
        assertEquals(meatLoaf, foodList.get(0));
    }
	
	@Test
	void testAddSameItemsToList() {
		ObservableList<Food> foodList = FXCollections.observableArrayList();
        Food meatLoaf = new Food("meatLoaf", "Meat");
        
        foodList.add(meatLoaf);
        Food.addFood(foodList, meatLoaf);
        
        assertEquals(1, foodList.size());
        assertEquals(meatLoaf, foodList.get(0));
    }
	
	@Test
	void testAddDifferentItemsToList() {
		ObservableList<Food> foodList = FXCollections.observableArrayList();
        Food meatLoaf = new Food("meatLoaf", "Meat");
        Food hotWings = new Food("hotWings", "Meat");
	
        Food.addFood(foodList, meatLoaf);
        Food.addFood(foodList, hotWings);
        
        assertEquals(2, foodList.size());
        assertTrue(foodList.contains(meatLoaf));
        assertTrue(foodList.contains(hotWings));
	}  
}
