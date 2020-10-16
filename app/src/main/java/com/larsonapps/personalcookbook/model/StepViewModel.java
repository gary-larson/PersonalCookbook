package com.larsonapps.personalcookbook.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.larsonapps.personalcookbook.AppContainer;
import com.larsonapps.personalcookbook.CookbookApplication;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.data.StepEntity;

import java.util.List;

public class StepViewModel extends AndroidViewModel {
    // Declare variables
    CookbookRepository mRepository;
    LiveData<List<StepEntity>> mSteps;

    public StepViewModel(Application application) {
        super(application);
        AppContainer appContainer = ((CookbookApplication) application).getAppContainer();
        mRepository = appContainer.getCookbookRepository();
    }

    public LiveData<List<StepEntity>> getSteps(int recipeId) {
        if (mSteps == null) {
            mSteps = mRepository.getSteps(recipeId);
        }
        return mSteps;
    }

    public void insertStep(StepEntity step) {
        mRepository.insertStep(step);
    }

    public void updateStep(StepEntity step) {
        mRepository.updateStep(step);
    }
}
