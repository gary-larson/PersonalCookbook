package com.larsonapps.personalcookbook.data;

import android.view.ViewDebug;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "keywords")
public class KeywordEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "keyword_id")
    private int keywordId;
    @ColumnInfo(name = "recipe_id")
    private int recipeId;
    @ColumnInfo(name = "keyword")
    private String keyword;

    /**
     * Constructor with all variables
     * @param keywordId to set
     * @param recipeId to set
     * @param keyword to set
     */
    public KeywordEntity(int keywordId, int recipeId, String keyword) {
        this.keywordId = keywordId;
        this.recipeId = recipeId;
        this.keyword = keyword;
    }

    /**
     * Getter for keyword id
     * @return keyword id
     */
    public int getKeywordId() {
        return keywordId;
    }

    /**
     * Setter for keyword id
     * @param keywordId to set
     */
    public void setKeywordId(int keywordId) {
        this.keywordId = keywordId;
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
    public void setRecipeId (int recipeId) {
        this.recipeId = recipeId;
    }

    /**
     * Getter for keyword
     * @return keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Setter for keyword
     * @param keyword to set
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
