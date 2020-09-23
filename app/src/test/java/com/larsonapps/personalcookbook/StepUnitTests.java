package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.Step;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StepUnitTests {
    // step data
    private static final String INSTRUCTION_VALUE = "Sufficient instruction";
    private static final int NUMBER_VALUE = 5;
    private static final String TO_STRING_VALUE = "5) Sufficient instruction";

    // Create step
    Step step = new Step(NUMBER_VALUE, INSTRUCTION_VALUE);

    // Tests for the constructor and Getters

    @Test
    public void testStepInstructionGetter() {
        assertEquals(INSTRUCTION_VALUE, step.getInstruction());
    }

    @Test
    public void testStepNumberGetter() {
        assertEquals(NUMBER_VALUE, step.getNumber());
    }

    public void testStepToString() {
        assertEquals(TO_STRING_VALUE, step.toString());
    }

    // Test default constructor and setters
    Step stepDefault = new Step();

    @Test
    public void testStepInstructionSetter() {
        String temp = "put in oven";
        stepDefault.setInstruction(temp);
        assertEquals(temp, stepDefault.getInstruction());
    }

    @Test
    public void testStepNumberSetter() {
        int temp = 55;
        stepDefault.setNumber(temp);
        assertEquals(temp, stepDefault.getNumber());
    }
}
