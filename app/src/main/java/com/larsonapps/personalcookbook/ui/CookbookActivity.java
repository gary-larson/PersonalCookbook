/*
This program saves all recipes on a device with search and editing capabilities.
Copyright (C) 2020  Larson Apps - Gary Larson

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.larsonapps.personalcookbook.ui;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.ImageEntity;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.databinding.CookbookActivityBinding;
import com.larsonapps.personalcookbook.model.ImageViewModel;
import com.larsonapps.personalcookbook.model.IngredientViewModel;
import com.larsonapps.personalcookbook.model.KeywordViewModel;
import com.larsonapps.personalcookbook.model.RecipeViewModel;
import com.larsonapps.personalcookbook.model.StepViewModel;

public class CookbookActivity extends AppCompatActivity implements
        RecipeFragment.OnListFragmentInteractionListener,
        StepFragment.OnListFragmentInteractionListener,
        IngredientFragment.OnListFragmentInteractionListener,
        ImageFragment.OnListFragmentInteractionListener,
        FragmentManager.OnBackStackChangedListener,
        AddCategoryDialogFragment.OnAddCategoryDialogListener {
    // Declare constants
    public static final int STATE_DISPLAY = 0;
    public static final int STATE_EDIT= 1;
    public static final int STATE_MANUAL = 2;
    public static final int STATE_IMPORT = 3;
    private static final String DETAILS_FRAGMENT = "DetailsFragment";
    private static final String MANUAL_FRAGMENT = "ManualFragment";
    private static final String IMPORT_FRAGMENT = "ImportFragment";
    private static final String ABOUT_FRAGMENT = "AboutFragment";
    // Declare variables
    private RecipeViewModel mRecipeViewModel;
    private IngredientViewModel mIngredientViewModel;
    private StepViewModel mStepViewModel;
    private ImageViewModel mImageViewModel;
    private KeywordViewModel mKeywordViewModel;
    private CookbookActivityBinding mBinding;
    private Context mContext;
    private int mHeight;

    /**
     * Method to create cookbook activity
     * @param savedInstanceState of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inflate the view
        mBinding = CookbookActivityBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        // check for saved inatance atate
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(mBinding.container.getId(), RecipeFragment.newInstance())
                    .commitNow();
        }
        // get an application context
        mContext = getApplicationContext();
        // Get the view models used by fragments
        mRecipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        mIngredientViewModel = new ViewModelProvider(this).get(IngredientViewModel.class);
        mStepViewModel = new ViewModelProvider(this).get(StepViewModel.class);
        mImageViewModel = new ViewModelProvider(this).get(ImageViewModel.class);
        mKeywordViewModel = new ViewModelProvider(this).get(KeywordViewModel.class);
        // get the height of the screen
        int height;
        if (android.os.Build.VERSION.SDK_INT >= 30){
            height = getWindowManager().getMaximumWindowMetrics().getBounds().height();
        } else{
            Point size = new Point();
            //noinspection deprecation
            getWindowManager().getDefaultDisplay().getRealSize(size);
            height = size.y;
        }
        // set the height of the screen
        mHeight = height;
    }

    /**
     * Inflate the main menu
     * @param menu to inflate
     * @return true to indicate menu inflated
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate main movie menu
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Method to process items clicked on menu
     * @param item to process
     * @return whether item is handled by this method or super
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // get menu item id
        int menuItemThatWasSelected = item.getItemId();
        // take action depending on item selected
        switch (menuItemThatWasSelected) {
            case R.id.action_add_category:
                FragmentManager fm = getSupportFragmentManager();
                AddCategoryDialogFragment addCategoryDialogFragment =
                        AddCategoryDialogFragment.newInstance("Add Category");
                addCategoryDialogFragment.show(fm, "add_category_fragment");
                return true;
            case R.id.action_manual:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(mBinding.container.getId(),
                                CookbookManualFragment.newInstance(STATE_MANUAL))
                        .addToBackStack(MANUAL_FRAGMENT)
                        .commitNow();
                return true;
            case R.id.action_import:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(mBinding.container.getId(), CookbookImportFragment.newInstance(STATE_IMPORT))
                        .addToBackStack(IMPORT_FRAGMENT)
                        .commitNow();
                return true;
            case R.id.action_about:
                // Get fragment manager and open about fragment
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(mBinding.container.getId(), new CookbookAboutFragment())
                        .addToBackStack(ABOUT_FRAGMENT)
                        .commit();
                return true;
            case android.R.id.home: {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                    return true;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Getter fo height
     * @return height
     */
    public int getHeight() {return mHeight;}

    /**
     * Method to handle listener for the recipe recyclerview adapter
     * @param recipe of the list item
     */
    @Override
    public void onListFragmentInteraction(RecipeEntity recipe) {
        // open detail fragment for the recipe in question
        getSupportFragmentManager().beginTransaction()
                .replace(mBinding.container.getId(), CookbookDetailsFragment
                        .newInstance(STATE_DISPLAY, recipe   ))
                .addToBackStack(DETAILS_FRAGMENT)
                .commitNow();
    }

    /**
     * Method to handle the listener for the ingredient recyclerview adapter
     * @param ingredient in question
     * @param state the state of the app
     * @param view that was clicked
     */
    @Override
    public void onListFragmentInteraction(IngredientEntity ingredient, int state, View view) {
        if (view.getId() == R.id.ingredient_edit_image_button) {
            Toast.makeText(this, "Ingredient edit clicked", Toast.LENGTH_LONG).show();
        } else if (view.getId() == R.id.ingredient_delete_image_button) {
            Toast.makeText(this, "Ingredient delete clicked", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Ingredient clicked", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Method to handle the listener for the step recyclerview adapter
     * @param step of the itme clicked
     * @param state of the app
     * @param view that was clicked
     */
    @Override
    public void onListFragmentInteraction(StepEntity step, int state, View view) {
        if (view.getId() == R.id.step_edit_image_button) {
            Toast.makeText(this, "Step edit clicked", Toast.LENGTH_LONG).show();
        } else if (view.getId() == R.id.step_delete_image_button) {
            Toast.makeText(this, "Step delete clicked", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Step clicked", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Method to handle the listener for the image recyclerview adapter
     * @param image of the item clicked
     * @param state of the app
     * @param view that was clicked
     */
    @Override
    public void onListFragmentInteraction(ImageEntity image, int state, View view) {
        if (view.getId() == R.id.image_button) {
            Toast.makeText(this, "Image delete clicked", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Image clicked", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Method to handle the listener for the back button
     */
    @Override
    public void onBackStackChanged() {
        getSupportFragmentManager().popBackStack();
    }

    /**
     * Method to handle the listener for the add category dialog fragment
     * @param category to be added
     */
    @Override
    public void onFinishedAddDialog(String category) {
        if (!(category.equals("Category") || category.equals("Favorite"))) {
            mRecipeViewModel.addCategory(category);
        }
    }
}