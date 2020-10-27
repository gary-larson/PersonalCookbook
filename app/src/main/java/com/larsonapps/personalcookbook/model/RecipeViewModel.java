/*
This program saves all recipes on a device with search and editing capabilities.
Copyright (C) 2020  Larson Apps - Gary Larson

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.larsonapps.personalcookbook.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.larsonapps.personalcookbook.AppContainer;
import com.larsonapps.personalcookbook.CookbookApplication;
import com.larsonapps.personalcookbook.data.CategoryEntity;
import com.larsonapps.personalcookbook.data.CookNoteEntity;
import com.larsonapps.personalcookbook.data.ImageEntity;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.data.KeywordEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.StepEntity;

import java.util.List;

/**
 * Class for recipe data
 */
public class RecipeViewModel extends AndroidViewModel {
    // Declare variables
    CookbookRepository mRepository;
    LiveData<List<RecipeEntity>> mRecipes;
    LiveData<List<CategoryEntity>> mCategories;
    LiveData<RecipeEntity> mRecipe;

    /**
     * Constructor for application
     * @param application to use
     */
    public RecipeViewModel(Application application) {
        super(application);
        AppContainer appContainer = ((CookbookApplication) application).getAppContainer();
        mRepository = appContainer.getCookbookRepository();
    }

    /**
     * Method to get a list of recipes through live data
     * @param isRefreshRecipes to determine when to force a refresh
     * @return list of recipes through live data
     */
    public LiveData<List<RecipeEntity>> getRecipes(boolean isRefreshRecipes) {
        if (isRefreshRecipes || mRecipes == null) {
            mRecipes = mRepository.getRecipes();
        }
        return mRecipes;
    }

    /**
     * Method to get a list of recipes with a specific keyword
     * @param isRefreshRecipes when to force a refresh
     * @param keyword to get recipes for
     * @return list of recipes through live data
     */
    public LiveData<List<RecipeEntity>> getRecipes(boolean isRefreshRecipes, String keyword) {
        if (isRefreshRecipes || mRecipes == null) {
            mRecipes = mRepository.getRecipes(keyword);
        }
        return mRecipes;
    }

    /**
     * Method to get a list of recipes with one of the keywords
     * @param isRefreshRecipes when to force a refresh
     * @param keywords to get recipes for
     * @return list of recipes through live data
     */
    public LiveData<List<RecipeEntity>> getRecipes(boolean isRefreshRecipes, String[] keywords) {
        if (isRefreshRecipes || mRecipes == null) {
            mRecipes = mRepository.getRecipes(keywords);
        }
        return mRecipes;
    }

    /**
     * Method to get a list of categories
     * @return list of categories through live data
     */
    public LiveData<List<CategoryEntity>> getCategories() {
        if (mCategories == null) {
            mCategories = mRepository.getCategories();
        }
        return mCategories;
    }

    /**
     * Method to add a category to the database
     * @param category to add
     */
    public void addCategory(String category) {
        mRepository.addCategory(category);
    }

    /**
     * Method to get a recipe from the database
     * @param recipeId of the recipe to get
     * @return a recipe
     */
    public LiveData<RecipeEntity> getRecipe(int recipeId) {
        if (mRecipe == null) {
            mRecipe = mRepository.getRecipe(recipeId);
        }
        return mRecipe;
    }

    /**
     * Method to add a recipe to the database
     * @param recipe to add
     */
    public void addRecipe(RecipeEntity recipe) {
        mRepository.addRecipe(recipe);
    }

    /**
     * Method to update a recipe in the database
     * @param recipe to update
     */
    public void updateRecipe(RecipeEntity recipe) {
        mRepository.updateRecipe(recipe);
    }

    /**
     * Method to add a recipe with cook notes, ingredients, steps, images and keywords
     * to the database
     * @param recipe to add
     * @param cookNotes to add
     * @param ingredients to add
     * @param steps to add
     * @param images to add
     * @param keywords to add
     */
    public void insertAll(RecipeEntity recipe, List<CookNoteEntity> cookNotes,
                          List<IngredientEntity> ingredients, List<StepEntity> steps,
                          List<ImageEntity> images, List<KeywordEntity> keywords) {
        mRepository.insertAll(recipe, cookNotes, ingredients, steps, images, keywords);
    }

    /**
     * Method to delete a category from the database
     * @param category to delete
     */
    public void deleteCategory(CategoryEntity category) {
        mRepository.deleteCategory(category);
    }

    /**
     * Method to delete a recipe from the database
     * @param recipe to delete
     */
    public void deleteRecipe(RecipeEntity recipe) {
        mRepository.deleteRecipe(recipe);
    }
}