package com.larsonapps.personalcookbook.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class RecipeEntity {
    // Declare Variables
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "servings")
    private int servings;
    @ColumnInfo(name = "prep_time")
    private int prepTime;
    @ColumnInfo(name = "cook_time")
    private int cookTime;
    @ColumnInfo(name = "total_time")
    private int totalTime;
    @ColumnInfo(name = "notes")
    private String notes;
    @ColumnInfo(name = "copyright")
    private String coypright;

    /**
     * Constructor for all arguments
     * @param id to set
     * @param name to set
     * @param description to set
     * @param servings to set
     * @param prepTime to set
     * @param cookTime to set
     * @param totalTime to set
     * @param notes to set
     * @param coypright to set
     */
    public RecipeEntity(int id, String name, String description, int servings, int prepTime,
                        int cookTime, int totalTime, String notes, String coypright) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.servings = servings;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.totalTime = totalTime;
        this.notes = notes;
        this.coypright = coypright;
    }

    /**
     * Getter for id
     * @return id
     */
    public int getId() {
        return id;
    }

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
     * Getter for description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     * @param description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for servings
     * @return servings
     */
    public int getServings() {
        return servings;
    }

    /**
     * Setter for servings
     * @param servings to set
     */
    public void setServings(int servings) {
        this.servings = servings;
    }

    /**
     * Getter for prepTime in minutes
     * @return prepTime in minutes
     */
    public int getPrepTime() {
        return prepTime;
    }

    /**
     * Setter for prepTime in minutes
     * @param prepTime to set
     */
    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    /**
     * Getter for cookTime in minutes
     * @return cookTime in minutes
     */
    public int getCookTime() {
        return cookTime;
    }

    /**
     * Setter for cookTime in minutes
     * @param cookTime to set
     */
    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    /**
     * Getter for totalTime in minutes
     * @return totalTime in minutes
     */
    public int getTotalTime() {
        return totalTime;
    }

    /**
     * Setter for totalTime in minutes
     * @param totalTime to set
     */
    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * Getter for notes
     * @return to set
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Setter for notes
     * @param notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Getter for copyright
     * @return copyright
     */
    public String getCoypright() {
        return coypright;
    }

    /**
     * Setter for copyright
     * @param coypright to set
     */
    public void setCoypright(String coypright) {
        this.coypright = coypright;
    }
}
