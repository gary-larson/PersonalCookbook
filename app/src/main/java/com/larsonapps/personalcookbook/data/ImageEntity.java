package com.larsonapps.personalcookbook.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Class for image entity
 */
@Entity(tableName = "images")
public class ImageEntity implements Parcelable {
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
     */
    public ImageEntity(int imageId, int recipeId, String type, String imageUrl, int height,
                       int width) {
        this.imageId = imageId;
        this.recipeId = recipeId;
        this.type = type;
        this.imageUrl = imageUrl;
        this.height = height;
        this.width = width;
    }

    /**
     * Method to create a parcel
     */
    public static final Creator<ImageEntity> CREATOR = new Creator<ImageEntity>() {
        @Override
        public ImageEntity createFromParcel(Parcel in) {
            return new ImageEntity(in);
        }

        @Override
        public ImageEntity[] newArray(int size) {
            return new ImageEntity[size];
        }
    };

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
     * Constructor for Parcelable
     * @param in parcel
     */
    @Ignore
    public ImageEntity(Parcel in) {
        this.imageId = in.readInt();
        this.recipeId = in.readInt();
        this.type = in.readString();
        this.imageUrl = in.readString();
        this.height = in.readInt();
        this.width = in.readInt();
    }

    /**
     * Method to descrip contents of parcel
     * @return description
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Methor to wrie to parcel
     * @param dest parcel
     * @param flags for  parcel
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeInt(recipeId);
        dest.writeString(type);
        dest.writeString(imageUrl);
        dest.writeInt(height);
        dest.writeInt(width);
    }
}
