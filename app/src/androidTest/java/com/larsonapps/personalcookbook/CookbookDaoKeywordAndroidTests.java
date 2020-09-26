package com.larsonapps.personalcookbook;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;
import com.larsonapps.personalcookbook.data.KeywordEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CookbookDaoKeywordAndroidTests {
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
    private static final int KEYWORD_ID_VALUE_1 = 0;
    private static final String KEYWORD_VALUE_1 = "beef";
    private static final int KEYWORD_ID_VALUE_2 = 0;
    private static final String KEYWORD_VALUE_2 = "pork";
    private static final int KEYWORD_ID_VALUE_3 = 0;
    private static final String KEYWORD_VALUE_3 = "chicken";
    private static final int KEYWORD_ID_VALUE_4 = 0;
    private static final String KEYWORD_VALUE_4 = "beef";
    // declare variables
    RecipeEntity recipeEntity1 = new RecipeEntity(0, NAME_VALUE_1, DESCRIPTION_VALUE_1,
            SERVINGS_VALUE_1, PREP_TIME_VALUE_1, COOK_TIME_VALUE_1, TOTAL_TIME_VALUE_1,
            NOTES_VALUE_1, COPYRIGHT_VALUE_1);
    RecipeEntity recipeEntity2 = new RecipeEntity(0, NAME_VALUE_2, DESCRIPTION_VALUE_2,
            SERVINGS_VALUE_2, PREP_TIME_VALUE_2, COOK_TIME_VALUE_2, TOTAL_TIME_VALUE_2,
            NOTES_VALUE_2, COPYRIGHT_VALUE_2);
    KeywordEntity keyword1;
    KeywordEntity keyword2;
    KeywordEntity keyword3;
    KeywordEntity keyword4;
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
        keyword1 = new KeywordEntity(KEYWORD_ID_VALUE_1, recipeId1, KEYWORD_VALUE_1);
        keyword2 = new KeywordEntity(KEYWORD_ID_VALUE_2, recipeId1, KEYWORD_VALUE_2);
        keyword3 = new KeywordEntity(KEYWORD_ID_VALUE_3, recipeId2, KEYWORD_VALUE_3);
        keyword4 = new KeywordEntity(KEYWORD_ID_VALUE_4, recipeId2, KEYWORD_VALUE_4);
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testInsertAllKeywordsAndGetAllKeywords() {
        // insert all keywords
        List<KeywordEntity> keywords = new ArrayList<>();
        keywords.add(keyword1);
        keywords.add(keyword2);
        keywords.add(keyword3);
        // add to database
        cookbookDao.insertAllKeywords(keywords);
        // get all keywords for recipe 1
        List<KeywordEntity> keywords1 = cookbookDao.getAllKeywords(recipeId1);
        // get all keywords for recipe 2
        List<KeywordEntity> keywords2 = cookbookDao.getAllKeywords(recipeId2);
        // test
        assertEquals(2, keywords1.size());
        assertEquals(1, keywords2.size());
    }

    @Test
    public void testInsertKeyword() {
        // insert keyword into database
        cookbookDao.insertKeyword(keyword1);
        // get keywords for recipe 1
        List<KeywordEntity> keywords = cookbookDao.getAllKeywords(recipeId1);
        // test
        assertEquals(1, keywords.size());
        assertEquals(recipeId1, keywords.get(0).getRecipeId());
        assertEquals(KEYWORD_VALUE_1, keywords.get(0).getKeyword());
        // get keywords for recipe 2
        List<KeywordEntity> keywords1 = cookbookDao.getAllKeywords(recipeId2);
        // test
        assertEquals(0, keywords1.size());
    }

    @Test
    public void testDeleteKeyword() {
        // insert keyword into database
        cookbookDao.insertKeyword(keyword1);
        // get keywords for recipe 1
        List<KeywordEntity> keywords = cookbookDao.getAllKeywords(recipeId1);
        assertEquals(1, keywords.size());
        // delete keyword
        cookbookDao.deleteKeyword(keywords.get(0));
        // get keywords for recipe 1
        List<KeywordEntity> keywords1 = cookbookDao.getAllKeywords(recipeId1);
        // test
        assertEquals(0, keywords1.size());
    }

    @Test
    public void testUpdateKeyword() {
        // insert keyword into database
        cookbookDao.insertKeyword(keyword1);
        // get keywords for recipe 1
        List<KeywordEntity> keywords = cookbookDao.getAllKeywords(recipeId1);
        assertEquals(1, keywords.size());
        // modify keyword
        String temp = "vegan";
        keywords.get(0).setKeyword(temp);
        cookbookDao.updateKeyword(keywords.get(0));
        // get keywords for recipe 1
        List<KeywordEntity> keywords1 = cookbookDao.getAllKeywords(recipeId1);
        // test
        assertEquals(1, keywords1.size());
        assertEquals(temp, keywords1.get(0).getKeyword());
    }

    @Test
    public void testGetAllRecipesByKeyword() {
        // insert all keywords
        List<KeywordEntity> keywords = new ArrayList<>();
        keywords.add(keyword1);
        keywords.add(keyword2);
        keywords.add(keyword3);
        keywords.add(keyword4);
        // add to database
        cookbookDao.insertAllKeywords(keywords);
        // get recipes by keyword beef
        List<RecipeEntity> recipes = cookbookDao.getAllRecipes("beef");
        // test
        assertEquals(2, recipes.size());
        // get recipes by keyword chicken
        List<RecipeEntity> recipes1 = cookbookDao.getAllRecipes("chicken");
        // test
        assertEquals(1, recipes1.size());
        // get recipes by keyword vegan
        List<RecipeEntity> recipes2 = cookbookDao.getAllRecipes("vegan");
        // test
        assertEquals(0, recipes2.size());
    }

    @Test
    public void testGetAllRecipesByArrayOfKeywords() {
        // insert all keywords
        List<KeywordEntity> keywords = new ArrayList<>();
        keywords.add(keyword1);
        keywords.add(keyword2);
        keywords.add(keyword3);
        keywords.add(keyword4);
        // add to database
        cookbookDao.insertAllKeywords(keywords);
        // get recipes by keyword chicken and pork
        String[] temp = {"chicken", "pork"};
        List<RecipeEntity> recipes = cookbookDao.getAllRecipes(temp);
        // test
        assertEquals(2, recipes.size());
        // get recipes by keyword vegan and dinner
        String[] temp1 = {"vegan", "dinner"};
        List<RecipeEntity> recipes1 = cookbookDao.getAllRecipes(temp1);
        // test
        assertEquals(0, recipes1.size());
    }
}
