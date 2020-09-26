package com.larsonapps.personalcookbook;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.RecipeImageEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CookbookDaoImageAndroidTests {
    // Declare constants
    private CookbookDao cookbookDao;
    private CookbookRoomDatabase db;
    private static final String NAME_VALUE_1 = "Chocolate Cookies";
    private static final String DESCRIPTION_VALUE_1 = "Sufficient description";
    private static final int SERVINGS_VALUE_1 = 12;
    private static final int PREP_TIME_VALUE_1 = 40;
    private static final int COOK_TIME_VALUE_1 = 12;
    private static final int TOTAL_TIME_VALUE_1 = 52;
    private static final String NOTES_VALUE_1 = "These are the notes by the cook.";
    private static final String COPYRIGHT_VALUE_1 = "copyright 5201";
    private static final String NAME_VALUE_2 = "Apple Pie";
    private static final String DESCRIPTION_VALUE_2 = "Old fashioned apple pie";
    private static final int SERVINGS_VALUE_2 = 8;
    private static final int PREP_TIME_VALUE_2 = 35;
    private static final int COOK_TIME_VALUE_2 = 60;
    private static final int TOTAL_TIME_VALUE_2 = 95;
    private static final String NOTES_VALUE_2 = "These are apple pie notes by the cook.";
    private static final String COPYRIGHT_VALUE_2 = "copyright 2020";
    private static final int IMAGE_ID_VALUE_1 = 0;
    private static final String TYPE_VALUE_1 = "Local";
    private static final String IMAGE_URL_VALUE_1 = "c:\646486468.jpg";
    private static final int HEIGHT_VALUE_1 = 250;
    private static final int WIDTH_VALUE_1 = 250;
    private static final String CAPTION_VALUE_1 = "Picture of apple pie";
    private static final int IMAGE_ID_VALUE_2 = 0;
    private static final String TYPE_VALUE_2 = "Internet";
    private static final String IMAGE_URL_VALUE_2 = "https://88686448.jpg";
    private static final int HEIGHT_VALUE_2 = 640;
    private static final int WIDTH_VALUE_2 = 480;
    private static final String CAPTION_VALUE_2 = "Picture of cookies";
    private static final int IMAGE_ID_VALUE_3 = 0;
    private static final String TYPE_VALUE_3 = "Internet";
    private static final String IMAGE_URL_VALUE_3 = "https://4894848.jpg";
    private static final int HEIGHT_VALUE_3 = 1200;
    private static final int WIDTH_VALUE_3 = 1400;
    private static final String CAPTION_VALUE_3 = "Great picture";
    // declare variables
    RecipeEntity recipeEntity1 = new RecipeEntity(0, NAME_VALUE_1, DESCRIPTION_VALUE_1,
            SERVINGS_VALUE_1, PREP_TIME_VALUE_1, COOK_TIME_VALUE_1, TOTAL_TIME_VALUE_1,
            NOTES_VALUE_1, COPYRIGHT_VALUE_1);
    RecipeEntity recipeEntity2 = new RecipeEntity(0, NAME_VALUE_2, DESCRIPTION_VALUE_2,
            SERVINGS_VALUE_2, PREP_TIME_VALUE_2, COOK_TIME_VALUE_2, TOTAL_TIME_VALUE_2,
            NOTES_VALUE_2, COPYRIGHT_VALUE_2);
    RecipeImageEntity image1;
    RecipeImageEntity image2;
    RecipeImageEntity image3;
    int recipeId1;
    int recipeId2;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, CookbookRoomDatabase.class).build();
        cookbookDao = db.cookbookDao();
        // insert recipe 1
        cookbookDao.insertRecipe(recipeEntity1);
        // get all records
        List<RecipeEntity> recipeEntities = cookbookDao.getAllRecipes();
        // test size
        assertEquals(1, recipeEntities.size());
        // get recipe id 1
        recipeId1 = recipeEntities.get(0).getId();
        // add another recipe
        cookbookDao.insertRecipe((recipeEntity2));
        // get all recipes
        recipeEntities = cookbookDao.getAllRecipes();
        // test size
        assertEquals(2, recipeEntities.size());
        // assign recipe id 3
        if (recipeEntities.get(0).getId() == recipeId1) {
            recipeId2 = recipeEntities.get(1).getId();
        } else {
            recipeId2 = recipeEntities.get(0).getId();
        }
        image1 = new RecipeImageEntity(IMAGE_ID_VALUE_1, recipeId1, TYPE_VALUE_1,
                IMAGE_URL_VALUE_1, HEIGHT_VALUE_1, WIDTH_VALUE_1, CAPTION_VALUE_1);
        image2 = new RecipeImageEntity(IMAGE_ID_VALUE_2, recipeId1, TYPE_VALUE_2,
                IMAGE_URL_VALUE_2, HEIGHT_VALUE_2, WIDTH_VALUE_2, CAPTION_VALUE_2);
        image3 = new RecipeImageEntity(IMAGE_ID_VALUE_3, recipeId2, TYPE_VALUE_3,
                IMAGE_URL_VALUE_3, HEIGHT_VALUE_3, WIDTH_VALUE_3, CAPTION_VALUE_3);
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testInsertAllImagesAndGetAllImages() {
        // insert all images
        List<RecipeImageEntity> images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        // add to database
        cookbookDao.insertAllImages(images);
        // get all images for recipe 1
        List<RecipeImageEntity> images1 = cookbookDao.getAllImages(recipeId1);
        // get all images for recipe 2
        List<RecipeImageEntity> images2 = cookbookDao.getAllImages(recipeId2);
        // test
        assertEquals(2, images1.size());
        assertEquals(1, images2.size());
    }

    @Test
    public void testInsertImage() {
        // insert image into database
        cookbookDao.insertImage(image1);
        // get images for recipe 1
        List<RecipeImageEntity> images = cookbookDao.getAllImages(recipeId1);
        // test
        assertEquals(1, images.size());
        assertEquals(recipeId1, images.get(0).getRecipeId());
        assertEquals(TYPE_VALUE_1, images.get(0).getType());
        assertEquals(IMAGE_URL_VALUE_1, images.get(0).getImageUrl());
        assertEquals(HEIGHT_VALUE_1, images.get(0).getHeight());
        assertEquals(WIDTH_VALUE_1, images.get(0).getWidth());
        // get images for recipe 2
        List<RecipeImageEntity> images1 = cookbookDao.getAllImages(recipeId2);
        // test
        assertEquals(0, images1.size());
    }

    @Test
    public void testDeleteImage() {
        // insert image into database
        cookbookDao.insertImage(image1);
        // get images for recipe 1
        List<RecipeImageEntity> images = cookbookDao.getAllImages(recipeId1);
        assertEquals(1, images.size());
        // delete image
        cookbookDao.deleteImage(images.get(0));
        // get images for recipe 1
        List<RecipeImageEntity> images1 = cookbookDao.getAllImages(recipeId1);
        // test
        assertEquals(0, images1.size());
    }

    @Test
    public void testUpdateImage() {
        // insert image into database
        cookbookDao.insertImage(image1);
        // get images for recipe 1
        List<RecipeImageEntity> images = cookbookDao.getAllImages(recipeId1);
        assertEquals(1, images.size());
        // modify image
        String temp = "https://54644645.jpg";
        images.get(0).setImageUrl(temp);
        cookbookDao.updateImage(images.get(0));
        // get images for recipe 1
        List<RecipeImageEntity> images1 = cookbookDao.getAllImages(recipeId1);
        // test
        assertEquals(1, images1.size());
        assertEquals(temp, images1.get(0).getImageUrl());
    }
}
