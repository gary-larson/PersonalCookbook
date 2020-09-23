package com.larsonapps.personalcookbook.data;

public class RecipeImage {
    private String type;
    private String imageUrl;
    private int height;
    private int width;
    private String caption;

    /**
     * Default Constructor
     */
    public RecipeImage() {
    }

    /**
     * Constructor for all member variables
     * @param type to set
     * @param imageUrl to set
     * @param height to set
     * @param width to set
     * @param caption to set
     */
    public RecipeImage(String type, String imageUrl, int height, int width, String caption) {
        this.type = type;
        this.imageUrl = imageUrl;
        this.height = height;
        this.width = width;
        this.caption = caption;
    }

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
