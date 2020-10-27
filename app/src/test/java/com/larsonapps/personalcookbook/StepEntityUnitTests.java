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
    private static final String TO_STRING_VALUE = "5) Sufficient instruction";
    private static final String PERSONAL_NOTE_VALUE = "This is a personal note.";

    // Create step
    StepEntity step = new StepEntity(STEP_ID_VALUE, RECIPE_ID_VALUE, NUMBER_VALUE,
            INSTRUCTION_VALUE, PERSONAL_NOTE_VALUE);

    // Tests for the constructor and Getters

    /**
     * Test for step id getter
     */
    @Test
    public void testStepIdGetter() {
        assertEquals(STEP_ID_VALUE, step.getStepId());
    }

    /**
     * Test for step recipe id getter
     */
    @Test
    public void testStepRecipeIdGetter() {
        assertEquals(RECIPE_ID_VALUE, step.getRecipeId());
    }

    /**
     * Test for step instruction getter
     */
    @Test
    public void testStepInstructionGetter() {
        assertEquals(INSTRUCTION_VALUE, step.getInstruction());
    }

    /**
     * Test for step number getter
     */
    @Test
    public void testStepNumberGetter() {
        assertEquals(NUMBER_VALUE, step.getNumber());
    }

    /**
     * Test for step personal note getter
     */
    @Test
    public void testStepPersonalNoteGetter() {
        assertEquals(PERSONAL_NOTE_VALUE, step.getPersonalNote());
    }

    // Test default constructor and setters

    /**
     * Test for step id setter
     */
    @Test
    public void testStepIdSetter() {
        int temp = 84;
        step.setStepId(temp);
        assertEquals(temp, step.getStepId());
    }

    /**
     * Test for step recipe id setter
     */
    @Test
    public void testStepRecipeIdSetter() {
        int temp = 46;
        step.setRecipeId(temp);
        assertEquals(temp, step.getRecipeId());
    }

    /**
     * Test for step instruction setter
     */
    @Test
    public void testStepInstructionSetter() {
        String temp = "put in oven";
        step.setInstruction(temp);
        assertEquals(temp, step.getInstruction());
    }

    /**
     * Test for step number setter
     */
    @Test
    public void testStepNumberSetter() {
        int temp = 55;
        step.setNumber(temp);
        assertEquals(temp, step.getNumber());
    }

    /**
     * Test for step personal note setter
     */
    @Test
    public void testStepPersonalNoteSetter() {
        String temp = "Another personal note.";
        step.setPersonalNote(temp);
        assertEquals(temp, step.getPersonalNote());
    }

    /**
     * Test for step to string
     */
    @Test
    public void testToString() {
        assertEquals(TO_STRING_VALUE, step.toString());
    }
}
