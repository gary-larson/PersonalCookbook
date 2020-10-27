package com.larsonapps.personalcookbook.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Class for cook note entity
 */
@Entity(tableName = "cook_notes")
public class CookNoteEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id")
    private int cookNoteId;
    @ColumnInfo(name = "recipe_id")
    private int recipeId;
    @ColumnInfo(name = "note")
    private String note;
    @ColumnInfo(name = "number")
    private int number;

    /**
     * Default constructor
     */
    @Ignore
    public CookNoteEntity() {}

    /**
     * Constructor for all variables
     * @param cookNoteId to set
     * @param recipeId to set
     * @param note to set
     * @param number to set
     */
    public CookNoteEntity(int cookNoteId, int recipeId, String note, int number) {
        this.cookNoteId = cookNoteId;
        this.recipeId = recipeId;
        this.note = note;
        this.number = number;
    }

    /**
     * Method to create a parcel
     */
    public static final Creator<CookNoteEntity> CREATOR = new Creator<CookNoteEntity>() {
        @Override
        public CookNoteEntity createFromParcel(Parcel in) {
            return new CookNoteEntity(in);
        }

        @Override
        public CookNoteEntity[] newArray(int size) {
            return new CookNoteEntity[size];
        }
    };

    /**
     * Getter for cook note id
     * @return cook note id
     */
    public int getCookNoteId() {
        return cookNoteId;
    }

    /**
     * Setter for cook note id
     * @param cookNoteId to set
     */
    public void setCookNoteId(int cookNoteId) {
        this.cookNoteId = cookNoteId;
    }

    /**
     * Getter for recipe id
     * @return recipe id
     */
    public int getRecipeId() {
        return recipeId;
    }

    /**
     * Setter for recipe id
     * @param recipeId to set
     */
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    /**
     * Getter for note
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * Setter for note
     * @param note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Getter for number
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Setter for note
     * @param number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Constructor for Parcelable
     * @param in parcel
     */
    @Ignore
    public CookNoteEntity(Parcel in) {
        this.cookNoteId = in.readInt();
        this.recipeId = in.readInt();
        this.note = in.readString();
        this.number = in.readInt();
    }

    /**
     * Method to describe parcel
     * @return parcel description
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Method to write parcel
     * @param dest parcel to write to
     * @param flags to use
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cookNoteId);
        dest.writeInt(this.recipeId);
        dest.writeString(this.note);
        dest.writeInt(this.number);
    }
}
