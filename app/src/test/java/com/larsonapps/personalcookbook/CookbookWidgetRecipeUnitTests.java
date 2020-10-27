package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.CookbookWidgetRecipe;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Class to testcookbook widget recipe data
 */
public class CookbookWidgetRecipeUnitTests {
    // category update data
    private static final String RECIPE_NAME_VALUE = "Our Recipe";
    private static final String INGREDIENT_VALUE_1 = "1 Cup Milk";
    private static final String INGREDIENT_VALUE_2 = "Olice Oil";
    private static final String INGREDIENT_VALUE_3 = "1 1/2 pounds Dried Fettuccine";
    private CookbookWidgetRecipe mRecipe;
    private CookbookWidgetRecipe mRecipe1;

    // create cookbook widget recipe

    /**
     * Method to create data for cookbook widget recipe tests
     */
    @Before
    public void createRecipe() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add(INGREDIENT_VALUE_1);
        ingredients.add(INGREDIENT_VALUE_2);
        ingredients.add(INGREDIENT_VALUE_3);
        mRecipe = new CookbookWidgetRecipe(RECIPE_NAME_VALUE, ingredients);
        mRecipe1 = new CookbookWidgetRecipe();
    }

    // test constructor and getters

    /**
     * Test for cookbook widget recipe name getter
     */
    @Test
    public void testCookbookWidgetRecipeRecipeNameGetter() {
        assertEquals(RECIPE_NAME_VALUE, mRecipe.getRecipeName());
    }

    /**
     * Test for cookbook widget ingredient list getter
     */
    @Test
    public void testCookbookWidgetRecipeIngredientListGetter() {
        assertEquals(INGREDIENT_VALUE_1, mRecipe.getIngredientList().get(0));
    }

    // test default constructor and setters

    /**
     * Test for cookbook widget recipe name setter
     */
    @Test
    public void testCookbookWidgetRecipeNameSetter() {
        String temp = "Another Recipe";
        mRecipe1.setRecipeName(temp);
        assertEquals(temp, mRecipe1.getRecipeName());
    }

    /**
     * Test for cookbook widget recipe ingredient list setter
     */
    @Test
    public void testCookbookWidgetRecipeIngredientListSetter() {
        List<String> temp = new ArrayList<>();
        temp.add(INGREDIENT_VALUE_3);
        mRecipe1.setIngredientList(temp);
        assertEquals(INGREDIENT_VALUE_3, mRecipe1.getIngredientList().get(0));
    }
}
