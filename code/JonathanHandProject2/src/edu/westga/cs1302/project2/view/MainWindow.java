package edu.westga.cs1302.project2.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.westga.cs1302.project2.utility.NameComparator;
import edu.westga.cs1302.project2.utility.PantryUtility;
import edu.westga.cs1302.project2.utility.RecipeFileWriter;
import edu.westga.cs1302.project2.utility.RecipeLoader;
import edu.westga.cs1302.project2.utility.TypeComparator;
import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML
	private ComboBox<String> ingredientType;
	@FXML
	private ListView<Ingredient> ingredientsList;
	@FXML
	private TextField ingredientName;
	@FXML
	private ComboBox<Comparator<Ingredient>> sortCriteria;
	@FXML
	private ListView<Ingredient> recipeIngredientsList;
	@FXML
	private TextField recipeNameField;
	@FXML
	private TextArea recipesTextArea;
	@FXML
	private Button displayRecipesButton;

	@FXML
	void addIngredient(ActionEvent event) {
		try {
			this.ingredientsList.getItems()
					.add(new Ingredient(this.ingredientName.getText(), this.ingredientType.getValue()));
			this.ingredientName.clear();
			this.ingredientType.getSelectionModel().clearSelection();
			this.sortIngredients();
		} catch (IllegalArgumentException error) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to add ingredient");
			alert.setContentText(error.getMessage());
			alert.showAndWait();
		}
	}

	@FXML
	void removeIngredient(ActionEvent event) {
		Ingredient selectedIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();
		if (selectedIngredient != null) {
			this.ingredientsList.getItems().remove(selectedIngredient);
			this.sortIngredients();
		}
	}

	@FXML
	void addIngredientToRecipe() {
		Ingredient selectedIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();
		if (selectedIngredient != null) {
			this.recipeIngredientsList.getItems().add(selectedIngredient);
		}
	}

	@FXML
	void addRecipe() {
		String recipeName = this.recipeNameField.getText();

		if (recipeName == null || recipeName.isEmpty()) {
			System.out.println("Recipe name cannot be empty.");
			return;
		}

		List<Ingredient> ingredients = new ArrayList<>(this.recipeIngredientsList.getItems());

		if (ingredients.isEmpty()) {
			System.out.println("Recipe must contain at least one ingredient.");
			return;
		}

		Recipe newRecipe = new Recipe(recipeName);
		for (Ingredient ingredient : ingredients) {
			newRecipe.addIngredient(ingredient);
		}

		try {
			RecipeFileWriter.appendRecipeToFile(newRecipe, "recipes.txt");
			System.out.println("Recipe added successfully.");
		} catch (IOException error) {
			System.out.println("Error writing to file: " + error.getMessage());
		} catch (IllegalStateException error) {
			System.out.println("Recipe already exists in the file.");
		}
	}

	@FXML
	void displayRecipes() {
		Ingredient selectedIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();
		if (selectedIngredient == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("No Ingredient Selected");
			alert.setContentText("Please select an ingredient to display related recipes.");
			alert.showAndWait();
			return;
		}

		try {
			List<Recipe> allRecipes = RecipeLoader.loadRecipes("recipes.txt");
			List<Recipe> filteredRecipes = new ArrayList<>();
			for (Recipe recipe : allRecipes) {
				for (Ingredient ingredient : recipe.getIngredients()) {
					if (ingredient.getName().equals(selectedIngredient.getName())) {
						filteredRecipes.add(recipe);
						break;
					}
				}
			}

			String recipesText = RecipeUtility.recipeListConverter(filteredRecipes);

			this.recipesTextArea.setText(recipesText);

		} catch (IOException error) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("File Error");
			alert.setContentText("Unable to load recipes from the file.");
			alert.showAndWait();
		}
	}

	@FXML
	void initialize() {
		this.ingredientType.getItems().add("Vegetable");
		this.ingredientType.getItems().add("Meat");
		this.ingredientType.getItems().add("Bread");
		this.ingredientType.getItems().add("Fruit");
		this.ingredientType.getItems().add("Spice");

		this.sortCriteria.getItems().add(new TypeComparator());
		this.sortCriteria.getItems().add(new NameComparator());

		this.recipeIngredientsList.getItems().clear();

	}

	/**
	 * Sorts ingredients in ListView using the selected comparator.
	 */
	private void sortIngredients() {
		Comparator<Ingredient> selectedComparator = this.sortCriteria.getSelectionModel().getSelectedItem();
		if (selectedComparator != null) {
			List<Ingredient> ingredients = new ArrayList<>(this.ingredientsList.getItems());
			PantryUtility.sortIngredients(ingredients, selectedComparator);
			this.ingredientsList.getItems().setAll(ingredients);
		}
	}

	/**
	 * Triggers the sorting of ingredients with the updated comparator when user
	 * changes the sort criteria.
	 * 
	 * @param event the action event triggered by sort criteria being changed.
	 */
	@FXML
	public void sortChanged(ActionEvent event) {
		this.sortIngredients();
	}

}
