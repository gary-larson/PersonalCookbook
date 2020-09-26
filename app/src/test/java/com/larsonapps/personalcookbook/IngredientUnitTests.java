package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.Ingredient;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientUnitTests {
    // ingredient data
    private static final double AMOUNT_VALUE = 20.45;
    private static final String MEASURE_VALUE = "Tablespoon";
    private static final String NAME_VALUE = "Milk";
    private static final String PREPARATION_VALUE = "Pour into container";
    private static final String TO_STRING_VALUE = "Milk 20.5 Tablespoon\nPour into container";

    // Create ingredient
    Ingredient ingredient = new Ingredient(NAME_VALUE, AMOUNT_VALUE, MEASURE_VALUE,
            PREPARATION_VALUE);

    // Tests for the constructor and Getters

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
    Ingredient ingredientDefault = new Ingredient();

    @Test
    public void testIngredientAmountSetter() {
        double temp = 2.75;
        ingredientDefault.setAmount(temp);
        assertEquals(temp, ingredientDefault.getAmount(), 0.01);
    }

    @Test
    public void testIngredientNameSetter() {
        String temp = "Oatmeal Coconut cookies";
        ingredientDefault.setName(temp);
        assertEquals(temp, ingredientDefault.getName());
    }

    @Test
    public void testIngredientMeasureSetter() {
        String temp = "cups";
        ingredientDefault.setName(temp);
        assertEquals(temp, ingredientDefault.getName());
    }

    @Test
    public void testIngredientPreparationSetter() {
        String temp = "This is a preparation statement";
        ingredientDefault.setName(temp);
        assertEquals(temp, ingredientDefault.getName());
    }

    @Test
    public void testIngredientToString() {
        assertEquals(TO_STRING_VALUE, ingredient.toString());
    }
}
