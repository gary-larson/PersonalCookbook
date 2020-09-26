package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.StepEntity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StepEntityUnitTests {
    // step data
    private static final int STEP_ID_VALUE = 6468;
    private static final int RECIPE_ID_VALUE = 78;
    private static final String INSTRUCTION_VALUE = "Sufficient instruction";
    private static final int NUMBER_VALUE = 5;

    // Create step
    StepEntity step = new StepEntity(STEP_ID_VALUE, RECIPE_ID_VALUE, NUMBER_VALUE,
            INSTRUCTION_VALUE);

    // Tests for the constructor and Getters
    @Test
    public void testStepIdGetter() {
        assertEquals(STEP_ID_VALUE, step.getStepId());
    }

    @Test
    public void testStepRecipeIdGetter() {
        assertEquals(RECIPE_ID_VALUE, step.getRecipeId());
    }

    @Test
    public void testStepDescriptionGetter() {
        assertEquals(INSTRUCTION_VALUE, step.getInstruction());
    }

    @Test
    public void testStepNumberGetter() {
        assertEquals(NUMBER_VALUE, step.getNumber());
    }

    // Test default constructor and setters
    @Test
    public void testStepInstructionSetter() {
        String temp = "put in oven";
        step.setInstruction(temp);
        assertEquals(temp, step.getInstruction());
    }

    @Test
    public void testStepNumberSetter() {
        int temp = 55;
        step.setNumber(temp);
        assertEquals(temp, step.getNumber());
    }
}
