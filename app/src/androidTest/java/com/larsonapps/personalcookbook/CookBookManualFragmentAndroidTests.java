package com.larsonapps.personalcookbook;

import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.larsonapps.personalcookbook.ui.CookbookActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class CookBookManualFragmentAndroidTests {
    // Declare variables
    private static final String DISPLAYED_TEXT = "Content";
    private static final String TITLE_TEXT = "Add Recipe";

    /**
     * Method to set create rule
     */
    @Rule
    public ActivityScenarioRule<CookbookActivity> mActivityTestRule = new
            ActivityScenarioRule<>(CookbookActivity.class);

    /**
     * Method to open manual entry fragment
     */
    @Before
    public void openManualEntryFragment() {
        onView(withId(R.id.action_manual)).perform(click());
    }

    /**
     * Method to perform test on displayed text
     */
    @Test
    public void testOpeningCookbookManualFragment() {
        onView(withText(DISPLAYED_TEXT)).check(matches(isDisplayed()));
    }

    /**
     * Method to perform test on manual entry title
     */
    @Test
    public void testCookbookManualTitle() {
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText(TITLE_TEXT)));
    }

    /**
     * Method to test the content edit capability
     */
    @Test
    public void testCookbookEditContentEdit() {
        String temp = "New Short Description";
        onView(withId(R.id.manual_add_content_button)).perform(click());
        onView(withId(R.id.edit_content_short_description_edit_text)).perform(replaceText(temp),
                closeSoftKeyboard());
        onView(withId(R.id.edit_content_submit_button)).perform(click());
        onView(allOf(withId(R.id.short_description_text_view), withText(temp)));
    }

    /**
     * Method to test cook note capabilities
     */
    @Test
    public void testCookbookEditCookNoteAddEditDelete() {
        String temp1 = "New Cook Note";
        onView(withId(R.id.manual_add_cook_note_button)).perform(click());
        onView(withId(R.id.edit_cook_note_edit_text)).perform(replaceText(temp1),
                closeSoftKeyboard());
        onView(withId(R.id.edit_cook_note_submit_button)).perform(click());
        onView(allOf(withId(R.id.cook_note_text_view), withText(temp1)));
    }

    /**
     * Method to test cook note capabilities
     */
    @Test
    public void testCookbookEditIngredientAddEditDelete() {
        String temp1 = "New Ingredient";
        onView(withId(R.id.manual_add_ingredient_button)).perform(click());
        onView(withId(R.id.edit_ingredient_name_edit_text)).perform(replaceText(temp1),
                closeSoftKeyboard());
        onView(withId(R.id.edit_ingredient_submit_button)).perform(click());
        onView(allOf(withId(R.id.ingredient_text_view), withText(temp1)));
    }

    /**
     * Method to test cook note capabilities
     */
    @Test
    public void testCookbookEditStepAddEditDelete() {
        String temp1 = "New Step";
        String temp2 = "0) New Step";
        onView(withId(R.id.manual_add_step_button)).perform(click());
        onView(withId(R.id.edit_step_instruction_edit_text)).perform(replaceText(temp1),
                closeSoftKeyboard());
        onView(withId(R.id.edit_step_submit_button)).perform(click());
        onView(allOf(withId(R.id.step_text_view), withText(temp2)));
    }
}
