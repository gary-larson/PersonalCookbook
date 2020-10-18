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
 * Class to deal with ingredient data for parcels and room database
 */
@Entity(tableName = "ingredients")
public class IngredientEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ingredient_id")
    private int ingredientId;
    @ColumnInfo(name = "recipe_id")
    private int recipeId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "amount")
    private String amount;
    @ColumnInfo(name = "measure")
    private String measure;
    @ColumnInfo(name = "preparation")
    private String preparation;
    @ColumnInfo(name = "personal_note")
    private String personalNote;

    /**
     * Default Constructor
     */
    @Ignore
    public IngredientEntity() {}

    /**
     * Constructor for all variables
     * @param ingredientId to set
     * @param recipeId to set
     * @param name to set
     * @param amount to set
     * @param measure to set
     * @param preparation to set
     * @param personalNote to set
     */
    public IngredientEntity(int ingredientId, int recipeId, String name, String amount,
                            String measure, String preparation, String personalNote) {
        this.ingredientId = ingredientId;
        this.recipeId = recipeId;
        this.name = name;
        this.amount = amount;
        this.measure = measure;
        this.preparation = preparation;
        this.personalNote = personalNote;
    }

    /**
     * Constructor for parcelable
     * @param in for parcelable
     */
    @Ignore
    protected IngredientEntity(Parcel in) {
        ingredientId = in.readInt();
        recipeId = in.readInt();
        name = in.readString();
        amount = in.readString();
        measure = in.readString();
        preparation = in.readString();
        personalNote = in.readString();
    }

    /**
     * Method for parcelable
     */
    public static final Creator<IngredientEntity> CREATOR = new Creator<IngredientEntity>() {
        @Override
        public IngredientEntity createFromParcel(Parcel in) {
            return new IngredientEntity(in);
        }

        @Override
        public IngredientEntity[] newArray(int size) {
            return new IngredientEntity[size];
        }
    };

    /**
     * Getter for id
     * @return id
     */
    public int getIngredientId() {
        return ingredientId;
    }

    /**
     * Setter for ingredient id
     * @param id to set
     */
    public void setIngredientId(int id) {this.ingredientId = id;}

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
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for amount
     * @return amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Setter for amount
     * @param amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Getter for measure
     * @return measure
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * Setter for measure
     * @param measure to set
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /**
     * Getter for preparation
     * @return preparation
     */
    public String getPreparation() {
        return preparation;
    }

    /**
     * Setter for preparation
     * @param preparation to set
     */
    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    /**
     * Method for string representation of class
     * @return string representation
     */
    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%s %s %s\n%s", name, amount, measure,
                preparation);
    }

    /**
     * Method for Parcelable description
     * @return description
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Method to write to parcelable
     * @param dest of parcel
     * @param flags for parcel
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ingredientId);
        dest.writeInt(recipeId);
        dest.writeString(name);
        dest.writeString(amount);
        dest.writeString(measure);
        dest.writeString(preparation);
        dest.writeString(personalNote);
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
