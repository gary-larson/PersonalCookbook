package com.larsonapps.personalcookbook;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;
import com.larsonapps.personalcookbook.data.RecipeEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class CookbookDaoRecipeAndroidTests {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    // Declare constants
    private CookbookDao cookbookDao;
    private CookbookRoomDatabase db;
    private static final String NAME_VALUE_1 = "Chocolate Cookies";
    private static final String SHORT_DESCRIPTION_VALUE_1 = "Short description";
    private static final String DESCRIPTION_VALUE_1 = "Sufficient description";
    private static final int SERVINGS_VALUE_1 = 12;
    private static final int PREP_TIME_VALUE_1 = 40;
    private static final int COOK_TIME_VALUE_1 = 12;
    private static final int TOTAL_TIME_VALUE_1 = 52;
    private static final String NOTES_VALUE_1 = "These are the notes by the cook.";
    private static final String COPYRIGHT_VALUE_1 = "copyright 5201";
    private static final String PERSONAL_NOTE_VALUE_1 = "Personal note";
    private static final String NAME_VALUE_2 = "Apple Pie";
    private static final String SHORT_DESCRIPTION_VALUE_2 = "another short description";
    private static final String DESCRIPTION_VALUE_2 = "Old fashioned apple pie";
    private static final int SERVINGS_VALUE_2 = 8;
    private static final int PREP_TIME_VALUE_2 = 35;
    private static final int COOK_TIME_VALUE_2 = 60;
    private static final int TOTAL_TIME_VALUE_2 = 95;
    private static final String NOTES_VALUE_2 = "These are apple pie notes by the cook.";
    private static final String COPYRIGHT_VALUE_2 = "copyright 2020";
    private static final String PERSONAL_NOTE_VALUE_2 = "Personal note two";
    // declare variables
    RecipeEntity recipeEntity1 = new RecipeEntity(0, NAME_VALUE_1, SHORT_DESCRIPTION_VALUE_1,
            DESCRIPTION_VALUE_1, SERVINGS_VALUE_1, PREP_TIME_VALUE_1, COOK_TIME_VALUE_1,
            TOTAL_TIME_VALUE_1, NOTES_VALUE_1, COPYRIGHT_VALUE_1, PERSONAL_NOTE_VALUE_1);
    RecipeEntity recipeEntity2 = new RecipeEntity(0, NAME_VALUE_2, SHORT_DESCRIPTION_VALUE_2,
            DESCRIPTION_VALUE_2, SERVINGS_VALUE_2, PREP_TIME_VALUE_2, COOK_TIME_VALUE_2,
            TOTAL_TIME_VALUE_2, NOTES_VALUE_2, COPYRIGHT_VALUE_2, PERSONAL_NOTE_VALUE_2);
    int recipeId1;
    int recipeId2;


    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        // this tests insert recipe
        db = Room.inMemoryDatabaseBuilder(context, CookbookRoomDatabase.class).build();
        cookbookDao = db.cookbookDao();
        // insert recipe 1
        cookbookDao.insertRecipe(recipeEntity1);
        recipeId1 = cookbookDao.getRecipeIdByName(NAME_VALUE_1);
        // add another recipe
        cookbookDao.insertRecipe((recipeEntity2));
        recipeId2 = cookbookDao.getRecipeIdByName(NAME_VALUE_2);
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testGetAllRecipes() {
        // get all recipes
        cookbookDao.getAllRecipes().observeForever(newRecipes -> {
            assertEquals(2, newRecipes.size());
            // test all variables except id which is auto generated
            int position = 0;
            if (newRecipes.get(0).getId() != recipeId1) {
                    position = 1;
            }
            assertEquals(NAME_VALUE_1, newRecipes.get(position).getName());
            assertEquals(SHORT_DESCRIPTION_VALUE_1, newRecipes.get(position).getShortDescription());
            assertEquals(DESCRIPTION_VALUE_1, newRecipes.get(position).getDescription());
            assertEquals(SERVINGS_VALUE_1, newRecipes.get(position).getServings());
            assertEquals(PREP_TIME_VALUE_1, newRecipes.get(position).getPrepTime());
            assertEquals(COOK_TIME_VALUE_1, newRecipes.get(position).getCookTime());
            assertEquals(TOTAL_TIME_VALUE_1, newRecipes.get(position).getTotalTime());
            assertEquals(NOTES_VALUE_1, newRecipes.get(position).getCookNotes());
            assertEquals(COPYRIGHT_VALUE_1, newRecipes.get(position).getCopyright());
            assertEquals(PERSONAL_NOTE_VALUE_1, newRecipes.get(position).getPersonalNote());
        });
    }

    @Test
    public void testGetRecipe() {
        // get recipe by id
        cookbookDao.getRecipe(recipeId1).observeForever(newRecipe -> {
            // test all variables
            assertEquals(recipeId1, newRecipe.getId());
            assertEquals(NAME_VALUE_1, newRecipe.getName());
            assertEquals(DESCRIPTION_VALUE_1, newRecipe.getDescription());
            assertEquals(SHORT_DESCRIPTION_VALUE_1, newRecipe.getShortDescription());
            assertEquals(SERVINGS_VALUE_1, newRecipe.getServings());
            assertEquals(PREP_TIME_VALUE_1, newRecipe.getPrepTime());
            assertEquals(COOK_TIME_VALUE_1, newRecipe.getCookTime());
            assertEquals(TOTAL_TIME_VALUE_1, newRecipe.getTotalTime());
            assertEquals(NOTES_VALUE_1, newRecipe.getCookNotes());
            assertEquals(COPYRIGHT_VALUE_1, newRecipe.getCopyright());
            assertEquals(PERSONAL_NOTE_VALUE_1, newRecipe.getPersonalNote());
        });
    }

    @Test
    public void testDeleteRecipe() {
        // get recipe id
        int recipeId = cookbookDao.getRecipeIdByName(NAME_VALUE_1);
        recipeEntity1.setId(recipeId);
        // delete recipe
        cookbookDao.deleteRecipe(recipeEntity1);
        // get all recipes and test
        cookbookDao.getAllRecipes().observeForever(newRecipes -> {
            assertEquals(1, newRecipes.size());
            assertNotEquals(recipeId1, newRecipes.get(0).getId());
        });
    }

    @Test
    public void testUpdateRecipe() {
        // get recipe id
        int recipeId = cookbookDao.getRecipeIdByName(NAME_VALUE_1);
        recipeEntity1.setId(recipeId);
        // change recipe
        recipeEntity1.setCookTime(500);
        // update database
        cookbookDao.updateRecipe(recipeEntity1);
        // get updated record and test
        cookbookDao.getRecipe(recipeId1).observeForever(newRecipe ->
            assertEquals(500, newRecipe.getCookTime()));
    }
}
