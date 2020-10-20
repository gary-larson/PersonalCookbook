package com.larsonapps.personalcookbook;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.larsonapps.personalcookbook.data.CookNoteEntity;
import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;
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
public class CookbookDaoCookNoteAndroidTests {
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
    private static final String COPYRIGHT_VALUE_1 = "copyright 5201";
    private static final String PERSONAL_NOTE_VALUE_1 = "Personal note";
    private static final String NAME_VALUE_2 = "Apple Pie";
    private static final String SHORT_DESCRIPTION_2 = "another short description";
    private static final String DESCRIPTION_VALUE_2 = "Old fashioned apple pie";
    private static final int SERVINGS_VALUE_2 = 8;
    private static final int PREP_TIME_VALUE_2 = 35;
    private static final int COOK_TIME_VALUE_2 = 60;
    private static final int TOTAL_TIME_VALUE_2 = 95;
    private static final String COPYRIGHT_VALUE_2 = "copyright 2020";
    private static final String PERSONAL_NOTE_VALUE_2 = "Personal note two";
    private static final int COOK_NOTE_ID_VALUE_1 = 0;
    private static final int NUMBER_VALUE_1 = 15;
    private static final String NOTE_VALUE_1 = "sprinkle sparely";
    private static final int COOK_NOTE_ID_VALUE_2 = 0;
    private static final int NUMBER_VALUE_2 = 106;
    private static final String NOTE_VALUE_2 = "combine ingredients by flipping";
    private static final int COOK_NOTE_ID_VALUE_3 = 0;
    private static final int NUMBER_VALUE_3 = 107;
    private static final String NOTE_VALUE_3 = "cook for 30 minutes over low heat";
    // declare variables
    RecipeEntity recipeEntity1 = new RecipeEntity(0, NAME_VALUE_1, SHORT_DESCRIPTION_1,
            DESCRIPTION_VALUE_1, SERVINGS_VALUE_1, PREP_TIME_VALUE_1, COOK_TIME_VALUE_1,
            TOTAL_TIME_VALUE_1, COPYRIGHT_VALUE_1, PERSONAL_NOTE_VALUE_1);
    RecipeEntity recipeEntity2 = new RecipeEntity(0, NAME_VALUE_2, SHORT_DESCRIPTION_2,
            DESCRIPTION_VALUE_2, SERVINGS_VALUE_2, PREP_TIME_VALUE_2, COOK_TIME_VALUE_2,
            TOTAL_TIME_VALUE_2, COPYRIGHT_VALUE_2, PERSONAL_NOTE_VALUE_2);
    CookNoteEntity cookNote1;
    CookNoteEntity cookNote2;
    CookNoteEntity cookNote3;
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
        cookNote1 = new CookNoteEntity(COOK_NOTE_ID_VALUE_1, recipeId1, NOTE_VALUE_1,
                NUMBER_VALUE_1);
        cookNote2 = new CookNoteEntity(COOK_NOTE_ID_VALUE_2, recipeId1, NOTE_VALUE_2,
                NUMBER_VALUE_2);
        cookNote3 = new CookNoteEntity(COOK_NOTE_ID_VALUE_3, recipeId2, NOTE_VALUE_3,
                NUMBER_VALUE_3);
        // this tests insert step
        cookbookDao.insertCookNote(cookNote1);
        cookbookDao.insertCookNote(cookNote2);
        cookbookDao.insertCookNote(cookNote3);
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testInsertAllCookNotesAndGetAllCookNotes() {
        // insert all cook's notes
        List<CookNoteEntity> cookNotes = new ArrayList<>();
        cookNotes.add(cookNote1);
        cookNotes.add(cookNote2);
        cookNotes.add(cookNote3);
        // add to database
        cookbookDao.insertAllNotes(cookNotes);
        // get all cook's notes for recipe 1 and test
        cookbookDao.getAllCookNotes(recipeId1).observeForever(newCookNotes ->
                assertEquals(4, newCookNotes.size()));
    }

    @Test
    public void testDeleteCookNoteAndGetCookNoteIdByNumber() {
        int cookNoteId = cookbookDao.getCookNoteByNumber(recipeId1, NUMBER_VALUE_1);
        cookNote1.setCookNoteId(cookNoteId);
        // delete cook note
        cookbookDao.deleteCookNote(cookNote1);
        // get cook notes for recipe 1 and test
        cookbookDao.getAllCookNotes(recipeId1).observeForever(newCookNotes -> {
            for(CookNoteEntity cookNote : newCookNotes) {
                assertNotEquals(cookNoteId, cookNote.getCookNoteId());
            }
        });
    }

    @Test
    public void testUpdateCookNote() {
        int cookNoteId = cookbookDao.getCookNoteByNumber(recipeId1, NUMBER_VALUE_1);
        cookNote1.setCookNoteId(cookNoteId);
        // modify cook note
        String temp = "add dry ingredients";
        cookNote1.setNote(temp);
        cookbookDao.updateCoolNote(cookNote1);
        // get cook notes for recipe 1 and test
        cookbookDao.getAllCookNotes(recipeId1).observeForever(newCookNotes -> {
            boolean isAsserted = false;
            for(CookNoteEntity cookNote : newCookNotes) {
                if (cookNoteId == cookNote.getCookNoteId()) {
                    assertEquals(temp, cookNote.getNote());
                    isAsserted = true;
                    break;
                }
            }
            if (!isAsserted) {
                assertEquals(1,2);
            }
        });
    }
}
