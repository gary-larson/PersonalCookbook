package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.ImageEntity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImageEntityUnitTests {
    // image data
    private static final int IMAGE_ID_VALUE = 548;
    private static final int RECIPE_ID_VALUE = 468;
    private static final String TYPE_VALUE = "Internet";
    private static final String IMAGE_URL_VALUE = "https://somewhere.com/5486946.jpg";
    private static final int HEIGHT_VALUE = 1720;
    private static final int WIDTH_VALUE = 2200;

    // Create RecipeImage
    ImageEntity image = new ImageEntity(IMAGE_ID_VALUE, RECIPE_ID_VALUE, TYPE_VALUE,
            IMAGE_URL_VALUE, HEIGHT_VALUE, WIDTH_VALUE);

    // Tests for the constructor and Getters

    /**
     * test for image id getter
     */
    @Test
    public void testImageIdGetter() {
        assertEquals(IMAGE_ID_VALUE, image.getImageId());
    }

    /**
     * test for image recipe id getter
     */
    @Test
    public void testImageRecipeIdGetter() {
        assertEquals(RECIPE_ID_VALUE, image.getRecipeId());
    }

    /**
     * test for image type getter
     */
    @Test
    public void testImageTypeGetter() {
        assertEquals(TYPE_VALUE, image.getType());
    }

    /**
     * test for image url getter
     */
    @Test
    public void testImageUrlGetter() {
        assertEquals(IMAGE_URL_VALUE, image.getImageUrl());
    }

    /**
     * test for image height getter
     */
    @Test
    public void testImageHeightGetter() {
        assertEquals(HEIGHT_VALUE, image.getHeight());
    }

    /**
     * test for image width getter
     */
    @Test
    public void testImageWidthGetter() {
        assertEquals(WIDTH_VALUE, image.getWidth());
    }

    // Test default constructor and setters

    /**
     * test for image id setter
     */
    @Test
    public void testImageIdSetter() {
        int temp = 255;
        image.setImageId(temp);
        assertEquals(temp, image.getImageId());
    }

    /**
     * test for image recipe id setter
     */
    @Test
    public void testImageRecipeIdSetter() {
        int temp = 91;
        image.setRecipeId(temp);
        assertEquals(temp, image.getRecipeId());
    }

    /**
     * test for image type setter
     */
    @Test
    public void testImageTypeSetter() {
        String temp = "local";
        image.setType(temp);
        assertEquals(temp, image.getType());
    }

    /**
     * test for image url setter
     */
    @Test
    public void testImageUrlSetter() {
        String temp = "c:/4648486.jpg";
        image.setImageUrl(temp);
        assertEquals(temp, image.getImageUrl());
    }

    /**
     * test for image height setter
     */
    @Test
    public void testImageHeightSetter() {
        int temp = 4000;
        image.setHeight(temp);
        assertEquals(temp, image.getHeight());
    }

    /**
     * test for image width setter
     */
    @Test
    public void testImageWidthSetter() {
        int temp = 3000;
        image.setWidth(temp);
        assertEquals(temp, image.getWidth());
    }
}
