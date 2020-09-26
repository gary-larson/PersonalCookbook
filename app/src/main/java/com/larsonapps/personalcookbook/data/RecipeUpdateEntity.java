package com.larsonapps.personalcookbook.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe_updates")
public class RecipeUpdateEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_update_id")
    private int recipeUpdateId;
    @ColumnInfo(name = "recipe_id")
    private int recipeId;
    @ColumnInfo(name = "update")
    private String update;

    /**
     * Constructor for all variables
     * @param recipeUpdateId to set
     * @param recipeId to set
     * @param update to set
     */
    public RecipeUpdateEntity(int recipeUpdateId, int recipeId, String update) {
        this.recipeUpdateId = recipeUpdateId;
        this.recipeId = recipeId;
        this.update = update;
    }

    /**
     * Getter for recipe update id
     * @return recipe update id
     */
    public int getRecipeUpdateId() {
        return recipeUpdateId;
    }

    /**
     * Setter for recipe update id
     * @param recipeUpdateId to set
     */
    public void setRecipeUpdateId(int recipeUpdateId) {
        this.recipeUpdateId = recipeUpdateId;
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
     * Getter for update
     * @return update
     */
    public String getUpdate() {
        return update;
    }

    /**
     * Setter for update
     * @param update to set
     */
    public void setUpdate(String update) {
        this.update = update;
    }
}
