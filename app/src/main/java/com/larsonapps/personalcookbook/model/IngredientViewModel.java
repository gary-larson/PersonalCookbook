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

/**
 * Class to handle ingredient data
 */
public class IngredientViewModel extends AndroidViewModel {
    // Declare variables
    CookbookRepository mRepository;
    LiveData<List<IngredientEntity>> mIngredients;

    /**
     * Constructor for application
     * @param application to use
     */
    public IngredientViewModel(Application application) {
        super(application);
        AppContainer appContainer = ((CookbookApplication) application).getAppContainer();
        mRepository = appContainer.getCookbookRepository();
    }

    /**
     * Method to get list of ingredients for a recipe
     * @param recipeId to get ingredients for
     * @return list of ingredients through live data
     */
    public LiveData<List<IngredientEntity>> getIngredients(int recipeId) {
        if (mIngredients == null) {
            mIngredients = mRepository.getIngredients(recipeId);
        }
        return mIngredients;
    }

    /**
     * Method to add ingredient to the database
     * @param ingredient to add
     */
    public void insertIngredient(IngredientEntity ingredient) {
        mRepository.insertIngredient(ingredient);
    }

    /**
     * Method to update and ingredient in the database
     * @param ingredient to update
     */
    public void updateIngredient(IngredientEntity ingredient) {
        mRepository.updateIngredient(ingredient);
    }

    /**
     * Method to delet an ingredient from the database
     * @param ingredient to delete
     */
    public void deleteIngredient(IngredientEntity ingredient) {
        mRepository.deleteIngredient(ingredient);
    }
}