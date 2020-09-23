package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.IngredientUpdateEntity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientUpdateEntityUnitTests {
    // ingredient update data
    private static final int INGREDIENT_UPDATE_ID_VALUE = 5;
    private static final int INGREDIENT_ID_VALUE = 54;
    private static final String UPDATE_VALUE = "changed amount to 2";

    IngredientUpdateEntity ingredientUpdateEntity = new
            IngredientUpdateEntity(INGREDIENT_UPDATE_ID_VALUE, INGREDIENT_ID_VALUE, UPDATE_VALUE);

    @Test
    public void testIngredientUpdateIdGetter() {
        assertEquals(INGREDIENT_UPDATE_ID_VALUE, ingredientUpdateEntity.getIngredientUpdateId());
    }

    @Test
    public void testIngredientIdGetter() {
        assertEquals(INGREDIENT_ID_VALUE, ingredientUpdateEntity.getIngredientId());
    }

    @Test
    public void testIngredientUpdateGetter() {
        assertEquals(UPDATE_VALUE, ingredientUpdateEntity.getUpdate());
    }

    @Test
    public void testIngredientUpdateSetter() {
        String temp = "changed name to skim milk";
        ingredientUpdateEntity.setUpdate(temp);
        assertEquals(temp, ingredientUpdateEntity.getUpdate());
    }
}
