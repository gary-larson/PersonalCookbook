package com.larsonapps.personalcookbook;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;
import com.larsonapps.personalcookbook.data.ImageEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class CookbookDaoImageAndroidTests {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    // Declare constants
    private CookbookDao cookbookDao;
    private CookbookRoomDatabase db;
    private static final String NAME_VALUE_1 = "Chocolate Cookies";
    private static final String SHORT_DESCRIPTION_1 = "Short description";
    private static final String DESCRIPTION_VALUE_1 = "Sufficient description";
    private static final int SERVINGS_VALUE_1 = 12;
    private static final int PREP_TIME_VALUE_1 = 40;
    private static final int COOK_TIME_VALUE_1 = 12;
    private static final int TOTAL_TIME_VALUE_1 = 52;
    private static final String COPYRIGHT_VALUE_1 = "copyright 5201";
    private static final String PERSONAL_NOTES_VALUE_1 = "Personal note";
    private static final String NAME_VALUE_2 = "Apple Pie";
    private static final String SHORT_DESCRIPTION_2 = "another short description";
    private static final String DESCRIPTION_VALUE_2 = "Old fashioned apple pie";
    private static final int SERVINGS_VALUE_2 = 8;
    private static final int PREP_TIME_VALUE_2 = 35;
    private static final int COOK_TIME_VALUE_2 = 60;
    private static final int TOTAL_TIME_VALUE_2 = 95;
    private static final String COPYRIGHT_VALUE_2 = "copyright 2020";
    private static final String PERSONAL_NOTES_VALUE_2 = "Personal note two";
    private static final int IMAGE_ID_VALUE_1 = 0;
    private static final String TYPE_VALUE_1 = "Local";
    private static final String IMAGE_URL_VALUE_1 = "c:\646486468.jpg";
    private static final int HEIGHT_VALUE_1 = 250;
    private static final int WIDTH_VALUE_1 = 250;
    private static final int IMAGE_ID_VALUE_2 = 0;
    private static final String TYPE_VALUE_2 = "Internet";
    private static final String IMAGE_URL_VALUE_2 = "https://88686448.jpg";
    private static final int HEIGHT_VALUE_2 = 640;
    private static final int WIDTH_VALUE_2 = 480;
    private static final int IMAGE_ID_VALUE_3 = 0;
    private static final String TYPE_VALUE_3 = "Internet";
    private static final String IMAGE_URL_VALUE_3 = "https://4894848.jpg";
    private static final int HEIGHT_VALUE_3 = 1200;
    private static final int WIDTH_VALUE_3 = 1400;
    // declare variables
    RecipeEntity recipeEntity1 = new RecipeEntity(0, NAME_VALUE_1, SHORT_DESCRIPTION_1,
            DESCRIPTION_VALUE_1, SERVINGS_VALUE_1, PREP_TIME_VALUE_1, COOK_TIME_VALUE_1,
            TOTAL_TIME_VALUE_1, COPYRIGHT_VALUE_1, PERSONAL_NOTES_VALUE_1);
    RecipeEntity recipeEntity2 = new RecipeEntity(0, NAME_VALUE_2, SHORT_DESCRIPTION_2,
            DESCRIPTION_VALUE_2, SERVINGS_VALUE_2, PREP_TIME_VALUE_2, COOK_TIME_VALUE_2,
            TOTAL_TIME_VALUE_2, COPYRIGHT_VALUE_2, PERSONAL_NOTES_VALUE_2);
    ImageEntity image1;
    ImageEntity image2;
    ImageEntity image3;
    int recipeId1;
    int recipeId2;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, CookbookRoomDatabase.class).build();
        cookbookDao = db.cookbookDao();
        // insert recipe 1
        cookbookDao.insertRecipe(recipeEntity1);
        recipeId1 = cookbookDao.getRecipeId(NAME_VALUE_1);
        // add another recipe
        cookbookDao.insertRecipe((recipeEntity2));
        recipeId2 = cookbookDao.getRecipeId(NAME_VALUE_2);
        image1 = new ImageEntity(IMAGE_ID_VALUE_1, recipeId1, TYPE_VALUE_1,
                IMAGE_URL_VALUE_1, HEIGHT_VALUE_1, WIDTH_VALUE_1);
        image2 = new ImageEntity(IMAGE_ID_VALUE_2, recipeId1, TYPE_VALUE_2,
                IMAGE_URL_VALUE_2, HEIGHT_VALUE_2, WIDTH_VALUE_2);
        image3 = new ImageEntity(IMAGE_ID_VALUE_3, recipeId2, TYPE_VALUE_3,
                IMAGE_URL_VALUE_3, HEIGHT_VALUE_3, WIDTH_VALUE_3);
        // this tests insert image
        cookbookDao.insertImage(image1);
        cookbookDao.insertImage(image2);
        cookbookDao.insertImage(image3);
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testInsertAllImagesAndGetAllImages() {
        // insert all images
        List<ImageEntity> images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        // add to database
        cookbookDao.insertAllImages(images);
        // get all images for recipe 1 and test
        cookbookDao.getAllImages(recipeId1).observeForever(newImages -> {
            boolean isAsserted = false;
            assertEquals(4, newImages.size());
            for (ImageEntity image : newImages) {
                if (image.getImageUrl().equals(IMAGE_URL_VALUE_1)) {
                    assertEquals(TYPE_VALUE_1, image.getType());
                    assertEquals(HEIGHT_VALUE_1, image.getHeight());
                    assertEquals(WIDTH_VALUE_1, image.getWidth());
                    isAsserted = true;
                    break;
                }
            }
            if (!isAsserted) {
                assertEquals(2,1);
            }
        });
    }

    @Test
    public void testDeleteImageAndGetImageIdByCaption() {
        int imageId = cookbookDao.getImageIdByUrl(recipeId1, image1.getImageUrl());
        image1.setImageId(imageId);
        // delete image
        cookbookDao.deleteImage(image1);
        // get images for recipe 1
        cookbookDao.getAllImages(recipeId1).observeForever(newImages -> {
            for(ImageEntity image : newImages) {
                assertNotEquals(imageId, image.getImageId());
            }
        });
    }

    @Test
    public void testUpdateImageAndGetImageIdByCaption() {
        int imageId = cookbookDao.getImageIdByUrl(recipeId1, IMAGE_URL_VALUE_1);
        // modify image
        String temp = "https://54644645.jpg";
        image1.setImageId(imageId);
        image1.setImageUrl(temp);
        cookbookDao.updateImage(image1);
        // get images for recipe 1 and test
        cookbookDao.getAllImages(recipeId1).observeForever(newImages -> {
            boolean isAsserted = false;
            for (ImageEntity image : newImages) {
                if (image.getImageId() == imageId) {
                    assertEquals(temp, image.getImageUrl());
                    isAsserted = true;
                }
            }
            if (!isAsserted) {
                assertEquals(1, 2);
            }
        });
    }
}
