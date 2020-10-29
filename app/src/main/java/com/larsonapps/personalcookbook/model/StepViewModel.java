package com.larsonapps.personalcookbook.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.larsonapps.personalcookbook.AppContainer;
import com.larsonapps.personalcookbook.CookbookApplication;
import com.larsonapps.personalcookbook.data.StepEntity;

import java.util.List;

/**
 * Class for step data
 */
public class StepViewModel extends AndroidViewModel {
    // Declare variables
    CookbookRepository mRepository;
    LiveData<List<StepEntity>> mSteps;

    /**
     * Constructor for application
     * @param application to use
     */
    public StepViewModel(Application application) {
        super(application);
        AppContainer appContainer = ((CookbookApplication) application).getAppContainer();
        mRepository = appContainer.getCookbookRepository();
    }

    /**
     * Method to get list of steps with live data for a recipe
     * @param recipeId of recipe to get steps for
     * @return list of steps through live data
     */
    public LiveData<List<StepEntity>> getSteps(int recipeId) {
        if (mSteps == null) {
            mSteps = mRepository.getSteps(recipeId);
        }
        return mSteps;
    }

    /**
     * Method to add a step to the database
     * @param step to add
     */
    public void insertStep(StepEntity step) {
        mRepository.insertStep(step);
    }

    /**
     * Method to update a step in the database
     * @param step to update
     */
    public void updateStep(StepEntity step) {
        mRepository.updateStep(step);
    }

    /**
     * Method to remove a step from the database
     * @param step to remove
     */
    public void deleteStep(StepEntity step) {
        mRepository.deleteStep(step);
    }
}
