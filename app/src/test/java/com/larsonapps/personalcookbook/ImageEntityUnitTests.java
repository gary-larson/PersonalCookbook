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
    private static final String CAPTION_VALUE = "picture of chocolate chip cookies";

    // Create RecipeImage
    ImageEntity image = new ImageEntity(IMAGE_ID_VALUE, RECIPE_ID_VALUE, TYPE_VALUE,
            IMAGE_URL_VALUE, HEIGHT_VALUE, WIDTH_VALUE, CAPTION_VALUE);

    // Tests for the constructor and Getters
    @Test
    public void testImageIdGetter() {
        assertEquals(IMAGE_ID_VALUE, image.getImageId());
    }

    @Test
    public void testImageRecipeIdGetter() {
        assertEquals(RECIPE_ID_VALUE, image.getRecipeId());
    }

    @Test
    public void testImageTypeGetter() {
        assertEquals(TYPE_VALUE, image.getType());
    }

    @Test
    public void testImageUrlGetter() {
        assertEquals(IMAGE_URL_VALUE, image.getImageUrl());
    }

    @Test
    public void testImageHeightGetter() {
        assertEquals(HEIGHT_VALUE, image.getHeight());
    }

    @Test
    public void testImageWidthGetter() {
        assertEquals(WIDTH_VALUE, image.getWidth());
    }

    @Test
    public void testImageCaptionGetter() {
        assertEquals(CAPTION_VALUE, image.getCaption());
    }

    // Test default constructor and setters
    @Test
    public void testImageIdSetter() {
        int temp = 255;
        image.setImageId(temp);
        assertEquals(temp, image.getImageId());
    }

    @Test
    public void testImageRecipeIdSetter() {
        int temp = 91;
        image.setRecipeId(temp);
        assertEquals(temp, image.getRecipeId());
    }

    @Test
    public void testImageTypeSetter() {
        String temp = "local";
        image.setType(temp);
        assertEquals(temp, image.getType());
    }

    @Test
    public void testImageUrlSetter() {
        String temp = "c:/4648486.jpg";
        image.setImageUrl(temp);
        assertEquals(temp, image.getImageUrl());
    }

    @Test
    public void testImageHeightSetter() {
        int temp = 4000;
        image.setHeight(temp);
        assertEquals(temp, image.getHeight());
    }

    @Test
    public void testImageWidthSetter() {
        int temp = 3000;
        image.setWidth(temp);
        assertEquals(temp, image.getWidth());
    }

    @Test
    public void testImageCaptionSetter() {
        String temp = "Great picture of meal";
        image.setCaption(temp);
        assertEquals(temp, image.getCaption());
    }
}
