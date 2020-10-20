package com.larsonapps.personalcookbook.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.larsonapps.personalcookbook.AppContainer;
import com.larsonapps.personalcookbook.CookbookApplication;
import com.larsonapps.personalcookbook.data.CookNoteEntity;
import com.larsonapps.personalcookbook.data.StepEntity;

import java.util.List;

public class CookNoteViewModel extends AndroidViewModel {
    // Declare variables
    CookbookRepository mRepository;
    LiveData<List<CookNoteEntity>> mNotes;

    public CookNoteViewModel(Application application) {
        super(application);
        AppContainer appContainer = ((CookbookApplication) application).getAppContainer();
        mRepository = appContainer.getCookbookRepository();
    }

    public LiveData<List<CookNoteEntity>> getNotes(int recipeId) {
        if (mNotes == null) {
            mNotes = mRepository.getNotes(recipeId);
        }
        return mNotes;
    }

    public void insertCookNote(CookNoteEntity cookNote) {
        mRepository.insertCookNote(cookNote);
    }

    public void updateCookNote(CookNoteEntity cookNote) {
        mRepository.updateCookNote(cookNote);
    }

    public void deleteCookNote(CookNoteEntity cookNote) {
        mRepository.deleteCookNote(cookNote);
    }
}
