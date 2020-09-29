package com.larsonapps.personalcookbook.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Locale;

@Entity(tableName = "recipes")
public class RecipeEntity {
    // Declare Variables
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "short_description")
    private String shortDescription;
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
    private String copyright;

    /**
     * Default Constructor
     */
    @Ignore
    public RecipeEntity() {}

    /**
     * Constructor for all arguments
     * @param id to set
     * @param name to set
     * @param shortDescription to set
     * @param description to set
     * @param servings to set
     * @param prepTime to set
     * @param cookTime to set
     * @param totalTime to set
     * @param notes to set
     * @param copyright to set
     */
    public RecipeEntity(int id, String name, String shortDescription, String description,
                        int servings, int prepTime, int cookTime, int totalTime, String notes,
                        String copyright) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.servings = servings;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.totalTime = totalTime;
        this.notes = notes;
        this.copyright = copyright;
    }

    /**
     * Getter for id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id to set
     */
    public void setId(int id) {this.id = id;}

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
     * Getter for short description
     * @return short description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Setter for short description
     * @param shortDescription to set
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
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
     * Getter for preparation time string
     * @return preparation time string
     */
    public String getPrepTimeString() {
        return getString(prepTime);
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
     * Getter for cook time string
     * @return cook time string
     */
    public String getCookTimeString() {
        return getString(cookTime);
    }

    private String getString(int time) {
        String temp;
        int days = time / 1440;
        int hours;
        if (time > 1440) {
            hours = (time - (days * 1440)) / 60;
        } else {
            hours = time / 60;
        }
        int mins = time % 60;
        String day = "days";
        String hour = "hours";
        String min = "mins";
        if (days == 1) {
            day = "day";
        }
        if (hours == 1) {
            hour = "hour";
        }
        if (hours == 0 && days == 0) {
            if (mins == 1) {
                min = "minute";
            } else {
                min = "minutes";
            }
        } else {
            if (mins == 1) {
                min = "min";
            }
        }
        if (days > 0) {
            temp = String.format(Locale.getDefault(), "%d %s %d %s %d %s", days, day,
                    hours, hour, mins, min);
        } else if (hours > 0) {
            temp = String.format(Locale.getDefault(), "%d %s %d %s", hours, hour, mins, min);
        } else {
            temp = String.format(Locale.getDefault(), "%d %s", mins, min);
        }
        return temp;
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
     * Getter for total time string
     * @return total time string
     */
    public String getTotalTimeString() {
        return getString(totalTime);
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
    public String getCopyright() {
        return copyright;
    }

    /**
     * Setter for copyright
     * @param copyright to set
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
