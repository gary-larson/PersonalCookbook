package com.larsonapps.personalcookbook.model;

import android.content.Context;

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

import java.util.List;

public class CookbookRepository {
    // Declare variables
    private CookbookExecutor mExecutor;
    private CookbookDao mDao;

    public CookbookRepository(Context context) {
        // Initialize variables
        mExecutor = new CookbookExecutor();
        CookbookRoomDatabase cookbookRoomDatabase = CookbookRoomDatabase.getDatabase(context);
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
}
