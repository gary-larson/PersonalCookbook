package com.larsonapps.personalcookbook.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "images")
public class ImageEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "image_id")
    private int imageId;
    @ColumnInfo(name = "recipe_id")
    private int recipeId;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "image_url")
    private String imageUrl;
    @ColumnInfo(name = "height")
    private int height;
    @ColumnInfo(name = "width")
    private int width;
    @ColumnInfo(name = "caption")
    private String caption;

    /**
     * Default constructor
     */
    @Ignore
    public ImageEntity() {}

    /**
     * Constructor for all member variables\
     * @param imageId to set
     * @param recipeId to set
     * @param type to set
     * @param imageUrl to set
     * @param height to set
     * @param width to set
     * @param caption to set
     */
    public ImageEntity(int imageId, int recipeId, String type, String imageUrl, int height,
                       int width, String caption) {
        this.imageId = imageId;
        this.recipeId = recipeId;
        this.type = type;
        this.imageUrl = imageUrl;
        this.height = height;
        this.width = width;
        this.caption = caption;
    }

    /**
     * Getter for image id
     * @return image id
     */
    public int getImageId() {
        return imageId;
    }

    /**
     * Setter for imageId
     * @param id to set
     */
    public void setImageId(int id) {this.imageId = id;}

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
     * Getter for image url
     * @return image url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Setter for image url
     * @param imageUrl to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Getter for type of image
     * @return type of image
     */
    public String getType() {
        return type;
    }

    /**
     * Setter fot type of image
     * @param type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for height in pixels
     * @return height in pixels
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setter for height in pixels
     * @param height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Getter for width in pixels
     * @return width in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Setter for width in pixels
     * @param width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Getter for caption
     * @return caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Setter for caption
     * @param caption to set
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }
}
