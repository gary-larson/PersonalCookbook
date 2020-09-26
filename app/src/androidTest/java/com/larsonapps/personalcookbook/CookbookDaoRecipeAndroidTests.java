package com.larsonapps.personalcookbook;

import android.content.Context;

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
    // declare variables
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
        List<RecipeEntity> recipeEntities = cookbookDao.getAllRecipes();
        // test size
        assertEquals(1, recipeEntities.size());
        // get recipe id
        recipeId1 = recipeEntities.get(0).getId();
        // add another recipe
        cookbookDao.insertRecipe((recipeEntity2));
        // get all recipes
        recipeEntities = cookbookDao.getAllRecipes();
        // test size
        assertEquals(2, recipeEntities.size());
        if (recipeEntities.get(0).getId() == recipeId1) {
            recipeId2 = recipeEntities.get(1).getId();
        } else {
            recipeId2 = recipeEntities.get(0).getId();
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
        List<RecipeEntity> recipeEntities = cookbookDao.getAllRecipes();
        // test size
        assertEquals(3, recipeEntities.size());
        // test all variables except id which is auto generated
        if (recipeEntities.get(0).getId() == recipeId1) {
            assertEquals(NAME_VALUE_1, recipeEntities.get(0).getName());
            assertEquals(DESCRIPTION_VALUE_1, recipeEntities.get(0).getDescription());
            assertEquals(SERVINGS_VALUE_1, recipeEntities.get(0).getServings());
            assertEquals(PREP_TIME_VALUE_1, recipeEntities.get(0).getPrepTime());
            assertEquals(COOK_TIME_VALUE_1, recipeEntities.get(0).getCookTime());
            assertEquals(TOTAL_TIME_VALUE_1, recipeEntities.get(0).getTotalTime());
            assertEquals(NOTES_VALUE_1, recipeEntities.get(0).getNotes());
            assertEquals(COPYRIGHT_VALUE_1, recipeEntities.get(0).getCoypright());
        } else {
            assertEquals(NAME_VALUE_2, recipeEntities.get(0).getName());
            assertEquals(DESCRIPTION_VALUE_2, recipeEntities.get(0).getDescription());
            assertEquals(SERVINGS_VALUE_2, recipeEntities.get(0).getServings());
            assertEquals(PREP_TIME_VALUE_2, recipeEntities.get(0).getPrepTime());
            assertEquals(COOK_TIME_VALUE_2, recipeEntities.get(0).getCookTime());
            assertEquals(TOTAL_TIME_VALUE_2, recipeEntities.get(0).getTotalTime());
            assertEquals(NOTES_VALUE_2, recipeEntities.get(0).getNotes());
            assertEquals(COPYRIGHT_VALUE_2, recipeEntities.get(0).getCoypright());
        }
    }

    @Test
    public void testGetRecipe() {
        // get recipe by id
        RecipeEntity recipeEntity = cookbookDao.getRecipe(recipeId1);
        // test all variables
        assertEquals(recipeId1, recipeEntity.getId());
        assertEquals(NAME_VALUE_1, recipeEntity.getName());
        assertEquals(DESCRIPTION_VALUE_1, recipeEntity.getDescription());
        assertEquals(SERVINGS_VALUE_1, recipeEntity.getServings());
        assertEquals(PREP_TIME_VALUE_1, recipeEntity.getPrepTime());
        assertEquals(COOK_TIME_VALUE_1, recipeEntity.getCookTime());
        assertEquals(TOTAL_TIME_VALUE_1, recipeEntity.getTotalTime());
        assertEquals(NOTES_VALUE_1, recipeEntity.getNotes());
        assertEquals(COPYRIGHT_VALUE_1, recipeEntity.getCoypright());
    }

    @Test
    public void testDeleteRecipe() {
        // get recipe
        RecipeEntity recipeEntity = cookbookDao.getRecipe(recipeId1);
        // delete recipe
        cookbookDao.deleteRecipe(recipeEntity);
        // get all recipes
        List<RecipeEntity> recipeEntities = cookbookDao.getAllRecipes();
        // test for recipe
        for (RecipeEntity recipe : recipeEntities) {
            assertNotEquals(recipeId1, recipe.getId());
        }
    }

    @Test
    public void testUpdateRecipe() {
        // get recipe
        RecipeEntity recipeEntity = cookbookDao.getRecipe(recipeId1);
        // change recipe
        recipeEntity.setCookTime(500);
        // update database
        cookbookDao.updateRecipe(recipeEntity);
        // get updated record
        RecipeEntity recipeEntity1 = cookbookDao.getRecipe(recipeId1);
        // test record
        assertEquals(500, recipeEntity1.getCookTime());
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
        RecipeUpdateEntity recipeUpdateEntity = cookbookDao.getRecipeUpdate(recipeId1);
        // test update
        assertEquals(temp, recipeUpdateEntity.getUpdate());
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
        RecipeUpdateEntity recipeUpdateEntity = cookbookDao.getRecipeUpdate(recipeId1);
        // delete the recipe update
        cookbookDao.deleteRecipeUpdate(recipeUpdateEntity);
        // get the update
        RecipeUpdateEntity recipeUpdateEntity1 = cookbookDao.getRecipeUpdate(recipeId1);
        // test
        assertNull(recipeUpdateEntity1);
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
        RecipeUpdateEntity recipeUpdateEntity = cookbookDao.getRecipeUpdate(recipeId1);
        // modify the update
        String temp1 = "Updated update";
        recipeUpdateEntity.setUpdate(temp1);
        // save to database
        cookbookDao.updateRecipeUpdate(recipeUpdateEntity);
        // get modified update
        RecipeUpdateEntity recipeUpdateEntity1 = cookbookDao.getRecipeUpdate(recipeId1);
        // test
        assertEquals(temp1, recipeUpdateEntity1.getUpdate());
    }
}
