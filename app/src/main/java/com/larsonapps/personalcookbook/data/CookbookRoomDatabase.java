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
        StepEntity.class, KeywordEntity.class, CategoryEntity.class, CookNoteEntity.class},
        version = 5)
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

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                CookbookDao dao = INSTANCE.cookbookDao();

                RecipeEntity recipe = new RecipeEntity();
                recipe.setName("Michael Symon's Fettuccine Alfredo");
                recipe.setServings(6);
                recipe.setTotalTime(30);
                recipe.setShortDescription("A simple preparation that delivers intense flavor.");
                recipe.setDescription("A simple preparation of melted butter and cheese delivers" +
                        " intense flavor.");
                recipe.setCopyright("© 2013 ABC Television / the chew");
                dao.insertRecipe(recipe);
                int recipeId = dao.getRecipeId(recipe.getName());
                CookNoteEntity cookNote = new CookNoteEntity();
                cookNote.setRecipeId(recipeId);
                cookNote.setNumber(1);
                cookNote.setNote("Aggressively season the pasta water, the water will become part" +
                        " of the sauce.");
                dao.insertCookNote(cookNote);
                cookNote.setNumber(2);
                cookNote.setNote("Add the pasta water to the pan to stop the shallots from" +
                        " cooking.");
                dao.insertCookNote(cookNote);
                IngredientEntity ingredient = new IngredientEntity();
                ingredient.setRecipeId(recipeId);
                ingredient.setName("Dried Fettuccine");
                ingredient.setAmount("1 1/2");
                ingredient.setMeasure("pounds");
                dao.insertIngredient(ingredient);
                ingredient.setName("Butter (unsalted)");
                ingredient.setAmount("1");
                ingredient.setMeasure("stick");
                dao.insertIngredient(ingredient);
                ingredient.setName("Shallot");
                ingredient.setAmount("1");
                ingredient.setMeasure("large");
                ingredient.setPreparation("finely minced");
                dao.insertIngredient(ingredient);
                ingredient.setName("Parmigiano-Reggiano");
                ingredient.setAmount("1");
                ingredient.setMeasure("cup");
                ingredient.setPreparation("freshly grated, plus more for garnish");
                dao.insertIngredient(ingredient);
                ingredient.setName("Parsley");
                ingredient.setAmount("1/3");
                ingredient.setMeasure("cup");
                ingredient.setPreparation("chopped, plus more for garnish");
                dao.insertIngredient(ingredient);
                ingredient.setName("Olive Oil");
                ingredient.setAmount("");
                ingredient.setMeasure("");
                ingredient.setPreparation("");
                dao.insertIngredient(ingredient);
                ingredient.setName("Salt and Pepper");
                dao.insertIngredient(ingredient);
                StepEntity step = new StepEntity();
                step.setRecipeId(recipeId);
                step.setNumber(1);
                step.setInstruction("Bring a large pot of salted water to a boil and cook pasta " +
                        "one minute less than package instructions.");
                dao.insertStep(step);
                step.setNumber(2);
                step.setInstruction("Place a large saute pan over medium heat and add 2 to 3 " +
                        "tablespoons of olive oil to the pan. Add the shallots and cook for " +
                        "2 to 3 minutes, or until just tender. Add the butter and swirl the pan" +
                        " to melt, add the parsley.");
                dao.insertStep(step);
                step.setNumber(3);
                step.setInstruction("Drain the pasta, reserving some water, and add it to the pan." +
                        "Add about 1/4 cup of pasta water to the pasta and add the Parmesan and" +
                        " toss to combine, cooking for about 1 minute more. Taste and adjust" +
                        " seasoning.");
                dao.insertStep(step);
                step.setNumber(4);
                step.setInstruction("Plate and garnish with more cheese and more parsley. Serve" +
                        " immediately.");
                dao.insertStep(step);
                ImageEntity image = new ImageEntity();
                image.setRecipeId(recipeId);
                image.setImageUrl("https://www.keyingredient.com/media/df/4a/6ff2fa7ecf1151a53dfa1d7a60de48bdf065.jpg/rh/fettuccine-alfredo-michael-symon.jpg");
                image.setHeight(454);
                image.setWidth(454);
                image.setType("Internet");
                dao.insertImage(image);
                KeywordEntity keyword = new KeywordEntity();
                keyword.setRecipeId(recipeId);
                keyword.setKeyword("Pasta");
                dao.insertKeyword(keyword);
                keyword.setKeyword("Dinner");
                dao.insertKeyword(keyword);
            });
        }
    };
}
