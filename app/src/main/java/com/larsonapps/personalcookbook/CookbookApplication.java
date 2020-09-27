package com.larsonapps.personalcookbook;

import android.app.Application;
import android.content.Context;

public class CookbookApplication extends Application {
    private AppContainer appContainer;

    public void onCreate() {
        super.onCreate();
        appContainer = new AppContainer(this);
    }

    public AppContainer getAppContainer() {return appContainer;}
}
