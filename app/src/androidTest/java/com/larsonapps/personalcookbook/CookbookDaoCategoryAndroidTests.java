package com.larsonapps.personalcookbook;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.larsonapps.personalcookbook.data.CategoryEntity;
import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class CookbookDaoCategoryAndroidTests {
    /**
     * Method to set test rule
     */
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    // Declare constants
    private CookbookDao cookbookDao;
    private CookbookRoomDatabase db;
    private static final int CATEGORY_ID_VALUE_1 = 3;
    private static final String CATEGORY_NAME_VALUE_1 = "Pasta";
    private static final int CATEGORY_ID_VALUE_2 = 9;
    private static final String CATEGORY_NAME_VALUE_2 = "Snack";
    CategoryEntity category1;
    CategoryEntity category2;


    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, CookbookRoomDatabase.class).build();
        cookbookDao = db.cookbookDao();
        category1 = new CategoryEntity(CATEGORY_ID_VALUE_1, CATEGORY_NAME_VALUE_1);
        category2 = new CategoryEntity(CATEGORY_ID_VALUE_2, CATEGORY_NAME_VALUE_2);
        // tests insertCategory no tests work if this fails
        cookbookDao.insertCategory(category1);
        cookbookDao.insertCategory(category2);
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void testGetAllCategories() {
        // get categories and test
        cookbookDao.getAllCategories().observeForever(newCategories -> {
            assertEquals(2, newCategories.size());
            int position = 0;
            if (!newCategories.get(0).getCategoryName().equals(CATEGORY_NAME_VALUE_1)) {
                position = 1;
            }
            assertEquals(CATEGORY_NAME_VALUE_1, newCategories.get(position).getCategoryName());
        });
    }

    @Test
    public void testDeleteCategoryAndGetCategoryIdByName() {
        int categoryId = cookbookDao.getCategoryIdByName(CATEGORY_NAME_VALUE_1);
        category1.setCategoryId(categoryId);
        // delete category
        cookbookDao.deleteCategory(category1);
        // get categories and test
        cookbookDao.getAllCategories().observeForever(newCategories -> {
            for (CategoryEntity category : newCategories) {
                assertNotEquals(CATEGORY_NAME_VALUE_1, category.getCategoryName());
            }
        });
    }
}
