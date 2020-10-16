package com.larsonapps.personalcookbook.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.larsonapps.personalcookbook.AppContainer;
import com.larsonapps.personalcookbook.CookbookApplication;
import com.larsonapps.personalcookbook.data.ImageEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;

import java.util.List;

public class ImageViewModel extends AndroidViewModel {
    // Declare variables
    CookbookRepository mRepository;
    LiveData<List<ImageEntity>> mImages;

    public ImageViewModel(Application application) {
        super(application);
        AppContainer appContainer = ((CookbookApplication) application).getAppContainer();
        mRepository = appContainer.getCookbookRepository();
    }

    public LiveData<List<ImageEntity>> getImages(int recipeId) {
        if (mImages == null) {
            mImages = mRepository.getImages(recipeId);
        }
        return mImages;
    }

    public void insertImage(ImageEntity image) {
        mRepository.insertImage(image);
    }

    public void updateImage(ImageEntity image) {
        mRepository.updateImage(image);
    }
}
