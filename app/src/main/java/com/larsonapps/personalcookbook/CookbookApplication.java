package com.larsonapps.personalcookbook;

import android.app.Application;

/**
 * Class to run app in
 */
public class CookbookApplication extends Application {
    // declare variable
    private AppContainer appContainer;

    /**
     * Method to create app container
     */
    public void onCreate() {
        super.onCreate();
        appContainer = new AppContainer(this);
    }

    /**
     * Getter for app container
     * @return app container
     */
    public AppContainer getAppContainer() {return appContainer;}
}
