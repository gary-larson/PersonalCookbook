package com.larsonapps.personalcookbook;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;
import com.larsonapps.personalcookbook.data.KeywordEntity;
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
public class CookbookDaoKeywordAndroidTests {
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
    private static final String PERSONAL_NOTE_VALUE_2 = "personal note two";
    private static final int KEYWORD_ID_VALUE_1 = 0;
    private static final String KEYWORD_VALUE_1 = "beef";
    private static final int KEYWORD_ID_VALUE_2 = 0;
    private static final String KEYWORD_VALUE_2 = "pork";
    private static final int KEYWORD_ID_VALUE_3 = 0;
    private static final String KEYWORD_VALUE_3 = "chicken";
    private static final int KEYWORD_ID_VALUE_4 = 0;
    private static final String KEYWORD_VALUE_4 = "beef";
    // declare variables
    RecipeEntity recipeEntity1 = new RecipeEntity(0, NAME_VALUE_1, SHORT_DESCRIPTION_1,
            DESCRIPTION_VALUE_1, SERVINGS_VALUE_1, PREP_TIME_VALUE_1, COOK_TIME_VALUE_1,
            TOTAL_TIME_VALUE_1, NOTES_VALUE_1, COPYRIGHT_VALUE_1, PERSONAL_NOTE_VALUE_1);
    RecipeEntity recipeEntity2 = new RecipeEntity(0, NAME_VALUE_2, SHORT_DESCRIPTION_2,
            DESCRIPTION_VALUE_2, SERVINGS_VALUE_2, PREP_TIME_VALUE_2, COOK_TIME_VALUE_2,
            TOTAL_TIME_VALUE_2, NOTES_VALUE_2, COPYRIGHT_VALUE_2, PERSONAL_NOTE_VALUE_2);
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
        cookbookDao = db.cookbookDao();
        // insert recipe 1
        cookbookDao.insertRecipe(recipeEntity1);
        recipeId1 = cookbookDao.getRecipeIdByName(NAME_VALUE_1);
        // add another recipe
        cookbookDao.insertRecipe((recipeEntity2));
        recipeId2 = cookbookDao.getRecipeIdByName(NAME_VALUE_2);
        keyword1 = new KeywordEntity(KEYWORD_ID_VALUE_1, recipeId1, KEYWORD_VALUE_1);
        keyword2 = new KeywordEntity(KEYWORD_ID_VALUE_2, recipeId1, KEYWORD_VALUE_2);
        keyword3 = new KeywordEntity(KEYWORD_ID_VALUE_3, recipeId2, KEYWORD_VALUE_3);
        keyword4 = new KeywordEntity(KEYWORD_ID_VALUE_4, recipeId2, KEYWORD_VALUE_4);
        // this tests insert keyword
        cookbookDao.insertKeyword(keyword1);
        cookbookDao.insertKeyword(keyword2);
        cookbookDao.insertKeyword(keyword3);
        cookbookDao.insertKeyword(keyword4);
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
        // get all keywords for recipe 1 and test
        cookbookDao.getAllKeywords(recipeId1).observeForever(newKeywords -> {
            boolean isAsserted = false;
            assertEquals(4, newKeywords.size());
            for(KeywordEntity keyword : newKeywords) {
                if (keyword.getKeyword().equals(KEYWORD_VALUE_1)) {
                    assertEquals(recipeId1, keyword.getRecipeId());
                    isAsserted = true;
                    break;
                }
            }
            if (!isAsserted) {
                assertEquals(1, 2);
            }
        });
    }

    @Test
    public void testDeleteKeyword() {
        int keywordId = cookbookDao.getKeywordIdByKeyword(recipeId1, keyword1.getKeyword());
        keyword1.setKeywordId(keywordId);
        // delete keyword
        cookbookDao.deleteKeyword(keyword1);
        // get keywords for recipe 1 and test
        cookbookDao.getAllKeywords(recipeId1).observeForever(newKeywords -> {
           for (KeywordEntity keyword : newKeywords) {
               assertNotEquals(keywordId, keyword.getKeywordId());
           }
        });
    }

    @Test
    public void testGetAllRecipesByKeyword() {
        // get recipes by keyword beef
        cookbookDao.getAllRecipes("beef").observeForever(newRecipes ->
            assertEquals(2, newRecipes.size()));
        // get recipes by keyword chicken
        cookbookDao.getAllRecipes("chicken").observeForever(newRecipes ->
                assertEquals(1, newRecipes.size()));
        // get recipes by keyword vegan
        cookbookDao.getAllRecipes("vegan").observeForever(newRecipes ->
                assertEquals(0, newRecipes.size()));
    }

    @Test
    public void testGetAllRecipesByArrayOfKeywords() {
        // get recipes by keyword chicken and pork
        String[] temp = {"chicken", "pork"};
        cookbookDao.getAllRecipes(temp).observeForever(newRecipes ->
                assertEquals(2, newRecipes.size()));
        // get recipes by keyword vegan and dinner
        String[] temp1 = {"vegan", "dinner"};
        cookbookDao.getAllRecipes(temp1).observeForever(newRecipes ->
                assertEquals(0, newRecipes.size()));
    }
}
