package com.larsonapps.personalcookbook;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.data.StepUpdateEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class CookbookDaoStepAndroidTests {
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
    private static final int STEP_ID_VALUE_1 = 0;
    private static final int NUMBER_VALUE_1 = 5;
    private static final String INSTRUCTION_VALUE_1 = "please follow instruction";
    private static final int STEP_ID_VALUE_2 = 0;
    private static final int NUMBER_VALUE_2 = 6;
    private static final String INSTRUCTION_VALUE_2 = "combine ingredients";
    private static final int STEP_ID_VALUE_3 = 0;
    private static final int NUMBER_VALUE_3 = 7;
    private static final String INSTRUCTION_VALUE_3 = "cook for 30 minutes";
    // declare variables
    // TODO add short description for database version 2
    RecipeEntity recipeEntity1 = new RecipeEntity(0, NAME_VALUE_1, DESCRIPTION_VALUE_1,
            SERVINGS_VALUE_1, PREP_TIME_VALUE_1, COOK_TIME_VALUE_1, TOTAL_TIME_VALUE_1,
            NOTES_VALUE_1, COPYRIGHT_VALUE_1);
    RecipeEntity recipeEntity2 = new RecipeEntity(0, NAME_VALUE_2, DESCRIPTION_VALUE_2,
            SERVINGS_VALUE_2, PREP_TIME_VALUE_2, COOK_TIME_VALUE_2, TOTAL_TIME_VALUE_2,
            NOTES_VALUE_2, COPYRIGHT_VALUE_2);
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
        // get all records
        LiveData<List<RecipeEntity>> liveRecipes = cookbookDao.getAllRecipes();
        List<RecipeEntity> recipes = liveRecipes.getValue();
        // test size
        assert recipes != null;
        assertEquals(1, recipes.size());
        // get recipe id 1
        recipeId1 = recipes.get(0).getId();
        // add another recipe
        cookbookDao.insertRecipe((recipeEntity2));
        // get all recipes
        liveRecipes = cookbookDao.getAllRecipes();
        recipes = liveRecipes.getValue();
        // test size
        assert recipes != null;
        assertEquals(2, recipes.size());
        // assign recipe id 3
        if (recipes.get(0).getId() == recipeId1) {
            recipeId2 = recipes.get(1).getId();
        } else {
            recipeId2 = recipes.get(0).getId();
        }
        step1 = new StepEntity(STEP_ID_VALUE_1, recipeId1, NUMBER_VALUE_1, INSTRUCTION_VALUE_1);
        step2 = new StepEntity(STEP_ID_VALUE_2, recipeId1, NUMBER_VALUE_2, INSTRUCTION_VALUE_2);
        step3 = new StepEntity(STEP_ID_VALUE_3, recipeId2, NUMBER_VALUE_3, INSTRUCTION_VALUE_3);
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
        // get all steps for recipe 1
        LiveData<List<StepEntity>> liveSteps1 = cookbookDao.getAllSteps(recipeId1);
        List<StepEntity> steps1 = liveSteps1.getValue();
        // get all ingredients for recipe 2
        LiveData<List<StepEntity>> liveSteps2 = cookbookDao.getAllSteps(recipeId2);
        List<StepEntity> steps2 = liveSteps2.getValue();
        // test
        assert steps1 != null;
        assertEquals(2, steps1.size());
        assert steps2 != null;
        assertEquals(1, steps2.size());
    }

    @Test
    public void testInsetStep() {
        // insert step to database
        cookbookDao.insertStep(step1);
        // get ingredients for recipe 1
        LiveData<List<StepEntity>> liveSteps = cookbookDao.getAllSteps(recipeId1);
        List<StepEntity> steps = liveSteps.getValue();
        // test
        assert steps != null;
        assertEquals(1, steps.size());
        assertEquals(recipeId1, steps.get(0).getRecipeId());
        assertEquals(NUMBER_VALUE_1, steps.get(0).getNumber());
        assertEquals(INSTRUCTION_VALUE_1, steps.get(0).getInstruction());
        // get steps for recipe 2
        LiveData<List<StepEntity>> liveSteps1 = cookbookDao.getAllSteps(recipeId2);
        List<StepEntity> steps1 = liveSteps1.getValue();
        // test
        assert steps1 != null;
        assertEquals(0, steps1.size());
    }

    @Test
    public void testDeleteStep() {
        // insert step into database
        cookbookDao.insertStep(step1);
        // get steps for recipe 1
        LiveData<List<StepEntity>>liveSteps = cookbookDao.getAllSteps(recipeId1);
        List<StepEntity> steps = liveSteps.getValue();
        assert steps != null;
        assertEquals(1, steps.size());
        // delete step
        cookbookDao.deleteStep(steps.get(0));
        // get steps for recipe 1
        LiveData<List<StepEntity>> liveSteps1 = cookbookDao.getAllSteps(recipeId1);
        List<StepEntity> steps1 = liveSteps1.getValue();
        // test
        assert steps1 != null;
        assertEquals(0, steps1.size());
    }

    @Test
    public void testUpdateStep() {
        // insert step to database
        cookbookDao.insertStep(step1);
        // get steps for recipe 1
        LiveData<List<StepEntity>> liveSteps = cookbookDao.getAllSteps(recipeId1);
        List<StepEntity> steps = liveSteps.getValue();
        assert steps != null;
        assertEquals(1, steps.size());
        // modify step
        String temp = "add dry ingredients";
        steps.get(0).setInstruction(temp);
        cookbookDao.updateStep(steps.get(0));
        // get steps for recipe 1
        LiveData<List<StepEntity>> liveSteps1 = cookbookDao.getAllSteps(recipeId1);
        List<StepEntity> steps1 = liveSteps1.getValue();
        // test
        assert steps1 != null;
        assertEquals(1, steps1.size());
        assertEquals(temp, steps1.get(0).getInstruction());
    }

    @Test
    public void testInsertStepUpdateAndGetStepUpdate() {
        // insert step into database
        cookbookDao.insertStep(step1);
        // get step id
        LiveData<List<StepEntity>> liveSteps = cookbookDao.getAllSteps(recipeId1);
        List<StepEntity> steps = liveSteps.getValue();
        assert steps != null;
        assertEquals(1, steps.size());
        int stepId = steps.get(0).getStepId();
        // create variable
        String temp = "This is an update for this step.";
        // Create a step update
        StepUpdateEntity stepUpdate = new StepUpdateEntity(0, stepId, temp);
        // insert the step update
        cookbookDao.insertStepUpdate(stepUpdate);
        // get step update
        StepUpdateEntity stepUpdateEntity = cookbookDao.getStepUpdate(stepId);
        // test update
        assertEquals(temp, stepUpdateEntity.getUpdate());
    }

    @Test
    public void testDeleteStepUpdate() {
        // insert step into database
        cookbookDao.insertStep(step1);
        // get step id
        LiveData<List<StepEntity>> liveSteps = cookbookDao.getAllSteps(recipeId1);
        List<StepEntity> steps = liveSteps.getValue();
        assert steps != null;
        assertEquals(1, steps.size());
        int stepId = steps.get(0).getStepId();
        // create variable
        String temp = "This is an update for this step.";
        // Create an step update
        StepUpdateEntity stepUpdate = new StepUpdateEntity(0, stepId, temp);
        // insert the step update
        cookbookDao.insertStepUpdate(stepUpdate);
        // get step update
        StepUpdateEntity stepUpdateEntity = cookbookDao.getStepUpdate(stepId);
        // delete the step update
        cookbookDao.deleteStepUpdate(stepUpdateEntity);
        // get the update
        StepUpdateEntity stepUpdateEntity1 = cookbookDao.getStepUpdate(stepId);
        // test
        assertNull(stepUpdateEntity1);
    }

    @Test
    public void testUpdateStepUpdate() {
        // insert step into database
        cookbookDao.insertStep(step1);
        // get step id
        LiveData<List<StepEntity>> liveSteps = cookbookDao.getAllSteps(recipeId1);
        List<StepEntity> steps = liveSteps.getValue();
        assert steps != null;
        assertEquals(1, steps.size());
        int stepId = steps.get(0).getStepId();
        // create variable
        String temp = "This is an update for this step.";
        // Create a step update
        StepUpdateEntity stepUpdate = new StepUpdateEntity(0, stepId, temp);
        // insert the step update
        cookbookDao.insertStepUpdate(stepUpdate);
        // get step update
        StepUpdateEntity stepUpdateEntity = cookbookDao.getStepUpdate(stepId);
        // modify the update
        String temp1 = "Updated update";
        stepUpdateEntity.setUpdate(temp1);
        // save to database
        cookbookDao.updateStepUpdate(stepUpdateEntity);
        // get modified update
        StepUpdateEntity stepUpdateEntity1 = cookbookDao.getStepUpdate(stepId);
        // test
        assertEquals(temp1, stepUpdateEntity1.getUpdate());
    }
}
