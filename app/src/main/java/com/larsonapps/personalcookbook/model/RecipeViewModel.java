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
import com.larsonapps.personalcookbook.data.RecipeEntity;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {
    // Declare variables
    CookbookRepository mRepository;
    LiveData<List<RecipeEntity>> mRecipes;
    LiveData<List<CategoryEntity>> mCategories;
    LiveData<RecipeEntity> mRecipe;

    public RecipeViewModel(Application application) {
        super(application);
        AppContainer appContainer = ((CookbookApplication) application).getAppContainer();
        mRepository = appContainer.getCookbookRepository();
    }

    public LiveData<List<RecipeEntity>> getRecipes(boolean isRefreshRecipes) {
        if (isRefreshRecipes || mRecipes == null) {
            mRecipes = mRepository.getRecipes();
        }
        return mRecipes;
    }

    public LiveData<List<RecipeEntity>> getRecipes(boolean isRefreshRecipes, String keyword) {
        if (isRefreshRecipes || mRecipes == null) {
            mRecipes = mRepository.getRecipes(keyword);
        }
        return mRecipes;
    }

    public LiveData<List<RecipeEntity>> getRecipes(boolean isRefreshRecipes, String[] keywords) {
        if (isRefreshRecipes || mRecipes == null) {
            mRecipes = mRepository.getRecipes(keywords);
        }
        return mRecipes;
    }

    public LiveData<List<CategoryEntity>> getCategories() {
        if (mCategories == null) {
            mCategories = mRepository.getCategories();
        }
        return mCategories;
    }

    public void addCategory(String category) {
        mRepository.addCategory(category);
    }

    public LiveData<RecipeEntity> getRecipe(int recipeId) {
        if (mRecipe == null) {
            mRecipe = mRepository.getRecipe(recipeId);
        }
        return mRecipe;
    }

    public void addRecipe(RecipeEntity recipe) {
        mRepository.addRwcipe(recipe);
    }

    public int getRecipeId(String name) {
        return mRepository.getRecipeId(name);
    }
}