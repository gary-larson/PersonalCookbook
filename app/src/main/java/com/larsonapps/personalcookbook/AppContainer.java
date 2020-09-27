package com.larsonapps.personalcookbook;

import android.app.Application;
import android.content.Context;

import com.larsonapps.personalcookbook.model.CookbookRepository;

public class AppContainer {
    private CookbookRepository cookbookRepository;

    public AppContainer(Context context) {
        cookbookRepository = new CookbookRepository(context);
    }



    // creates a repository
    public CookbookRepository getCookbookRepository() {return cookbookRepository;}
}
