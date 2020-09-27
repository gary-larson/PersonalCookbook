package com.larsonapps.personalcookbook;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.RecipeUpdateEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class CookbookDaoRecipeAndroidTests {
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
    private static final String NAME_VALUE_2 = "Apple Pie";
    private static final String SHORT_DESCRIPTION_2 = "another short description";
    private static final String DESCRIPTION_VALUE_2 = "Old fashioned apple pie";
    private static final int SERVINGS_VALUE_2 = 8;
    private static final int PREP_TIME_VALUE_2 = 35;
    private static final int COOK_TIME_VALUE_2 = 60;
    private static final int TOTAL_TIME_VALUE_2 = 95;
    private static final String NOTES_VALUE_2 = "These are apple pie notes by the cook.";
    private static final String COPYRIGHT_VALUE_2 = "copyright 2020";
    // declare variables
    //TODO add short description in for database version 2
    RecipeEntity recipeEntity1 = new RecipeEntity(0, NAME_VALUE_1, DESCRIPTION_VALUE_1,
            SERVINGS_VALUE_1, PREP_TIME_VALUE_1, COOK_TIME_VALUE_1, TOTAL_TIME_VALUE_1,
            NOTES_VALUE_1, COPYRIGHT_VALUE_1);
    RecipeEntity recipeEntity2 = new RecipeEntity(0, NAME_VALUE_2, DESCRIPTION_VALUE_2,
            SERVINGS_VALUE_2, PREP_TIME_VALUE_2, COOK_TIME_VALUE_2, TOTAL_TIME_VALUE_2,
            NOTES_VALUE_2, COPYRIGHT_VALUE_2);
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
        LiveData<List<RecipeEntity>> liveRecipes = cookbookDao.getAllRecipes();
        List<RecipeEntity> recipes = liveRecipes.getValue();
        // test size
        assert recipes != null;
        assertEquals(1, recipes.size());
        // get recipe id
        recipeId1 = recipes.get(0).getId();
        // add another recipe
        cookbookDao.insertRecipe((recipeEntity2));
        // get all recipes
        liveRecipes = cookbookDao.getAllRecipes();
        recipes = liveRecipes.getValue();
        // test size
        assert recipes != null;
        assertEquals(2, recipes.size());
        if (recipes.get(0).getId() == recipeId1) {
            recipeId2 = recipes.get(1).getId();
        } else {
            recipeId2 = recipes.get(0).getId();
        }
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testInsertRecipeAndGetAllRecipes() {
        // insert recipe 1
        cookbookDao.insertRecipe(recipeEntity1);
        // get all recipes
        LiveData<List<RecipeEntity>> liveRecipes = cookbookDao.getAllRecipes();
        List<RecipeEntity> recipes = liveRecipes.getValue();
        // test size
        assert recipes != null;
        assertEquals(3, recipes.size());
        // test all variables except id which is auto generated
        if (recipes.get(0).getId() == recipeId1) {
            assertEquals(NAME_VALUE_1, recipes.get(0).getName());
            //TODO ADD for database version 2
            //assertEquals(SHORT_DESCRIPTION_1, recipes.get(0).getShortDescription);
            assertEquals(DESCRIPTION_VALUE_1, recipes.get(0).getDescription());
            assertEquals(SERVINGS_VALUE_1, recipes.get(0).getServings());
            assertEquals(PREP_TIME_VALUE_1, recipes.get(0).getPrepTime());
            assertEquals(COOK_TIME_VALUE_1, recipes.get(0).getCookTime());
            assertEquals(TOTAL_TIME_VALUE_1, recipes.get(0).getTotalTime());
            assertEquals(NOTES_VALUE_1, recipes.get(0).getNotes());
            assertEquals(COPYRIGHT_VALUE_1, recipes.get(0).getCopyright());
        } else {
            assertEquals(NAME_VALUE_2, recipes.get(0).getName());
            //TODO ADD for database version 2
            //assertEquals(SHORT_DESCRIPTION_2, recipes.get(0).getShortDescription);
            assertEquals(DESCRIPTION_VALUE_2, recipes.get(0).getDescription());
            assertEquals(SERVINGS_VALUE_2, recipes.get(0).getServings());
            assertEquals(PREP_TIME_VALUE_2, recipes.get(0).getPrepTime());
            assertEquals(COOK_TIME_VALUE_2, recipes.get(0).getCookTime());
            assertEquals(TOTAL_TIME_VALUE_2, recipes.get(0).getTotalTime());
            assertEquals(NOTES_VALUE_2, recipes.get(0).getNotes());
            assertEquals(COPYRIGHT_VALUE_2, recipes.get(0).getCopyright());
        }
    }

    @Test
    public void testGetRecipe() {
        // get recipe by id
        RecipeEntity recipe = cookbookDao.getRecipe(recipeId1);
        // test all variables
        assertEquals(recipeId1, recipe.getId());
        assertEquals(NAME_VALUE_1, recipe.getName());
        assertEquals(DESCRIPTION_VALUE_1, recipe.getDescription());
        //TODO add for database version 2
        //assertEquals(SHORT_DESCRIPTION_VALUE_1, recipe.getShortDescription());
        assertEquals(SERVINGS_VALUE_1, recipe.getServings());
        assertEquals(PREP_TIME_VALUE_1, recipe.getPrepTime());
        assertEquals(COOK_TIME_VALUE_1, recipe.getCookTime());
        assertEquals(TOTAL_TIME_VALUE_1, recipe.getTotalTime());
        assertEquals(NOTES_VALUE_1, recipe.getNotes());
        assertEquals(COPYRIGHT_VALUE_1, recipe.getCopyright());
    }

    @Test
    public void testDeleteRecipe() {
        // get recipe
        RecipeEntity recipe = cookbookDao.getRecipe(recipeId1);
        // delete recipe
        cookbookDao.deleteRecipe(recipe);
        // get all recipes
        LiveData<List<RecipeEntity>> liveRecipes = cookbookDao.getAllRecipes();
        List<RecipeEntity> recipes = liveRecipes.getValue();
        // test for recipe
        assert recipes != null;
        for (RecipeEntity recipe1 : recipes) {
            assertNotEquals(recipeId1, recipe1.getId());
        }
    }

    @Test
    public void testUpdateRecipe() {
        // get recipe
        RecipeEntity recipe = cookbookDao.getRecipe(recipeId1);
        // change recipe
        recipe.setCookTime(500);
        // update database
        cookbookDao.updateRecipe(recipe);
        // get updated record
        RecipeEntity recipe1 = cookbookDao.getRecipe(recipeId1);
        // test record
        assertEquals(500, recipe1.getCookTime());
    }

    @Test
    public void testInsertRecipeUpdateAndGetRecipeUpdate() {
        // create variable
        String temp = "This is an update for this recipe.";
        // Create a recipe update
        RecipeUpdateEntity recipeUpdate = new RecipeUpdateEntity(0, recipeId1, temp);
        // insert the recipe update
        cookbookDao.insertRecipeUpdate(recipeUpdate);
        // get recipe update
        RecipeUpdateEntity recipeUpdate1 = cookbookDao.getRecipeUpdate(recipeId1);
        // test update
        assertEquals(temp, recipeUpdate1.getUpdate());
    }

    @Test
    public void testDeleteRecipeUpdate() {
        // create variable
        String temp = "This is an update for this recipe.";
        // Create a recipe update
        RecipeUpdateEntity recipeUpdate = new RecipeUpdateEntity(0, recipeId1, temp);
        // insert the recipe update
        cookbookDao.insertRecipeUpdate(recipeUpdate);
        // get recipe update
        RecipeUpdateEntity recipeUpdate1 = cookbookDao.getRecipeUpdate(recipeId1);
        // delete the recipe update
        cookbookDao.deleteRecipeUpdate(recipeUpdate1);
        // get the update
        RecipeUpdateEntity recipeUpdate2 = cookbookDao.getRecipeUpdate(recipeId1);
        // test
        assertNull(recipeUpdate2);
    }

    @Test
    public void testUpdateRecipeUpdate() {
        // create variable
        String temp = "This is an update for this recipe.";
        // Create a recipe update
        RecipeUpdateEntity recipeUpdate = new RecipeUpdateEntity(0, recipeId1, temp);
        // insert the recipe update
        cookbookDao.insertRecipeUpdate(recipeUpdate);
        // get recipe update
        RecipeUpdateEntity recipeUpdate1 = cookbookDao.getRecipeUpdate(recipeId1);
        // modify the update
        String temp1 = "Updated update";
        recipeUpdate1.setUpdate(temp1);
        // save to database
        cookbookDao.updateRecipeUpdate(recipeUpdate1);
        // get modified update
        RecipeUpdateEntity recipeUpdate2 = cookbookDao.getRecipeUpdate(recipeId1);
        // test
        assertEquals(temp1, recipeUpdate2.getUpdate());
    }
}
