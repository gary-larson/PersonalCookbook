package com.larsonapps.personalcookbook;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.data.IngredientUpdateEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class CookbookDaoIngredientAndroidTests {
    // Declare constants
    private CookbookDao cookbookDao;
    private CookbookRoomDatabase db;
    private static final String NAME_VALUE_1 = "Chocolate Cookies";
    private static final String DESCRIPTION_VALUE_1 = "Sufficient description";
    private static final int SERVINGS_VALUE_1 = 12;
    private static final int PREP_TIME_VALUE_1 = 40;
    private static final int COOK_TIME_VALUE_1 = 12;
    private static final int TOTAL_TIME_VALUE_1 = 52;
    private static final String NOTES_VALUE_1 = "These are the notes by the cook.";
    private static final String COPYRIGHT_VALUE_1 = "copyright 5201";
    private static final String NAME_VALUE_2 = "Apple Pie";
    private static final String DESCRIPTION_VALUE_2 = "Old fashioned apple pie";
    private static final int SERVINGS_VALUE_2 = 8;
    private static final int PREP_TIME_VALUE_2 = 35;
    private static final int COOK_TIME_VALUE_2 = 60;
    private static final int TOTAL_TIME_VALUE_2 = 95;
    private static final String NOTES_VALUE_2 = "These are apple pie notes by the cook.";
    private static final String COPYRIGHT_VALUE_2 = "copyright 2020";
    private static final int INGREDIENT_ID_VALUE_1 = 0;
    private static final String INGREDIENT_NAME_VALUE_1 = "Milk";
    private static final double AMOUNT_VALUE_1 = 1.56;
    private static final String MEASURE_VALUE_1 = "cup";
    private static final String PREPARATION_VALUE_1 = "pour into a bowl";
    private static final int INGREDIENT_ID_VALUE_2 = 0;
    private static final String INGREDIENT_NAME_VALUE_2 = "flour";
    private static final double AMOUNT_VALUE_2 = 3.5;
    private static final String MEASURE_VALUE_2 = "cups";
    private static final String PREPARATION_VALUE_2 = "sift";
    private static final int INGREDIENT_ID_VALUE_3 = 0;
    private static final String INGREDIENT_NAME_VALUE_3 = "vanilla";
    private static final double AMOUNT_VALUE_3 = 1.5;
    private static final String MEASURE_VALUE_3 = "teaspoon";
    private static final String PREPARATION_VALUE_3 = "";
    // declare variables
    RecipeEntity recipeEntity1 = new RecipeEntity(0, NAME_VALUE_1, DESCRIPTION_VALUE_1,
            SERVINGS_VALUE_1, PREP_TIME_VALUE_1, COOK_TIME_VALUE_1, TOTAL_TIME_VALUE_1,
            NOTES_VALUE_1, COPYRIGHT_VALUE_1);
    RecipeEntity recipeEntity2 = new RecipeEntity(0, NAME_VALUE_2, DESCRIPTION_VALUE_2,
            SERVINGS_VALUE_2, PREP_TIME_VALUE_2, COOK_TIME_VALUE_2, TOTAL_TIME_VALUE_2,
            NOTES_VALUE_2, COPYRIGHT_VALUE_2);
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
        // get all records
        List<RecipeEntity> recipeEntities = cookbookDao.getAllRecipes();
        // test size
        assertEquals(1, recipeEntities.size());
        // get recipe id 1
        recipeId1 = recipeEntities.get(0).getId();
        // add another recipe
        cookbookDao.insertRecipe((recipeEntity2));
        // get all recipes
        recipeEntities = cookbookDao.getAllRecipes();
        // test size
        assertEquals(2, recipeEntities.size());
        // assign recipe id 3
        if (recipeEntities.get(0).getId() == recipeId1) {
            recipeId2 = recipeEntities.get(1).getId();
        } else {
            recipeId2 = recipeEntities.get(0).getId();
        }
        ingredient1 = new IngredientEntity(INGREDIENT_ID_VALUE_1, recipeId1,
                INGREDIENT_NAME_VALUE_1, AMOUNT_VALUE_1, MEASURE_VALUE_1, PREPARATION_VALUE_1);
        ingredient2 = new IngredientEntity(INGREDIENT_ID_VALUE_2, recipeId1,
                INGREDIENT_NAME_VALUE_2, AMOUNT_VALUE_2, MEASURE_VALUE_2, PREPARATION_VALUE_2);
        ingredient3 = new IngredientEntity(INGREDIENT_ID_VALUE_3, recipeId2,
                INGREDIENT_NAME_VALUE_3, AMOUNT_VALUE_3, MEASURE_VALUE_3, PREPARATION_VALUE_3);
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
        // get all ingredient for recipe 1
        List<IngredientEntity> ingredients1 = cookbookDao.getAllIngredients(recipeId1);
        // get all ingredients for recipe 2
        List<IngredientEntity> ingredients2 = cookbookDao.getAllIngredients(recipeId2);
        // test
        assertEquals(2, ingredients1.size());
        assertEquals(1, ingredients2.size());
    }

    @Test
    public void testInsertIngredient() {
        // insert ingredient into database
        cookbookDao.insertIngredient(ingredient1);
        // get ingredients for recipe 1
        List<IngredientEntity> ingredients = cookbookDao.getAllIngredients(recipeId1);
        // test
        assertEquals(1, ingredients.size());
        assertEquals(recipeId1, ingredients.get(0).getRecipeId());
        assertEquals(INGREDIENT_NAME_VALUE_1, ingredients.get(0).getName());
        assertEquals(AMOUNT_VALUE_1, ingredients.get(0).getAmount(), .01);
        assertEquals(MEASURE_VALUE_1, ingredients.get(0).getMeasure());
        assertEquals(PREPARATION_VALUE_1, ingredients.get(0).getPreparation());
        // get ingredients for recipe 2
        List<IngredientEntity> ingredients1 = cookbookDao.getAllIngredients(recipeId2);
        // test
        assertEquals(0, ingredients1.size());
    }

    @Test
    public void testDeleteIngredient() {
        // insert ingredient into database
        cookbookDao.insertIngredient(ingredient1);
        // get ingredients for recipe 1
        List<IngredientEntity> ingredients = cookbookDao.getAllIngredients(recipeId1);
        assertEquals(1, ingredients.size());
        // delete ingredient
        cookbookDao.deleteIngredient(ingredients.get(0));
        // get ingredients for recipe 1
        List<IngredientEntity> ingredients1 = cookbookDao.getAllIngredients(recipeId1);
        // test
        assertEquals(0, ingredients1.size());
    }

    @Test
    public void testUpdateIngredient() {
        // insert ingredient into database
        cookbookDao.insertIngredient(ingredient1);
        // get ingredients for recipe 1
        List<IngredientEntity> ingredients = cookbookDao.getAllIngredients(recipeId1);
        assertEquals(1, ingredients.size());
        // modify ingredient
        String temp = "tablespoon";
        ingredients.get(0).setMeasure(temp);
        cookbookDao.updateIngredient(ingredients.get(0));
        // get ingredients for recipe 1
        List<IngredientEntity> ingredients1 = cookbookDao.getAllIngredients(recipeId1);
        // test
        assertEquals(1, ingredients1.size());
        assertEquals(temp, ingredients1.get(0).getMeasure());
    }

    @Test
    public void testInsertIngredientUpdateAndGetIngredientUpdate() {
        // insert ingredient into database
        cookbookDao.insertIngredient(ingredient1);
        // get ingredient id
        List<IngredientEntity> ingredients = cookbookDao.getAllIngredients(recipeId1);
        assertEquals(1, ingredients.size());
        int ingredientId = ingredients.get(0).getIngredientId();
        // create variable
        String temp = "This is an update for this ingredient.";
        // Create a ingredient update
        IngredientUpdateEntity ingredientUpdate = new IngredientUpdateEntity(0,
                ingredientId, temp);
        // insert the ingredient update
        cookbookDao.insertIngredientUpdate(ingredientUpdate);
        // get ingredient update
        IngredientUpdateEntity ingredientUpdateEntity =
                cookbookDao.getIngredientUpdate(ingredientId);
        // test update
        assertEquals(temp, ingredientUpdateEntity.getUpdate());
    }

    @Test
    public void testDeleteIngredientUpdate() {
        // insert ingredient into database
        cookbookDao.insertIngredient(ingredient1);
        // get ingredient id
        List<IngredientEntity> ingredients = cookbookDao.getAllIngredients(recipeId1);
        assertEquals(1, ingredients.size());
        int ingredientId = ingredients.get(0).getIngredientId();
        // create variable
        String temp = "This is an update for this ingredient.";
        // Create an ingredient update
        IngredientUpdateEntity ingredientUpdate = new IngredientUpdateEntity(0, ingredientId, temp);
        // insert the ingredient update
        cookbookDao.insertIngredientUpdate(ingredientUpdate);
        // get ingredient update
        IngredientUpdateEntity ingredientUpdateEntity = cookbookDao.getIngredientUpdate(ingredientId);
        // delete the ingredient update
        cookbookDao.deleteIngredientUpdate(ingredientUpdateEntity);
        // get the update
        IngredientUpdateEntity ingredientUpdateEntity1 = cookbookDao.getIngredientUpdate(ingredientId);
        // test
        assertNull(ingredientUpdateEntity1);
    }

    @Test
    public void testUpdateIngredientUpdate() {
        // insert ingredient into database
        cookbookDao.insertIngredient(ingredient1);
        // get ingredient id
        List<IngredientEntity> ingredients = cookbookDao.getAllIngredients(recipeId1);
        assertEquals(1, ingredients.size());
        int ingredientId = ingredients.get(0).getIngredientId();
        // create variable
        String temp = "This is an update for this ingredient.";
        // Create a ingredient update
        IngredientUpdateEntity ingredientUpdate = new IngredientUpdateEntity(0, ingredientId, temp);
        // insert the ingredient update
        cookbookDao.insertIngredientUpdate(ingredientUpdate);
        // get ingredient update
        IngredientUpdateEntity ingredientUpdateEntity = cookbookDao.getIngredientUpdate(ingredientId);
        // modify the update
        String temp1 = "Updated update";
        ingredientUpdateEntity.setUpdate(temp1);
        // save to database
        cookbookDao.updateIngredientUpdate(ingredientUpdateEntity);
        // get modified update
        IngredientUpdateEntity ingredientUpdateEntity1 = cookbookDao.getIngredientUpdate(ingredientId);
        // test
        assertEquals(temp1, ingredientUpdateEntity1.getUpdate());
    }
}
