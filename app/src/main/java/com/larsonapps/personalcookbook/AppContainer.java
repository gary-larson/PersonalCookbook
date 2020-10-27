package com.larsonapps.personalcookbook;

import android.content.Context;

import com.larsonapps.personalcookbook.model.CookbookRepository;

/**
 * Class to expose cookbook repository so anly one instance is opened
 */
public class AppContainer {
    // declare variable
    private CookbookRepository cookbookRepository;

    /**
     * Constructor to create an instance of cookbook repository
     * @param context to use
     */
    public AppContainer(Context context) {
        cookbookRepository = new CookbookRepository(context);
    }


    /**
     * Getter for cookbook repository
     * @return cookbook repository
     */
    public CookbookRepository getCookbookRepository() {return cookbookRepository;}
}
