package com.larsonapps.personalcookbook.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "steps")
public class StepEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "step_id")
    private int stepId;
    @ColumnInfo(name = "recipe_id")
    private int recipeId;
    @ColumnInfo(name = "number")
    private int number;
    @ColumnInfo(name = "instruction")
    private String instruction;

    /**
     * Default Constructor
     */
    @Ignore
    public StepEntity() {}

    /**
     * Constructor for all member variables
     * @param stepId to set
     * @param recipeId to set
     * @param number to set
     * @param instruction to set
     */
    public StepEntity(int stepId, int recipeId,int number, String instruction) {
        this.stepId = stepId;
        this.recipeId = recipeId;
        this.number = number;
        this.instruction = instruction;
    }

    /**
     * Getter for step id
     * @return step id
     */
    public int getStepId() {
        return stepId;
    }

    /**
     * Getter for recipe id
     * @return recipe id
     */
    public int getRecipeId() {
        return recipeId;
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
}
