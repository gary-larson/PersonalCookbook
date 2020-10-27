package com.larsonapps.personalcookbook.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.larsonapps.personalcookbook.AppContainer;
import com.larsonapps.personalcookbook.CookbookApplication;
import com.larsonapps.personalcookbook.data.CookNoteEntity;

import java.util.List;

/**
 * Class to handle data requests for cook notes
 */
public class CookNoteViewModel extends AndroidViewModel {
    // Declare variables
    CookbookRepository mRepository;
    LiveData<List<CookNoteEntity>> mNotes;

    /**
     * Constructor for application
     * @param application to use
     */
    public CookNoteViewModel(Application application) {
        super(application);
        AppContainer appContainer = ((CookbookApplication) application).getAppContainer();
        mRepository = appContainer.getCookbookRepository();
    }

    /**
     * Method to get cook notes through live data
     * @param recipeId to get the cook notes for
     * @return list of cook notes
     */
    public LiveData<List<CookNoteEntity>> getNotes(int recipeId) {
        if (mNotes == null) {
            mNotes = mRepository.getNotes(recipeId);
        }
        return mNotes;
    }

    /**
     * Method to add cook note to database
     * @param cookNote to add
     */
    public void insertCookNote(CookNoteEntity cookNote) {
        mRepository.insertCookNote(cookNote);
    }

    /**
     * Method to update a cook note in the database
     * @param cookNote to update
     */
    public void updateCookNote(CookNoteEntity cookNote) {
        mRepository.updateCookNote(cookNote);
    }

    /**
     * Method to delete a cook note from the database
     * @param cookNote to delete
     */
    public void deleteCookNote(CookNoteEntity cookNote) {
        mRepository.deleteCookNote(cookNote);
    }
}
