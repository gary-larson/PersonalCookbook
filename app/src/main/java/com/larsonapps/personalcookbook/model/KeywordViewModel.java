package com.larsonapps.personalcookbook.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.larsonapps.personalcookbook.AppContainer;
import com.larsonapps.personalcookbook.CookbookApplication;
import com.larsonapps.personalcookbook.data.KeywordEntity;

import java.util.List;

/**
 * Class for keyword data
 */
public class KeywordViewModel extends AndroidViewModel {
    // Declare variables
    CookbookRepository mRepository;
    LiveData<List<KeywordEntity>> mKeywords;

    /**
     * Constructor for application
     * @param application to use
     */
    public KeywordViewModel(Application application) {
        super(application);
        AppContainer appContainer = ((CookbookApplication) application).getAppContainer();
        mRepository = appContainer.getCookbookRepository();
    }

    /**
     * Method to get a list of keywords  for a recipe through live data
     * @param recipeId to get keywords for
     * @return list of keywords
     */
    public LiveData<List<KeywordEntity>> getKeywords(int recipeId) {
        if (mKeywords == null) {
            mKeywords = mRepository.getKeywords(recipeId);
        }
        return mKeywords;
    }

    /**
     * Method to add a keyword in the database
     * @param keyword to add
     */
    public void insertKeyword(KeywordEntity keyword) {
        mRepository.insertKeyword(keyword);
    }

    /**
     * Method to delete a keyword from the database
     * @param keyword to delete
     */
    public void deleteKeyword(KeywordEntity keyword) {
        mRepository.deleteKeyword(keyword);
    }
}
