package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.RecipeUpdateEntity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RecipeUpdateEntityUnitTests {
    // recipe update data
    private static final int RECIPE_UPDATE_ID_VALUE = 5;
    private static final int RECIPE_ID_VALUE = 548;
    private static final String UPDATE_VALUE = "changed amount to 9";

    // create recipe update
    RecipeUpdateEntity recipeUpdateEntity = new
            RecipeUpdateEntity(RECIPE_UPDATE_ID_VALUE, RECIPE_ID_VALUE, UPDATE_VALUE);

    // test constructor and getters
    @Test
    public void testRecipeUpdateIdGetter() {
        assertEquals(RECIPE_UPDATE_ID_VALUE, recipeUpdateEntity.getRecipeUpdateId());
    }

    @Test
    public void testRecipeIdGetter() {
        assertEquals(RECIPE_ID_VALUE, recipeUpdateEntity.getRecipeId());
    }

    @Test
    public void testRecipeUpdateGetter() {
        assertEquals(UPDATE_VALUE, recipeUpdateEntity.getUpdate());
    }

    // test setter
    @Test
    public void testRecipeUpdateIdSetter() {
        int temp = 12468;
        recipeUpdateEntity.setRecipeUpdateId(temp);
        assertEquals(temp, recipeUpdateEntity.getRecipeUpdateId());
    }

    @Test
    public void testRecipeIdSetter() {
        int temp = 17;
        recipeUpdateEntity.setRecipeId(temp);
        assertEquals(temp, recipeUpdateEntity.getRecipeId());
    }

    @Test
    public void testRecipeUpdateSetter() {
        String temp = "total time to 25 minutes";
        recipeUpdateEntity.setUpdate(temp);
        assertEquals(temp, recipeUpdateEntity.getUpdate());
    }
}
