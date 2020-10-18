package com.larsonapps.personalcookbook.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.larsonapps.personalcookbook.AppContainer;
import com.larsonapps.personalcookbook.CookbookApplication;
import com.larsonapps.personalcookbook.data.KeywordEntity;

import java.util.List;

public class KeywordViewModel extends AndroidViewModel {
    // Declare variables
    CookbookRepository mRepository;
    LiveData<List<KeywordEntity>> mKeywords;

    public KeywordViewModel(Application application) {
        super(application);
        AppContainer appContainer = ((CookbookApplication) application).getAppContainer();
        mRepository = appContainer.getCookbookRepository();
    }

    public LiveData<List<KeywordEntity>> getKeywords(int recipeId) {
        if (mKeywords == null) {
            mKeywords = mRepository.getKeywords(recipeId);
        }
        return mKeywords;
    }

    public void insertKeyword(KeywordEntity keyword) {
        mRepository.insertKeyword(keyword);
    }

    public void deleteKeyword(KeywordEntity keyword) {
        mRepository.deleteKeyword(keyword);
    }
}
