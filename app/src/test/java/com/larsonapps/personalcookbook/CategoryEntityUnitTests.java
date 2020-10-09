package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.CategoryEntity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryEntityUnitTests {
    // category update data
    private static final int CATEGORY_ID_VALUE = 48;
    private static final String CATEGORY_NAME_VALUE = "Pasta";

    // create category
    CategoryEntity categoryEntity = new
            CategoryEntity(CATEGORY_ID_VALUE, CATEGORY_NAME_VALUE);

    // test constructor and getters
    @Test
    public void testCategoryIdGetter() {
        assertEquals(CATEGORY_ID_VALUE, categoryEntity.getCategoryId());
    }

    @Test
    public void testCategoryNameGetter() {
        assertEquals(CATEGORY_NAME_VALUE, categoryEntity.getCategoryName());
    }

    // test setters
    @Test
    public void testCategoryIdSetter() {
        int temp = 46;
        categoryEntity.setCategoryId(temp);
        assertEquals(temp, categoryEntity.getCategoryId());
    }

    @Test
    public void testCategorySetter() {
        String temp = "Snacks";
        categoryEntity.setCategoryName(temp);
        assertEquals(temp, categoryEntity.getCategoryName());
    }
}
