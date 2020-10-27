package com.larsonapps.personalcookbook.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.larsonapps.personalcookbook.AppContainer;
import com.larsonapps.personalcookbook.CookbookApplication;
import com.larsonapps.personalcookbook.data.ImageEntity;

import java.util.List;

/**
 * Class for image data
 */
public class ImageViewModel extends AndroidViewModel {
    // Declare variables
    CookbookRepository mRepository;
    LiveData<List<ImageEntity>> mImages;

    /**
     * Constructor for application
     * @param application to use
     */
    public ImageViewModel(Application application) {
        super(application);
        AppContainer appContainer = ((CookbookApplication) application).getAppContainer();
        mRepository = appContainer.getCookbookRepository();
    }

    /**
     * Method to get a list of images for a recipe
     * @param recipeId to get the images for
     * @return list of images
     */
    public LiveData<List<ImageEntity>> getImages(int recipeId) {
        if (mImages == null) {
            mImages = mRepository.getImages(recipeId);
        }
        return mImages;
    }

    /**
     * Method to add an image to the database
     * @param image to add
     */
    public void insertImage(ImageEntity image) {
        mRepository.insertImage(image);
    }

    /**
     * Method to delete an image from the database
     * @param image to delete
     */
    public void deleteImage(ImageEntity image) {
        mRepository.deleteImage(image);
    }

    /**
     * Method to add an image
     * @param image to add
     */
    public void addImage(ImageEntity image) {
        mRepository.addImage(image);
    }
}
