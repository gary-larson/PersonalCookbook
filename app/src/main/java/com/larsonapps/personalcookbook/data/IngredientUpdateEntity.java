package com.larsonapps.personalcookbook.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient_updates")
public class IngredientUpdateEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ingredient_update_id")
    private int ingredientUpdateId;
    @ColumnInfo(name = "ingredient_id")
    private int ingredientId;
    @ColumnInfo(name = "update")
    private String update;

    /**
     * Constructor for all variables
     * @param ingredientUpdateId to set
     * @param ingredientId to set
     * @param update to set
     */
    public IngredientUpdateEntity(int ingredientUpdateId, int ingredientId, String update) {
        this.ingredientUpdateId = ingredientUpdateId;
        this.ingredientId = ingredientId;
        this.update = update;
    }

    /**
     * Getter for ingredient update id
     * @return ingredient update id
     */
    public int getIngredientUpdateId() {
        return ingredientUpdateId;
    }

    /**
     * Setter for ingredient update id
     * @param ingredientUpdateId to set
     */
    public void setIngredientUpdateId(int ingredientUpdateId) {
        this.ingredientUpdateId = ingredientUpdateId;
    }

    /**
     * Getter for ingredient id
     * @return ingredient id
     */
    public int getIngredientId() {
        return ingredientId;
    }

    /**
     * Setter for ingredient id
     * @param ingredientId to set
     */
    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
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
