package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.StepUpdateEntity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StepUpdateEntityUnitTests {
    // step update data
    private static final int STEP_UPDATE_ID_VALUE = 5;
    private static final int STEP_ID_VALUE = 548;
    private static final String UPDATE_VALUE = "cook in non stick pan";

    // create step update
    StepUpdateEntity stepUpdateEntity = new
            StepUpdateEntity(STEP_UPDATE_ID_VALUE, STEP_ID_VALUE, UPDATE_VALUE);

    // test constructor and getters
    @Test
    public void testRecipeUpdateIdGetter() {
        assertEquals(STEP_UPDATE_ID_VALUE, stepUpdateEntity.getStepUpdateId());
    }

    @Test
    public void testRecipeIdGetter() {
        assertEquals(STEP_ID_VALUE, stepUpdateEntity.getStepId());
    }

    @Test
    public void testRecipeUpdateGetter() {
        assertEquals(UPDATE_VALUE, stepUpdateEntity.getUpdate());
    }

    // test setter
    @Test
    public void testRecipeUpdateSetter() {
        String temp = "beat eggs for 12 minutes";
        stepUpdateEntity.setUpdate(temp);
        assertEquals(temp, stepUpdateEntity.getUpdate());
    }
}
