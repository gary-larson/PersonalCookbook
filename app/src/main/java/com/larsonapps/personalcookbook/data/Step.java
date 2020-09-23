package com.larsonapps.personalcookbook.data;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class Step {
    private int number;
    private String instruction;

    /**
     * Default constructor
     */
    public Step() {
    }

    /**
     * Constructor for all member variables
     * @param number to set
     * @param instruction to set
     */
    public Step(int number, String instruction) {
        this.number = number;
        this.instruction = instruction;
    }

    /**
     * Getter for number
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Setter for number
     * @param number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Getter for instruction
     * @return instruction
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * Setter for instruction
     * @param instruction to set
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Step presented as a string
     * @return string presentation of Step
     */
    @NotNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(),"%d) %s", number, instruction);
    }
}
