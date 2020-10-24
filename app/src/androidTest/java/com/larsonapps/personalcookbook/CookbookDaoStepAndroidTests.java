package com.larsonapps.personalcookbook;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.StepEntity;

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
public class CookbookDaoStepAndroidTests {
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
    private static final int STEP_ID_VALUE_1 = 0;
    private static final int NUMBER_VALUE_1 = 5;
    private static final String INSTRUCTION_VALUE_1 = "please follow instruction";
    private static final String STEP_PERSONAL_NOTE_VALUE_1 = "Step Personal note";
    private static final int STEP_ID_VALUE_2 = 0;
    private static final int NUMBER_VALUE_2 = 6;
    private static final String INSTRUCTION_VALUE_2 = "combine ingredients";
    private static final String STEP_PERSONAL_NOTE_VALUE_2 = "Step Personal note two";
    private static final int STEP_ID_VALUE_3 = 0;
    private static final int NUMBER_VALUE_3 = 7;
    private static final String INSTRUCTION_VALUE_3 = "cook for 30 minutes";
    private static final String STEP_PERSONAL_NOTE_VALUE_3 = "Step Personal note three";
    // declare variables
    RecipeEntity recipeEntity1 = new RecipeEntity(0, NAME_VALUE_1, SHORT_DESCRIPTION_1,
            DESCRIPTION_VALUE_1, SERVINGS_VALUE_1, PREP_TIME_VALUE_1, COOK_TIME_VALUE_1,
            TOTAL_TIME_VALUE_1, COPYRIGHT_VALUE_1, PERSONAL_NOTE_VALUE_1);
    RecipeEntity recipeEntity2 = new RecipeEntity(0, NAME_VALUE_2, SHORT_DESCRIPTION_2,
            DESCRIPTION_VALUE_2, SERVINGS_VALUE_2, PREP_TIME_VALUE_2, COOK_TIME_VALUE_2,
            TOTAL_TIME_VALUE_2, COPYRIGHT_VALUE_2, PERSONAL_NOTE_VALUE_2);
    StepEntity step1;
    StepEntity step2;
    StepEntity step3;
    int recipeId1;
    int recipeId2;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, CookbookRoomDatabase.class).build();
        cookbookDao = db.cookbookDao();
        // insert recipe 1
        cookbookDao.insertRecipe(recipeEntity1);
        recipeId1 = cookbookDao.getRecipeId(NAME_VALUE_1);
        // add another recipe
        cookbookDao.insertRecipe((recipeEntity2));
        recipeId2 = cookbookDao.getRecipeId(NAME_VALUE_2);
        step1 = new StepEntity(STEP_ID_VALUE_1, recipeId1, NUMBER_VALUE_1, INSTRUCTION_VALUE_1,
                STEP_PERSONAL_NOTE_VALUE_1);
        step2 = new StepEntity(STEP_ID_VALUE_2, recipeId1, NUMBER_VALUE_2, INSTRUCTION_VALUE_2,
                STEP_PERSONAL_NOTE_VALUE_2);
        step3 = new StepEntity(STEP_ID_VALUE_3, recipeId2, NUMBER_VALUE_3, INSTRUCTION_VALUE_3,
                STEP_PERSONAL_NOTE_VALUE_3);
        // this tests insert step
        cookbookDao.insertStep(step1);
        cookbookDao.insertStep(step2);
        cookbookDao.insertStep(step3);
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testInsertAllStepsAndGetAllSteps() {
        // insert all steps
        List<StepEntity> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        // add to database
        cookbookDao.insertAllSteps(steps);
        // get all steps for recipe 1 and test
        cookbookDao.getAllSteps(recipeId1).observeForever(newSteps ->
                assertEquals(4, newSteps.size()));
    }

    @Test
    public void testDeleteStepAndGetStepIdByNumber() {
        int stepId = cookbookDao.getStepIdByNumber(recipeId1, NUMBER_VALUE_1);
        step1.setStepId(stepId);
        // delete step
        cookbookDao.deleteStep(step1);
        // get steps for recipe 1 and test
        cookbookDao.getAllSteps(recipeId1).observeForever(newSteps -> {
            for(StepEntity step : newSteps) {
                assertNotEquals(stepId, step.getStepId());
            }
        });
    }

    @Test
    public void testUpdateStep() {
        int stepId = cookbookDao.getStepIdByNumber(recipeId1, NUMBER_VALUE_1);
        step1.setStepId(stepId);
        // modify step
        String temp = "add dry ingredients";
        step1.setInstruction(temp);
        cookbookDao.updateStep(step1);
        // get steps for recipe 1 and test
        cookbookDao.getAllSteps(recipeId1).observeForever(newSteps -> {
            boolean isAsserted = false;
            for(StepEntity step : newSteps) {
                if (stepId == step.getStepId()) {
                    assertEquals(temp, step.getInstruction());
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
