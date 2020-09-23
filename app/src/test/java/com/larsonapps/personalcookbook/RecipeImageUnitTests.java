package com.larsonapps.personalcookbook;

import com.larsonapps.personalcookbook.data.RecipeImage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RecipeImageUnitTests {
    // image data
    private static final String TYPE_VALUE = "Internet";
    private static final String IMAGE_URL_VALUE = "https://somewhere.com/5486946.jpg";
    private static final int HEIGHT_VALUE = 1720;
    private static final int WIDTH_VALUE = 2200;
    private static final String CAPTION_VALUE = "picture of chocolate chip cookies";

    // Create RecipeImage
    RecipeImage image = new RecipeImage(TYPE_VALUE, IMAGE_URL_VALUE, HEIGHT_VALUE, WIDTH_VALUE,
            CAPTION_VALUE);

    // Tests for the constructor and Getters

    @Test
    public void testRecipeImageTypeGetter() {
        assertEquals(TYPE_VALUE, image.getType());
    }

    @Test
    public void testRecipeImageUrlGetter() {
        assertEquals(IMAGE_URL_VALUE, image.getImageUrl());
    }

    @Test
    public void testRecipeImageHeightGetter() {
        assertEquals(HEIGHT_VALUE, image.getHeight());
    }

    @Test
    public void testRecipeImageWidthGetter() {
        assertEquals(WIDTH_VALUE, image.getWidth());
    }

    @Test
    public void testRecipeImageCaptionGetter() {
        assertEquals(CAPTION_VALUE, image.getCaption());
    }

    // Test default constructor and setters
    RecipeImage imageDefault = new RecipeImage();
    @Test
    public void testRecipeImageTypeSetter() {
        String temp = "local";
        imageDefault.setType(temp);
        assertEquals(temp, imageDefault.getType());
    }

    @Test
    public void testRecipeImageUrlSetter() {
        String temp = "c:/4648486.jpg";
        imageDefault.setImageUrl(temp);
        assertEquals(temp, imageDefault.getImageUrl());
    }

    @Test
    public void testRecipeImageHeightSetter() {
        int temp = 4000;
        imageDefault.setHeight(temp);
        assertEquals(temp, imageDefault.getHeight());
    }

    @Test
    public void testRecipeImageWidthSetter() {
        int temp = 3000;
        imageDefault.setWidth(temp);
        assertEquals(temp, imageDefault.getWidth());
    }

    @Test
    public void testRecipeImageCaptionSetter() {
        String temp = "Great picture of meal";
        imageDefault.setCaption(temp);
        assertEquals(temp, imageDefault.getCaption());
    }
}
