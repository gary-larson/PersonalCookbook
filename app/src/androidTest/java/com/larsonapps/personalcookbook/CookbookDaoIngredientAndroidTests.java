package com.larsonapps.personalcookbook;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class CookbookDaoIngredientAndroidTests {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    // Declare constants
    private CookbookDao cookbookDao;
    private CookbookRoomDatabase db;
    private static final String NAME_VALUE_1 = "Chocolate Cookies";
    private static final String SHORT_DESCRIPTION_1 = "Short description";
    private static final String DESCRIPTION_VALUE_1 = "Sufficient description";
    private static final int SERVINGS_VALUE_1 = 12;
    private static final int PREP_TIME_VALUE_1 = 40;
    private static final int COOK_TIME_VALUE_1 = 12;
    private static final int TOTAL_TIME_VALUE_1 = 52;
    private static final String NOTES_VALUE_1 = "These are the notes by the cook.";
    private static final String COPYRIGHT_VALUE_1 = "copyright 5201";
    private static final String PERSONAL_NOTE_VALUE_1 = "personal note";
    private static final String NAME_VALUE_2 = "Apple Pie";
    private static final String SHORT_DESCRIPTION_2 = "another short description";
    private static final String DESCRIPTION_VALUE_2 = "Old fashioned apple pie";
    private static final int SERVINGS_VALUE_2 = 8;
    private static final int PREP_TIME_VALUE_2 = 35;
    private static final int COOK_TIME_VALUE_2 = 60;
    private static final int TOTAL_TIME_VALUE_2 = 95;
    private static final String NOTES_VALUE_2 = "These are apple pie notes by the cook.";
    private static final String COPYRIGHT_VALUE_2 = "copyright 2020";
    private static final String PERSONAL_NOTE_VALUE_2 = "Another personal note";
    private static final int INGREDIENT_ID_VALUE_1 = 0;
    private static final String INGREDIENT_NAME_VALUE_1 = "Milk";
    private static final String AMOUNT_VALUE_1 = "1 1/2";
    private static final String MEASURE_VALUE_1 = "cup";
    private static final String PREPARATION_VALUE_1 = "pour into a bowl";
    private static final String INGREDIENT_PERSONAL_NOTE_1 = "Ingredient personal note";
    private static final int INGREDIENT_ID_VALUE_2 = 0;
    private static final String INGREDIENT_NAME_VALUE_2 = "flour";
    private static final String AMOUNT_VALUE_2 = "3 1/2";
    private static final String MEASURE_VALUE_2 = "cups";
    private static final String PREPARATION_VALUE_2 = "sift";
    private static final String INGREDIENT_PERSONAL_NOTE_2 = "Ingredient personal note two";
    private static final int INGREDIENT_ID_VALUE_3 = 0;
    private static final String INGREDIENT_NAME_VALUE_3 = "vanilla";
    private static final String AMOUNT_VALUE_3 = "1 1/4";
    private static final String MEASURE_VALUE_3 = "teaspoon";
    private static final String PREPARATION_VALUE_3 = "";
    private static final String INGREDIENT_PERSONAL_NOTE_3 = "Ingredient personal note three";
    // declare variables
    RecipeEntity recipeEntity1 = new RecipeEntity(0, NAME_VALUE_1, SHORT_DESCRIPTION_1,
            DESCRIPTION_VALUE_1, SERVINGS_VALUE_1, PREP_TIME_VALUE_1, COOK_TIME_VALUE_1,
            TOTAL_TIME_VALUE_1, NOTES_VALUE_1, COPYRIGHT_VALUE_1, PERSONAL_NOTE_VALUE_1);
    RecipeEntity recipeEntity2 = new RecipeEntity(0, NAME_VALUE_2, SHORT_DESCRIPTION_2,
            DESCRIPTION_VALUE_2, SERVINGS_VALUE_2, PREP_TIME_VALUE_2, COOK_TIME_VALUE_2,
            TOTAL_TIME_VALUE_2, NOTES_VALUE_2, COPYRIGHT_VALUE_2, PERSONAL_NOTE_VALUE_2);
    IngredientEntity ingredient1;
    IngredientEntity ingredient2;
    IngredientEntity ingredient3;
    int recipeId1;
    int recipeId2;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, CookbookRoomDatabase.class).build();
        cookbookDao = db.cookbookDao();
        // insert recipe 1
        cookbookDao.insertRecipe(recipeEntity1);
        recipeId1 = cookbookDao.getRecipeIdByName(NAME_VALUE_1);
        // add another recipe
        cookbookDao.insertRecipe((recipeEntity2));
        recipeId2 = cookbookDao.getRecipeIdByName(NAME_VALUE_2);
        ingredient1 = new IngredientEntity(INGREDIENT_ID_VALUE_1, recipeId1,
                INGREDIENT_NAME_VALUE_1, AMOUNT_VALUE_1, MEASURE_VALUE_1, PREPARATION_VALUE_1,
                INGREDIENT_PERSONAL_NOTE_1);
        ingredient2 = new IngredientEntity(INGREDIENT_ID_VALUE_2, recipeId1,
                INGREDIENT_NAME_VALUE_2, AMOUNT_VALUE_2, MEASURE_VALUE_2, PREPARATION_VALUE_2,
                INGREDIENT_PERSONAL_NOTE_2);
        ingredient3 = new IngredientEntity(INGREDIENT_ID_VALUE_3, recipeId2,
                INGREDIENT_NAME_VALUE_3, AMOUNT_VALUE_3, MEASURE_VALUE_3, PREPARATION_VALUE_3,
                INGREDIENT_PERSONAL_NOTE_3);
        // This tests insert ingredients
        cookbookDao.insertIngredient(ingredient1);
        cookbookDao.insertIngredient(ingredient2);
        cookbookDao.insertIngredient(ingredient3);
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testInsertAllIngredientsAndGetAllIngredients() {
        // insert all ingredients
        List<IngredientEntity> ingredients = new ArrayList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);
        // add to database
        cookbookDao.insertAllIngredients(ingredients);
        // get all ingredients for recipe 1 and test
        cookbookDao.getAllIngredients(recipeId1).observeForever(newIngredients -> {
            boolean isAsserted = false;
            assertEquals(4, newIngredients.size());
            for (IngredientEntity ingredient : newIngredients) {
                if (ingredient.getName().equals(INGREDIENT_NAME_VALUE_1)) {
                    assertEquals(AMOUNT_VALUE_1, ingredient.getAmount());
                    assertEquals(MEASURE_VALUE_1, ingredient.getMeasure());
                    assertEquals(PREPARATION_VALUE_1, ingredient.getPreparation());
                    assertEquals(INGREDIENT_PERSONAL_NOTE_1, ingredient.getPersonalNote());
                    isAsserted = true;
                    break;
                }
            }
            if (!isAsserted) {
                assertEquals(2,1);
            }
        });
    }

    @Test
    public void testDeleteIngredientAndGetIngredientIdByName() {
        int ingredientId = cookbookDao.getIngredientIdByName(recipeId1, ingredient1.getName());
        ingredient1.setIngredientId(ingredientId);
        // delete ingredient
        cookbookDao.deleteIngredient(ingredient1);
        // get all ingredients for recipe 1 and test
        cookbookDao.getAllIngredients(recipeId1).observeForever(newIngredients -> {
            for(IngredientEntity ingredient : newIngredients) {
                assertNotEquals(ingredientId, ingredient.getIngredientId());
            }
        });
    }

    @Test
    public void testUpdateIngredient() {
        int ingredientId = cookbookDao.getIngredientIdByName(recipeId1, ingredient1.getName());
        // modify ingredient
        String temp = "Pudding";
        ingredient1.setIngredientId(ingredientId);
        ingredient1.setName(temp);
        cookbookDao.updateIngredient(ingredient1);
        // get ingredients for recipe 1 and test
        cookbookDao.getAllIngredients(recipeId1).observeForever(newIngredients -> {
            boolean isAsserted = false;
            for (IngredientEntity ingredient : newIngredients) {
                if (ingredient.getIngredientId() == ingredientId) {
                    assertEquals(temp, ingredient.getName());
                    isAsserted = true;
                }
            }
            if (!isAsserted) {
                assertEquals(1, 2);
            }
        });
    }
}
