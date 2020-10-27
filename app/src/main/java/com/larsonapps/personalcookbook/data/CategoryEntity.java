package com.larsonapps.personalcookbook.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Class for category entity data
 */
@Entity(tableName = "categories")
public class CategoryEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    private int categoryId;
    @ColumnInfo(name = "category_name")
    private String categoryName;

    /**
     * Default constructor
     */
    @Ignore
    public CategoryEntity() {}

    /**
     * Constructor for all variables
     * @param categoryId to set
     * @param categoryName to set
     */
    public CategoryEntity(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    /**
     * Getter for category id
     * @return category id
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Setter for category id
     * @param categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Getter for category name
     * @return category name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Setter for category name
     * @param categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
