package com.larsonapps.personalcookbook.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "keywords")
public class KeywordEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "keyword_id")
    private int keywordId;
    @ColumnInfo(name = "recipe_id")
    private int recipeId;
    @ColumnInfo(name = "keyword")
    private String keyword;

    /**
     * Default constructor
     */
    @Ignore
    public KeywordEntity() {}

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
     * Constructor for parcelable
     * @param in to get parcel data
     */
    @Ignore
    protected KeywordEntity(Parcel in) {
        keywordId = in.readInt();
        recipeId = in.readInt();
        keyword = in.readString();
    }

    /**
     * Method for pacelable
     */
    public static final Creator<KeywordEntity> CREATOR = new Creator<KeywordEntity>() {
        @Override
        public KeywordEntity createFromParcel(Parcel in) {
            return new KeywordEntity(in);
        }

        @Override
        public KeywordEntity[] newArray(int size) {
            return new KeywordEntity[size];
        }
    };

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

    /**
     * Method to describe parcel description
     * @return parcel description
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Method to write class to a parcel
     * @param dest parcel
     * @param flags to use
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(keywordId);
        dest.writeInt(recipeId);
        dest.writeString(keyword);
    }
}
