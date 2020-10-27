package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.IngredientEntity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientEntityUnitTests {
    // ingredient data
    private static final int ID_VALUE = 11245;
    private static final int RECIPE_ID_VALUE = 6;
    private static final String AMOUNT_VALUE = "20 1/2";
    private static final String MEASURE_VALUE = "Tablespoon";
    private static final String NAME_VALUE = "Milk";
    private static final String PREPARATION_VALUE = "Pour into container";
    private static final String TO_STRING_VALUE = "Milk 20 1/2 Tablespoon\nPour into container";
    private static final String PERSONAL_NOTE_VALUE = "This is a personal note";

    // Create ingredient
    IngredientEntity ingredient = new IngredientEntity(ID_VALUE, RECIPE_ID_VALUE, NAME_VALUE,
            AMOUNT_VALUE, MEASURE_VALUE, PREPARATION_VALUE, PERSONAL_NOTE_VALUE);

    // Tests for the constructor and Getters

    /**
     * test for ingredient id getter
     */
    @Test
    public void testIngredientIDGetter() {
        assertEquals(ID_VALUE, ingredient.getIngredientId());
    }

    /**
     * test for ingredient recipe id getter
     */
    @Test
    public void testIngredientRecipeIdGetter() {
        assertEquals(RECIPE_ID_VALUE, ingredient.getRecipeId());
    }

    /**
     * Test for ingredient amount getter
     */
    @Test
    public void testIngredientAmountGetter() {
        assertEquals(AMOUNT_VALUE, ingredient.getAmount());
    }

    /**
     * Test for ingredient name getter
     */
    @Test
    public void testIngredientNameGetter() {
        assertEquals(NAME_VALUE, ingredient.getName());
    }

    /**
     * Test for ingredient measuer getter
     */
    @Test
    public void testIngredientMeasureGetter() {
        assertEquals(MEASURE_VALUE, ingredient.getMeasure());
    }

    /**
     * Test for ingredient preparation getter
     */
    @Test
    public void testIngredientPreparationGetter() {
        assertEquals(PREPARATION_VALUE, ingredient.getPreparation());
    }

    /**
     * Test for ingredient personal note getter
     */
    @Test
    public void testIngredientPersonalNoteGetter() {
        assertEquals(PERSONAL_NOTE_VALUE, ingredient.getPersonalNote());
    }

    // Test default constructor and setters

    /**
     * Test for ingredient id setter
     */
    @Test
    public void testIngredientIdSetter() {
        int temp = 846;
        ingredient.setIngredientId(temp);
        assertEquals(temp, ingredient.getIngredientId());
    }

    /**
     * Test for ingredient recipe id setter
     */
    @Test
    public void testIngredientRecipeIdSetter() {
        int temp = 456;
        ingredient.setRecipeId(temp);
        assertEquals(temp, ingredient.getRecipeId());
    }

    /**
     * Test for ingredient amount setter
     */
    @Test
    public void testIngredientAmountSetter() {
        String temp = "2 3/4";
        ingredient.setAmount(temp);
        assertEquals(temp, ingredient.getAmount());
    }

    /**
     * Test for ingredient name setter
     */
    @Test
    public void testIngredientNameSetter() {
        String temp = "Oatmeal Coconut cookies";
        ingredient.setName(temp);
        assertEquals(temp, ingredient.getName());
    }

    /**
     * Test for ingredient measure setter
     */
    @Test
    public void testIngredientMeasureSetter() {
        String temp = "cups";
        ingredient.setName(temp);
        assertEquals(temp, ingredient.getName());
    }

    /**
     * Test for ingredient preparation setter
     */
    @Test
    public void testIngredientPreparationSetter() {
        String temp = "This is a preparation statement";
        ingredient.setName(temp);
        assertEquals(temp, ingredient.getName());
    }

    /**
     * Test for ingredient personal note setter
     */
    @Test
    public void testIngredientPersonalNoteSetter() {
        String temp = "Another personal note";
        ingredient.setPersonalNote(temp);
        assertEquals(temp, ingredient.getPersonalNote());
    }

    /**
     * Test for ingredient to string
     */
    @Test
    public void testIngredientToString() {
        assertEquals(TO_STRING_VALUE, ingredient.toString());
    }
}
