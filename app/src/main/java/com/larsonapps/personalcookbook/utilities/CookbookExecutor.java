package com.larsonapps.personalcookbook.utilities;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;

public class CookbookExecutor implements Executor {
    /**
     * Method to start a thread to execute
     * @param command to start
     */
    @Override
    public void execute(@NonNull Runnable command) {
        new Thread(command).start();
    }
}
