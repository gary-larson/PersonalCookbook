package com.larsonapps.personalcookbook.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Class to handle a room database cookbook_database
 */
@Database(entities = {IngredientEntity.class, RecipeEntity.class, ImageEntity.class,
        StepEntity.class, KeywordEntity.class, CategoryEntity.class}, version = 4)
public abstract class CookbookRoomDatabase extends RoomDatabase {
    // register DAO
    public abstract CookbookDao cookbookDao();

    // create an instance of the database
    private static volatile CookbookRoomDatabase INSTANCE;
    // set number of threads
    private static final int NUMBER_OF_THREADS = 4;
    // create executor
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * Method to get or create baking_database
     * @param context to use for database
     * @return the database instance
     */
    public static CookbookRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CookbookRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CookbookRoomDatabase.class, "cookbook_database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    /**
     * Callback for database
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };
}
