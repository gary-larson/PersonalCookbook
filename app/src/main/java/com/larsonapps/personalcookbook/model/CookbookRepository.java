package com.larsonapps.personalcookbook.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.larsonapps.personalcookbook.data.CategoryEntity;
import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;
import com.larsonapps.personalcookbook.data.ImageEntity;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.data.KeywordEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.utilities.CookbookExecutor;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class CookbookRepository {
    // Declare constants
    private static final String INTERNET_TYPE = "Internet";
    private static final String TAG = CookbookRepository.class.getSimpleName();
    // Declare variables
    private CookbookDao mDao;
    private CookbookExecutor mExecutor;

    public CookbookRepository(Context context) {
        // Initialize variables
        CookbookRoomDatabase cookbookRoomDatabase = CookbookRoomDatabase.getDatabase(context);
        mExecutor = new CookbookExecutor();
        mDao = cookbookRoomDatabase.cookbookDao();
    }

    public LiveData<List<RecipeEntity>> getRecipes () {
        return mDao.getAllRecipes();
    }

    public LiveData<List<RecipeEntity>> getRecipes(String keyword) {
        return mDao.getAllRecipes(keyword);
    }

    public LiveData<List<RecipeEntity>> getRecipes(String[] keywords) {
        return mDao.getAllRecipes(keywords);
    }

    public LiveData<List<IngredientEntity>> getIngredients(int recipeId) {
        return mDao.getAllIngredients(recipeId);
    }

    public LiveData<List<StepEntity>> getSteps(int recipeId) {
        return mDao.getAllSteps(recipeId);
    }

    public LiveData<List<ImageEntity>> getImages(int recipeId) {
        return mDao.getAllImages(recipeId);
    }

    public LiveData<List<KeywordEntity>> getKeywords(int recipeId) {
        return mDao.getAllKeywords(recipeId);
    }

    public LiveData<List<CategoryEntity>> getCategories() {
        return mDao.getAllCategories();
    }

    public void addCategory(String category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName(category);
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> {
            if (mDao.categoryExists(category) == 0) {
                mDao.insertCategory(categoryEntity);
            }
        });
    }

    public LiveData<RecipeEntity> getRecipe(int recipeId) {
        return mDao.getRecipe(recipeId);
    }

    public void addRwcipe(RecipeEntity recipe) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertRecipe(recipe));
    }

    public int getRecipeId(String name) {
        return mDao.getRecipeIdByName(name);
    }

    public void updateRecipe(RecipeEntity recipe) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.updateRecipe(recipe));
    }

    public void insertIngredient(IngredientEntity ingredient) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertIngredient(ingredient));
    }

    public void insertStep(StepEntity step) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertStep(step));
    }

    public void insertImage(ImageEntity image) {
        if (image.getType().equals(INTERNET_TYPE)) {
            mExecutor.execute(() -> {
                try {
                    URL url = new URL(image.getImageUrl());
                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    image.setHeight(bmp.getHeight());
                    image.setWidth(bmp.getWidth());
                } catch (IOException e) {
                    Log.e(TAG, "Exception for image: " + e.getMessage());
                }
                CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertImage(image));
            });
        }
    }

    public void updateIngredient(IngredientEntity ingredient) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.updateIngredient(ingredient));
    }

    public void updateStep(StepEntity step) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.updateStep(step));
    }

    public void updateImage(ImageEntity image) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.updateImage(image));
    }

    public void insertAll(RecipeEntity recipe, List<IngredientEntity> ingredients,
                          List<StepEntity> steps, List<ImageEntity> images,
                          List<KeywordEntity> keywords) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDao.insertRecipe(recipe);
            int recipeId = mDao.getRecipeIdByName(recipe.getName());
            if (ingredients.size() > 0) {
                for (IngredientEntity ingredient : ingredients) {
                    ingredient.setRecipeId(recipeId);
                }
                mDao.insertAllIngredients(ingredients);
            }
            if (steps.size() > 0) {
                for (StepEntity step : steps) {
                    step.setRecipeId(recipeId);
                }
                mDao.insertAllSteps(steps);
            }
            if (images.size() > 0) {
                for (ImageEntity image : images) {
                    image.setRecipeId(recipeId);
                }
                mDao.insertAllImages(images);
            }
            if (keywords.size() > 0) {
                for (KeywordEntity keyword : keywords) {
                    keyword.setRecipeId(recipeId);
                }
                mDao.insertAllKeywords(keywords);
            }
        });
    }

    public void insertKeyword(KeywordEntity keyword) {
        CookbookRoomDatabase.databaseWriteExecutor.execute(() -> mDao.insertKeyword(keyword));
    }
}
