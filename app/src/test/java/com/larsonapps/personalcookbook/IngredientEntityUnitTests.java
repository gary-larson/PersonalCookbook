package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.Ingredient;
import com.larsonapps.personalcookbook.data.IngredientEntity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientEntityUnitTests {
    // ingredient data
    private static final int ID_VALUE = 11245;
    private static final int RECIPE_ID_VALUE = 6;
    private static final double AMOUNT_VALUE = 20.45;
    private static final String MEASURE_VALUE = "Tablespoon";
    private static final String NAME_VALUE = "Milk";
    private static final String PREPARATION_VALUE = "Pour into container";

    // Create ingredient
    IngredientEntity ingredient = new IngredientEntity(ID_VALUE, RECIPE_ID_VALUE, NAME_VALUE,
            AMOUNT_VALUE, MEASURE_VALUE, PREPARATION_VALUE);

    // Tests for the constructor and Getters
    @Test
    public void testIngredientIDGetter() {
        assertEquals(ID_VALUE, ingredient.getId());
    }

    @Test
    public void testIngredientRecipeIdGetter() {
        assertEquals(RECIPE_ID_VALUE, ingredient.getRecipeId());
    }

    @Test
    public void testIngredientAmountGetter() {
        assertEquals(AMOUNT_VALUE, ingredient.getAmount(), 0.01);
    }

    @Test
    public void testIngredientNameGetter() {
        assertEquals(NAME_VALUE, ingredient.getName());
    }

    @Test
    public void testIngredientMeasureGetter() {
        assertEquals(MEASURE_VALUE, ingredient.getMeasure());
    }

    @Test
    public void testIngredientPreparationGetter() {
        assertEquals(PREPARATION_VALUE, ingredient.getPreparation());
    }

    // Test default constructor and setters
    @Test
    public void testIngredientAmountSetter() {
        double temp = 2.75;
        ingredient.setAmount(temp);
        assertEquals(temp, ingredient.getAmount(), 0.01);
    }

    @Test
    public void testIngredientNameSetter() {
        String temp = "Oatmeal Coconut cookies";
        ingredient.setName(temp);
        assertEquals(temp, ingredient.getName());
    }

    @Test
    public void testIngredientMeasureSetter() {
        String temp = "cups";
        ingredient.setName(temp);
        assertEquals(temp, ingredient.getName());
    }

    @Test
    public void testIngredientPreparationSetter() {
        String temp = "This is a preparation statement";
        ingredient.setName(temp);
        assertEquals(temp, ingredient.getName());
    }
}
