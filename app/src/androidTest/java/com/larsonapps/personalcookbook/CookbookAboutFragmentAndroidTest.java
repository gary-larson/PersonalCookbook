package com.larsonapps.personalcookbook;

import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.larsonapps.personalcookbook.ui.CookbookActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Class to test the cookbook about fragment
 */
@RunWith(AndroidJUnit4.class)
public class CookbookAboutFragmentAndroidTest {
    // Declare variables
    private static final String DISPLAYED_TEXT = "Welcome to Personal Cookbook";
    private static final String TITLE_TEXT = "About and Help";
    private static final String MENU_TEXT = "About/Help";

    /**
     * Method to set create rule
     */
    @Rule
    public ActivityScenarioRule<CookbookActivity> mActivityTestRule = new
            ActivityScenarioRule<>(CookbookActivity.class);

    /**
     * Method to open about fragment
     */
    @Before
    public void openAboutFragment() {
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext());
        onView(withText(MENU_TEXT)).perform(click());
    }

    /**
     * Method to perform test on displayed text
     */
    @Test
    public void testOpeningCookbookAboutFragment() {
        onView(withText(DISPLAYED_TEXT)).check(matches(isDisplayed()));
    }

    /**
     * Method to perform test on about title
     */
    @Test
    public void testCookbookAboutTitle() {
        onView(allOf(isAssignableFrom(TextView.class), withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText(TITLE_TEXT)));
    }
}
