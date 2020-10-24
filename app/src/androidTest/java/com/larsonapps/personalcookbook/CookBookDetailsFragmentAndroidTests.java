package com.larsonapps.personalcookbook;

import android.content.Context;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.larsonapps.personalcookbook.data.CookNoteEntity;
import com.larsonapps.personalcookbook.data.CookbookDao;
import com.larsonapps.personalcookbook.data.CookbookRoomDatabase;
import com.larsonapps.personalcookbook.data.ImageEntity;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.data.KeywordEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.ui.CookbookActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class CookBookDetailsFragmentAndroidTests {
    private CookbookRoomDatabase db;
    private static final String RECIPE_NAME_TEXT = "Michael Symon's Fettuccine Alfredo";
    private static final String COOK_NOTE_TEXT = "1) Aggressively season the pasta water, the water" +
            " will become part of the sauce.";
    private static final String INGREDIENT_NAME_TEXT = "Dried Fettuccine";
    private static final String STEP_INSTRUCTION_TEXT = "1) Bring a large pot of salted water to a" +
            " boil and cook pasta one minute less than package instructions.";
    private static final String KEYWORD_TEXT = "Pasta";

    /**
     * Method to set create rule
     */
    @Rule
    public ActivityScenarioRule<CookbookActivity> mActivityTestRule = new
            ActivityScenarioRule<>(CookbookActivity.class);

    /**
     * Method to load database with a recipe in memory
     */
    @Before
    public void loadDatabaseWithRecipe() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, CookbookRoomDatabase.class).build();
        // Declare variables
        CookbookDao cookbookDao = db.cookbookDao();
        RecipeEntity recipe = new RecipeEntity();
        recipe.setName("Michael Symon's Fettuccine Alfredo");
        recipe.setServings(6);
        recipe.setTotalTime(30);
        recipe.setShortDescription("A simple preparation that delivers intense flavor.");
        recipe.setDescription("A simple preparation of melted butter and cheese delivers" +
                " intense flavor.");
        recipe.setCopyright("© 2013 ABC Television / the chew");
        cookbookDao.insertRecipe(recipe);
        int recipeId = cookbookDao.getRecipeId(recipe.getName());
        CookNoteEntity cookNote = new CookNoteEntity();
        cookNote.setRecipeId(recipeId);
        cookNote.setNumber(1);
        cookNote.setNote("Aggressively season the pasta water, the water will become part" +
                " of the sauce.");
        cookbookDao.insertCookNote(cookNote);
        cookNote.setNumber(2);
        cookNote.setNote("Add the pasta water to the pan to stop the shallots from" +
                " cooking.");
        cookbookDao.insertCookNote(cookNote);
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setRecipeId(recipeId);
        ingredient.setName("Dried Fettuccine");
        ingredient.setAmount("1 1/2");
        ingredient.setMeasure("pounds");
        cookbookDao.insertIngredient(ingredient);
        ingredient.setName("Butter (unsalted)");
        ingredient.setAmount("1");
        ingredient.setMeasure("stick");
        cookbookDao.insertIngredient(ingredient);
        ingredient.setName("Shallot");
        ingredient.setAmount("1");
        ingredient.setMeasure("large");
        ingredient.setPreparation("finely minced");
        cookbookDao.insertIngredient(ingredient);
        ingredient.setName("Parmigiano-Reggiano");
        ingredient.setAmount("1");
        ingredient.setMeasure("cup");
        ingredient.setPreparation("freshly grated, plus more for garnish");
        cookbookDao.insertIngredient(ingredient);
        ingredient.setName("Parsley");
        ingredient.setAmount("1/3");
        ingredient.setMeasure("cup");
        ingredient.setPreparation("chopped, plus more for garnish");
        cookbookDao.insertIngredient(ingredient);
        ingredient.setName("Olive Oil");
        ingredient.setAmount("");
        ingredient.setMeasure("");
        ingredient.setPreparation("");
        cookbookDao.insertIngredient(ingredient);
        ingredient.setName("Salt and Pepper");
        cookbookDao.insertIngredient(ingredient);
        StepEntity step = new StepEntity();
        step.setRecipeId(recipeId);
        step.setNumber(1);
        step.setInstruction("Bring a large pot of salted water to a boil and cook pasta " +
                "one minute less than package instructions.");
        cookbookDao.insertStep(step);
        step.setNumber(2);
        step.setInstruction("Place a large saute pan over medium heat and add 2 to 3 " +
                "tablespoons of olive oil to the pan. Add the shallots and cook for " +
                "2 to 3 minutes, or until just tender. Add the butter and swirl the pan" +
                " to melt, add the parsley.");
        cookbookDao.insertStep(step);
        step.setNumber(3);
        step.setInstruction("Drain the pasta, reserving some water, and add it to the pan." +
                "Add about 1/4 cup of pasta water to the pasta and add the Parmesan and" +
                " toss to combine, cooking for about 1 minute more. Taste and adjust" +
                " seasoning.");
        cookbookDao.insertStep(step);
        step.setNumber(4);
        step.setInstruction("Plate and garnish with more cheese and more parsley. Serve" +
                " immediately.");
        cookbookDao.insertStep(step);
        ImageEntity image = new ImageEntity();
        image.setRecipeId(recipeId);
        image.setImageUrl("https://www.keyingredient.com/media/df/4a/6ff2fa7ecf1151a53dfa1d7a60de48bdf065.jpg/rh/fettuccine-alfredo-michael-symon.jpg");
        image.setHeight(454);
        image.setWidth(454);
        image.setType("Internet");
        cookbookDao.insertImage(image);
        KeywordEntity keyword = new KeywordEntity();
        keyword.setRecipeId(recipeId);
        keyword.setKeyword("Pasta");
        cookbookDao.insertKeyword(keyword);
        keyword.setKeyword("Dinner");
        cookbookDao.insertKeyword(keyword);
    }

    /**
     * Method to close database
     */
    @After
    public void closeDb() {
        db.close();
    }

    /**
     * Method to perform test for recipe
     */
    @Test
    public void testRecipeOnScreen() {
        onView(withText(RECIPE_NAME_TEXT)).perform(click());
        onView(allOf(withId(R.id.recipe_name_text_view), withText(RECIPE_NAME_TEXT)));
    }

    /**
     * Method to test recipe fragment title
     */
    @Test
    public void testRecipeFragmentTitle() {
        onView(withText(RECIPE_NAME_TEXT)).perform(click());
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText(RECIPE_NAME_TEXT)));
    }

    /**
     * Method to perform test for cook note
     */
    @Test
    public void testCookNoteOnScreen() {
        onView(withText(RECIPE_NAME_TEXT)).perform(click());
        onView(allOf(withId(R.id.cook_note_text_view), withText(COOK_NOTE_TEXT)));
    }

    /**
     * Method to perform test for ingredient
     */
    @Test
    public void testIngredientOnScreen() {
        onView(withText(RECIPE_NAME_TEXT)).perform(click());
        onView(allOf(withId(R.id.ingredient_text_view), withText(INGREDIENT_NAME_TEXT)));
    }

    /**
     * Method to perform test for step
     */
    @Test
    public void testStepOnScreen() {
        onView(withText(RECIPE_NAME_TEXT)).perform(click());
        onView(allOf(withId(R.id.step_text_view), withText(STEP_INSTRUCTION_TEXT)));
    }

    /**
     * Method to perform test for keyword
     */
    @Test
    public void testKeywordOnScreen() {
        onView(withText(RECIPE_NAME_TEXT)).perform(click());
        onView(allOf(withId(R.id.keyword_text_view), withText(KEYWORD_TEXT)));
    }
}
