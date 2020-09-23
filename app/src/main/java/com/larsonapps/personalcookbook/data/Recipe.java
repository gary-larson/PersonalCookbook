package com.larsonapps.personalcookbook.data;

import java.util.List;
import java.util.Locale;

public class Recipe {
    private String name;
    private String description;
    private int servings;
    // Times in minutes
    private int prepTime;
    private int cookTime;
    private int totalTime;
    private String notes;
    private String coypright;
    List<Ingredient> ingredients;
    List<Step> steps;
    List<RecipeImage> images;

    /**
     * Constructor for all variables
     * @param name to set
     * @param description to set
     * @param servings to set
     * @param prepTime to set
     * @param cookTime to set
     * @param totalTime to set
     * @param notes to set
     * @param coypright to set
     * @param ingredients to set
     * @param steps to set
     * @param images to set
     */
    public Recipe(String name, String description, int servings, int prepTime, int cookTime,
                  int totalTime, String notes, String coypright, List<Ingredient> ingredients,
                  List<Step> steps, List<RecipeImage> images) {
        this.name = name;
        this.description = description;
        this.servings = servings;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.totalTime = totalTime;
        this.notes = notes;
        this.coypright = coypright;
        this.ingredients = ingredients;
        this.steps = steps;
        this.images = images;
    }

    /**
     * Default constructor
     */
    public Recipe() {}

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

    /**
     * Getter for ingredients
     * @return ingredients
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Setter for ingredients
     * @param ingredients to set
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Getter for steps
     * @return steps
     */
    public List<Step> getSteps() {
        return steps;
    }

    /**
     * Setter for steps
     * @param steps to set
     */
    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    /**
     * Getter for images
     * @return images
     */
    public List<RecipeImage> getImages() {
        return images;
    }

    /**
     * Setter for images
     * @param images to set
     */
    public void setImages(List<RecipeImage> images) {
        this.images = images;
    }

    /**
     * Getter for prepTime in a string
     * @return prepTime in a string
     */
    public String getPrepTimeString() {
        int days, hours, mins;
        days = prepTime / 1440;
        hours = (prepTime - (days * 1440)) / 60;
        mins = prepTime % 60;
        return String.format(Locale.getDefault(),"%d days %d hours %d minutes", days,
                hours, mins);
    }

    /**
     * Getter for cookTime in a string
     * @return cookTime in a string
     */
    public String getCookTimeString() {
        int days, hours, mins;
        days = cookTime / 1440;
        hours = (cookTime - (days * 1440)) / 60;
        mins = cookTime % 60;
        return String.format(Locale.getDefault(),"%d days %d hours %d minutes", days,
                hours, mins);
    }

    /**
     * Getter for totalTime in a string
     * @return totalTime in a string
     */
    public String getTotalTimeString() {
        int days, hours, mins;
        days = totalTime / 1440;
        hours = (totalTime - (days * 1440)) / 60;
        mins = totalTime % 60;
        return String.format(Locale.getDefault(),"%d days %d hours %d minutes", days,
                hours, mins);
    }
}
