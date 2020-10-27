package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.CategoryEntity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class to test category entity data
 */
public class CategoryEntityUnitTests {
    // category update data
    private static final int CATEGORY_ID_VALUE = 48;
    private static final String CATEGORY_NAME_VALUE = "Pasta";

    // create category
    CategoryEntity categoryEntity = new
            CategoryEntity(CATEGORY_ID_VALUE, CATEGORY_NAME_VALUE);

    // test constructor and getters

    /**
     * Test for category id getter
     */
    @Test
    public void testCategoryIdGetter() {
        assertEquals(CATEGORY_ID_VALUE, categoryEntity.getCategoryId());
    }

    /**
     * Test for category name getter
     */
    @Test
    public void testCategoryNameGetter() {
        assertEquals(CATEGORY_NAME_VALUE, categoryEntity.getCategoryName());
    }

    // test setters

    /**
     * Test for category id setter
     */
    @Test
    public void testCategoryIdSetter() {
        int temp = 46;
        categoryEntity.setCategoryId(temp);
        assertEquals(temp, categoryEntity.getCategoryId());
    }

    /**
     * Test for category name setter
     */
    @Test
    public void testCategoryNameSetter() {
        String temp = "Snacks";
        categoryEntity.setCategoryName(temp);
        assertEquals(temp, categoryEntity.getCategoryName());
    }
}
