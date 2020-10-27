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
                // Add one recipe see copyright below
                CookbookDao dao = INSTANCE.cookbookDao();

                // create recipe
                RecipeEntity recipe = new RecipeEntity();
                // set name
                recipe.setName("Michael Symon's Fettuccine Alfredo");
                // set servings
                recipe.setServings(6);
                // set total time
                recipe.setTotalTime(30);
                // set short description
                recipe.setShortDescription("A simple preparation that delivers intense flavor.");
                // set description
                recipe.setDescription("A simple preparation of melted butter and cheese delivers" +
                        " intense flavor.");
                // set copyright
                recipe.setCopyright("© 2013 ABC Television / the chew");
                // set recipe to database
                dao.insertRecipe(recipe);
                // get recipe id
                int recipeId = dao.getRecipeId(recipe.getName());
                // create cook note
                CookNoteEntity cookNote = new CookNoteEntity();
                // set recipe id
                cookNote.setRecipeId(recipeId);
                // set number
                cookNote.setNumber(1);
                // set note
                cookNote.setNote("Aggressively season the pasta water, the water will become part" +
                        " of the sauce.");
                // add cook note
                dao.insertCookNote(cookNote);
                // set number
                cookNote.setNumber(2);
                // set cook note
                cookNote.setNote("Add the pasta water to the pan to stop the shallots from" +
                        " cooking.");
                // add cook note
                dao.insertCookNote(cookNote);
                // create ingredient
                IngredientEntity ingredient = new IngredientEntity();
                // set recipe id
                ingredient.setRecipeId(recipeId);
                // set ingredient name
                ingredient.setName("Dried Fettuccine");
                // set ingredient amount
                ingredient.setAmount("1 1/2");
                // set ingredient measure
                ingredient.setMeasure("pounds");
                // add ingredient
                dao.insertIngredient(ingredient);
                // set ingredient name
                ingredient.setName("Butter (unsalted)");
                // set ingredient amount
                ingredient.setAmount("1");
                // set ingredient measure
                ingredient.setMeasure("stick");
                // add ingredient
                dao.insertIngredient(ingredient);
                // set ingredient name
                ingredient.setName("Shallot");
                // set ingredient amount
                ingredient.setAmount("1");
                //set ingredient measure
                ingredient.setMeasure("large");
                // set ingredient preparation
                ingredient.setPreparation("finely minced");
                // add ingredient
                dao.insertIngredient(ingredient);
                // set ingredient name
                ingredient.setName("Parmigiano-Reggiano");
                // set ingredient amount
                ingredient.setAmount("1");
                // set ingredient measure
                ingredient.setMeasure("cup");
                // set ingredient preparation
                ingredient.setPreparation("freshly grated, plus more for garnish");
                // add ingredient
                dao.insertIngredient(ingredient);
                // set ingredient name
                ingredient.setName("Parsley");
                // set ingredient amount
                ingredient.setAmount("1/3");
                // set ingredient measure
                ingredient.setMeasure("cup");
                // set ingredient preparation
                ingredient.setPreparation("chopped, plus more for garnish");
                // add ingredient
                dao.insertIngredient(ingredient);
                // set ingredient name
                ingredient.setName("Olive Oil");
                // clear ingredient amount
                ingredient.setAmount("");
                // clear ingredient measure
                ingredient.setMeasure("");
                // clear ingredient preparation
                ingredient.setPreparation("");
                // add ingredient
                dao.insertIngredient(ingredient);
                // set ingredient name
                ingredient.setName("Salt and Pepper");
                // add ingredient
                dao.insertIngredient(ingredient);
                // create step
                StepEntity step = new StepEntity();
                // set step recipe id
                step.setRecipeId(recipeId);
                // set stpe number
                step.setNumber(1);
                // set step instruction
                step.setInstruction("Bring a large pot of salted water to a boil and cook pasta " +
                        "one minute less than package instructions.");
                // add step
                dao.insertStep(step);
                // set step number
                step.setNumber(2);
                // set step instruction
                step.setInstruction("Place a large saute pan over medium heat and add 2 to 3 " +
                        "tablespoons of olive oil to the pan. Add the shallots and cook for " +
                        "2 to 3 minutes, or until just tender. Add the butter and swirl the pan" +
                        " to melt, add the parsley.");
                // add step
                dao.insertStep(step);
                // set step number
                step.setNumber(3);
                // set step instruction
                step.setInstruction("Drain the pasta, reserving some water, and add it to the pan." +
                        "Add about 1/4 cup of pasta water to the pasta and add the Parmesan and" +
                        " toss to combine, cooking for about 1 minute more. Taste and adjust" +
                        " seasoning.");
                // add step
                dao.insertStep(step);
                // set step number
                step.setNumber(4);
                // set step instruction
                step.setInstruction("Plate and garnish with more cheese and more parsley. Serve" +
                        " immediately.");
                // add step
                dao.insertStep(step);
                // create image
                ImageEntity image = new ImageEntity();
                // set image recipe id
                image.setRecipeId(recipeId);
                // set image url
                image.setImageUrl("https://www.keyingredient.com/media/df/4a/6ff2fa7ecf1151a53dfa1d7a60de48bdf065.jpg/rh/fettuccine-alfredo-michael-symon.jpg");
                // set image height
                image.setHeight(454);
                // set image width
                image.setWidth(454);
                // set image type
                image.setType("Internet");
                // add image
                dao.insertImage(image);
                // create keyword
                KeywordEntity keyword = new KeywordEntity();
                // set keyword recipe id
                keyword.setRecipeId(recipeId);
                // set keyword
                keyword.setKeyword("Pasta");
                // add keyword
                dao.insertKeyword(keyword);
                // set keyword
                keyword.setKeyword("Dinner");
                // add keyword
                dao.insertKeyword(keyword);
            });
        }
    };
}
