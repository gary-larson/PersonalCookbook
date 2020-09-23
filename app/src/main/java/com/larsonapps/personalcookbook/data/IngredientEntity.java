package com.larsonapps.personalcookbook.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredients")
public class IngredientEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ingredient_id")
    private int id;
    @ColumnInfo(name = "recipe_id")
    private int recipeId;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "amount")
    private double amount;
    @ColumnInfo(name = "measure")
    private String measure;
    @ColumnInfo(name = "preparation")
    private String preparation;

    /**
     * Constructor for all variables
     * @param id to set
     * @param recipeId to set
     * @param name to set
     * @param amount to set
     * @param measure to set
     * @param preparation to set
     */
    public IngredientEntity(int id, int recipeId, String name, double amount, String measure,
                            String preparation) {
        this.id = id;
        this.recipeId = recipeId;
        this.name = name;
        this.amount = amount;
        this.measure = measure;
        this.preparation = preparation;
    }

    /**
     * Getter for id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for recipe id
     * @return recipe id
     */
    public int getRecipeId() {
        return recipeId;
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
     * Getter for amount
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Setter for amount
     * @param amount to set
     */
    public void setAmount(double amount) {
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
}
