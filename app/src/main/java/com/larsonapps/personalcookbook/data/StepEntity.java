package com.larsonapps.personalcookbook.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Locale;

/**
 * Class to deal with step data for parcel and room database
 */
@Entity(tableName = "steps")
public class StepEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "step_id")
    private int stepId;
    @ColumnInfo(name = "recipe_id")
    private int recipeId;
    @ColumnInfo(name = "number")
    private int number;
    @ColumnInfo(name = "instruction")
    private String instruction;
    @ColumnInfo(name = "personal_note")
    private String personalNote;

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
     * @param personalNote to set
     */
    public StepEntity(int stepId, int recipeId, int number, String instruction,
                      String personalNote) {
        this.stepId = stepId;
        this.recipeId = recipeId;
        this.number = number;
        this.instruction = instruction;
        this.personalNote = personalNote;
    }

    /**
     * Method to create parcelable
     */
    public static final Creator<StepEntity> CREATOR = new Creator<StepEntity>() {
        @Override
        public StepEntity createFromParcel(Parcel in) {
            return new StepEntity(in);
        }

        @Override
        public StepEntity[] newArray(int size) {
            return new StepEntity[size];
        }
    };

    /**
     * Getter for step id
     * @return step id
     */
    public int getStepId() {
        return stepId;
    }

    /**
     * Setter for step id
     * @param id to set
     */
    public void setStepId(int id) {this.stepId = id;}

    /**
     * Getter for recipe id
     * @return recipe id
     */
    public int getRecipeId() {
        return recipeId;
    }

    /**
     * Setter for recipe id
     * @param id to set
     */
    public void setRecipeId(int id) {this.recipeId = id;}

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
     * Constructor for Parcelable
     * @param in parcel to set
     */
    @Ignore
    public StepEntity(Parcel in) {
        this.stepId = in.readInt();
        this.recipeId = in.readInt();
        this.number = in.readInt();
        this.instruction = in.readString();
        this.personalNote = in.readString();
    }

    /**
     * To string method to give strin representation of class
     * @return string representation
     */
    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%d) %s",number, instruction);
    }

    /**
     * Method to describe the contents of the parcel
     * @return description
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Method to write to parcel
     * @param dest of parcel
     * @param flags for parcel
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.stepId);
        dest.writeInt(this.recipeId);
        dest.writeInt(this.number);
        dest.writeString(this.instruction);
        dest.writeString(this.personalNote);
    }

    /**
     * Getter for personal note
     * @return personal note
     */
    public String getPersonalNote() {
        return personalNote;
    }

    /**
     * Setter for personal note
     * @param personalNote to set
     */
    public void setPersonalNote(String personalNote) {
        this.personalNote = personalNote;
    }
}
