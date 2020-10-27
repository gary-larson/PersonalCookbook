package com.larsonapps.personalcookbook.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to deal with widget data
 */
public class CookbookWidgetRecipe {
    // Declare variables
    private String recipeName;
    private List<String> ingredientList;

    /**
     * Default constructor
     */
    public CookbookWidgetRecipe() {
        ingredientList = new ArrayList<>();
    }

    /**
     * Constructor with all variables
     * @param name to set
     * @param ingredients to set
     */
    public CookbookWidgetRecipe(String name, List<String> ingredients) {
        this.recipeName = name;
        this.ingredientList = ingredients;
    }

    /**
     * Getter for recipe name
     * @return recipe name
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * Setter for recipe name
     * @param recipeName to set
     */
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    /**
     * Getter for ingredient list
     * @return ingredient list
     */
    public List<String> getIngredientList() {
        return ingredientList;
    }

    /**
     * Setter for ingredient list
     * @param ingredientList to set
     */
    public void setIngredientList(List<String> ingredientList) {
        this.ingredientList = ingredientList;
    }
}
