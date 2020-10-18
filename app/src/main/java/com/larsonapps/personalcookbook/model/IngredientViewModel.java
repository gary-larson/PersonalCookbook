package com.larsonapps.personalcookbook.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.larsonapps.personalcookbook.AppContainer;
import com.larsonapps.personalcookbook.CookbookApplication;
import com.larsonapps.personalcookbook.data.ImageEntity;
import com.larsonapps.personalcookbook.data.IngredientEntity;

import java.util.List;

public class IngredientViewModel extends AndroidViewModel {
    // Declare variables
    CookbookRepository mRepository;
    LiveData<List<IngredientEntity>> mIngredients;

    public IngredientViewModel(Application application) {
        super(application);
        AppContainer appContainer = ((CookbookApplication) application).getAppContainer();
        mRepository = appContainer.getCookbookRepository();
    }

    public LiveData<List<IngredientEntity>> getIngredients(int recipeId) {
        if (mIngredients == null) {
            mIngredients = mRepository.getIngredients(recipeId);
        }
        return mIngredients;
    }

    public void insertIngredient(IngredientEntity ingredient) {
        mRepository.insertIngredient(ingredient);
    }

    public void updateIngredient(IngredientEntity ingredient) {
        mRepository.updateIngredient(ingredient);
    }

    public void deleteIngredient(IngredientEntity ingredient) {
        mRepository.deleteIngredient(ingredient);
    }
}