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

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.ads.MobileAds;
import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.CategoryEntity;
import com.larsonapps.personalcookbook.data.CookNoteEntity;
import com.larsonapps.personalcookbook.data.ImageEntity;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.data.KeywordEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.databinding.CookbookActivityBinding;
import com.larsonapps.personalcookbook.model.CookNoteViewModel;
import com.larsonapps.personalcookbook.model.ImageViewModel;
import com.larsonapps.personalcookbook.model.IngredientViewModel;
import com.larsonapps.personalcookbook.model.KeywordViewModel;
import com.larsonapps.personalcookbook.model.RecipeViewModel;
import com.larsonapps.personalcookbook.model.StepViewModel;

/**
 * Class for cookbook activity
 */
public class CookbookActivity extends AppCompatActivity implements
        FragmentManager.OnBackStackChangedListener,
        RecipeFragment.OnListFragmentInteractionListener,
        StepFragment.OnListFragmentInteractionListener,
        IngredientFragment.OnListFragmentInteractionListener,
        ImageFragment.OnListFragmentInteractionListener,
        KeywordFragment.OnListFragmentInteractionListener,
        CookNoteFragment.OnListFragmentInteractionListener,
        CategoryFragment.OnListFragmentInteractionListener,
        AddCategoryDialogFragment.OnAddCategoryDialogListener,
        EditCookNoteDialogFragment.EditCookNoteDialogEditListener,
        EditIngredientDialogFragment.EditIngredientDialogEditListener,
        EditStepDialogFragment.EditStepDialogEditListener,
        CookbookDetailsFragment.OnCookbookDetailsEditFabListener,
        CookbookEditFragment.OnCookbookEditDeleteFabListener {
    // Declare constants
    public static final int STATE_DISPLAY = 0;
    public static final int STATE_EDIT= 1;
    public static final int STATE_MANUAL = 2;
    public static final int STATE_ADD = 3;
    private static final String CATEGORY_FRAGMENT = "CategoryFragment";
    private static final String DETAILS_FRAGMENT = "DetailsFragment";
    private static final String EDIT_FRAGMENT = "EditFragment";
    private static final String MANUAL_FRAGMENT = "ManualFragment";
    private static final String ABOUT_FRAGMENT = "AboutFragment";
    public static final String EDIT_CONTENT_DIALOG = "EditContentDialogFragment";
    public static final String EDIT_COOK_NOTE_DIALOG = "EditCookNoteDialogFragment";
    public static final String EDIT_INGREDIENT_DIALOG = "EditIngredientDialogFragment";
    public static final String EDIT_STEP_DIALOG = "EditStepDialogFragment";
    public static final String ADD_IMAGE_DIALOG = "AddImageDialogFragment";
    public static final String ADD_KEYWORD_DIALOG = "AddKeywordDialogFragment";
    public static final String ADD_CATEGORY_DIALOG = "AddCategoryDialogFragment";
    private static final String CATEGORY_TITLE = "Add Category";
    private static final String COOK_NOTE_TITLE = "Add Cook's Note";
    private static final String INGREDIENT_TITLE = "Edit Ingredient";
    private static final String STEP_TITLE = "Edit Step";
    // Declare variables
    private RecipeViewModel mRecipeViewModel;
    private CookNoteViewModel mCookNoteViewModel;
    private IngredientViewModel mIngredientViewModel;
    private StepViewModel mStepViewModel;
    private ImageViewModel mImageViewModel;
    private KeywordViewModel mKeywordViewModel;
    private CookbookActivityBinding mBinding;

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
        setSupportActionBar(mBinding.toolbar);
        // check for saved inatance state
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(mBinding.container.getId(), RecipeFragment.newInstance())
                    .commit();
        }
        // Get the view models used by fragments
        mRecipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        mCookNoteViewModel = new ViewModelProvider(this).get(CookNoteViewModel.class);
        mIngredientViewModel = new ViewModelProvider(this).get(IngredientViewModel.class);
        mStepViewModel = new ViewModelProvider(this).get(StepViewModel.class);
        mImageViewModel = new ViewModelProvider(this).get(ImageViewModel.class);
        mKeywordViewModel = new ViewModelProvider(this).get(KeywordViewModel.class);
        // initialize ads
        MobileAds.initialize(this, initializationStatus -> {
        });
        // set on back stack listener
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        // display up button
        displayUpButton();
    }

    /**
     * Method to display up button if back stack is greater than 0
     */
    private void displayUpButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(
                    getSupportFragmentManager().getBackStackEntryCount() > 0);
        }
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
                // open add category dialog box
                AddCategoryDialogFragment addCategoryDialogFragment =
                        AddCategoryDialogFragment.newInstance(CATEGORY_TITLE);
                addCategoryDialogFragment.show(getSupportFragmentManager(), ADD_CATEGORY_DIALOG);
                return true;
            case R.id.action_manual:
                // open manual entry fragment
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(mBinding.container.getId(),
                                CookbookManualFragment.newInstance(STATE_MANUAL))
                        .addToBackStack(MANUAL_FRAGMENT)
                        .commit();
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
                // deal with up button
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                    return true;
                }
            }
            case R.id.action_delete_category:
                // Get fragment manager and open category fragment
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(mBinding.container.getId(), new CategoryFragment())
                        .addToBackStack(CATEGORY_FRAGMENT)
                        .commit();
                return true;
        }
        // if not handled send to super
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to handle listener for the recipe recyclerview adapter
     * @param recipe of the list item
     */
    @Override
    public void onListFragmentInteraction(RecipeEntity recipe) {
        // open detail fragment for the recipe in question
        getSupportFragmentManager().beginTransaction()
                .replace(mBinding.container.getId(), CookbookDetailsFragment
                        .newInstance(STATE_DISPLAY, recipe))
                .addToBackStack(DETAILS_FRAGMENT)
                .commit();
    }

    /**
     * Method to handle the listener for the ingredient recyclerview adapter
     * @param ingredient in question
     * @param state the state of the app
     * @param view that was clicked
     */
    @Override
    public void onListFragmentInteraction(IngredientEntity ingredient, int state, View view) {
        // test which view
        if (view.getId() == R.id.ingredient_edit_image_button) {
            // open edit ingredient dialog fragment
            EditIngredientDialogFragment editIngredientDialogFragment =
                    EditIngredientDialogFragment.newInstance(INGREDIENT_TITLE, STATE_EDIT,
                            ingredient);
            editIngredientDialogFragment.show(getSupportFragmentManager(), EDIT_INGREDIENT_DIALOG);
        } else if (view.getId() == R.id.ingredient_delete_image_button) {
            // open alert dialog to confirm or cancel delete
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.alert_ingredient_message)
                    .setTitle(R.string.alert_ingredient_title);
            builder.setPositiveButton(R.string.alert_delete, (dialog, id) ->
                    // delete ingredient
                    mIngredientViewModel.deleteIngredient(ingredient));
            builder.setNegativeButton(R.string.alert_cancel, (dialog, id) -> {
                // User cancelled the dialog do nothing
            });
            AlertDialog dialog = builder.create();
            dialog.show();
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
        // test for which view
        if (view.getId() == R.id.step_edit_image_button) {
            // open edit step dialog
            EditStepDialogFragment editStepDialogFragment = EditStepDialogFragment
                    .newInstance(STEP_TITLE, STATE_EDIT, step);
            editStepDialogFragment.show(getSupportFragmentManager(), EDIT_STEP_DIALOG);
        } else if (view.getId() == R.id.step_delete_image_button) {
            // open alert to confirm or cancel delete step
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.alert_step_message)
                    .setTitle(R.string.alert_step_title);
            builder.setPositiveButton(R.string.alert_delete, (dialog, id) ->
                    // delete step
                    mStepViewModel.deleteStep(step));
            builder.setNegativeButton(R.string.alert_cancel, (dialog, id) -> {
                // User cancelled the dialog do nothing
            });
            AlertDialog dialog = builder.create();
            dialog.show();
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
        //test which view
        if (view.getId() == R.id.image_button) {
            // open alert to confirm or cancel delete image
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.alert_image_message)
                    .setTitle(R.string.alert_image_title);
            builder.setPositiveButton(R.string.alert_delete, (dialog, id) ->
                    // delete image
                    mImageViewModel.deleteImage(image));
            builder.setNegativeButton(R.string.alert_cancel, (dialog, id) -> {
                // User cancelled the dialog do nothing
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    /**
     * Method to handle keyword clicks
     * @param keyword to use
     * @param state of app
     * @param view clicked
     */
    @Override
    public void onListFragmentInteraction(KeywordEntity keyword, int state, View view) {
        // test which view was clicked
        if (view.getId() == R.id.keyword_delete_image_button) {
            // open alert to confirm or cancel keyword delete
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.alert_keyword_message)
                    .setTitle(R.string.alert_keyword_title);
            builder.setPositiveButton(R.string.alert_delete, (dialog, id) ->
                    // delete keyword
                    mKeywordViewModel.deleteKeyword(keyword));
            builder.setNegativeButton(R.string.alert_cancel, (dialog, id) -> {
                // User cancelled the dialog do nothing
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    /**
     * Method to deal with cook note clicks
     * @param cookNote to use
     * @param state of app
     * @param view clicked
     */
    @Override
    public void onListFragmentInteraction(CookNoteEntity cookNote, int state, View view) {
        // test which view was clicked
        if (view.getId() == R.id.cook_note_edit_image_button) {
            // open cook note dialog box
            EditCookNoteDialogFragment editCookNoteDialogFragment = EditCookNoteDialogFragment
                    .newInstance(COOK_NOTE_TITLE, STATE_EDIT, cookNote);
            editCookNoteDialogFragment.show(getSupportFragmentManager(), EDIT_COOK_NOTE_DIALOG);
        } else if (view.getId() == R.id.cook_note_delete_image_button) {
            // open alert dialog to confirm or cancel cook note delete
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.alert_cook_note_message)
                    .setTitle(R.string.alert_cook_note_title);
            builder.setPositiveButton(R.string.alert_delete, (dialog, id) ->
                    // delete cook note
                    mCookNoteViewModel.deleteCookNote(cookNote));
            builder.setNegativeButton(R.string.alert_cancel, (dialog, id) -> {
                // User cancelled the dialog do nothing
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    /**
     * Method to deal with category clicks
     * @param category to use
     * @param view clicked
     */
    @Override
    public void onListFragmentInteraction(CategoryEntity category, View view) {
        // open alert dialog to confirm or cancel category delete
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.alert_category_message)
                .setTitle(R.string.alert_category_title);
        builder.setPositiveButton(R.string.alert_delete, (dialog, id) ->
                // delete category
                mRecipeViewModel.deleteCategory(category));
        builder.setNegativeButton(R.string.alert_cancel, (dialog, id) -> {
            // User cancelled the dialog do nothing
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Method to process up button
     * @return true
     */
    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        getSupportFragmentManager().popBackStack();
        return true;
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

    /**
     * Method to deal with up button changes
     */
    @Override
    public void onBackStackChanged() {
        displayUpButton();
    }

    /**
     * Method to deal with edit fab clicked on cookbook details fragment
     * @param recipe to use
     */
    @Override
    public void onEditFabClickListener(RecipeEntity recipe) {
        // open cookbook edit fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(mBinding.container.getId(),
                        CookbookEditFragment.newInstance(STATE_EDIT, recipe))
                .addToBackStack(EDIT_FRAGMENT)
                .commit();
    }

    /**
     * Method to deal with edit ingredient dialog box submit button
     * @param ingredient to use
     */
    @Override
    public void onFinishEditIngredientDialog(IngredientEntity ingredient) {
        // update ingredient in database
        mIngredientViewModel.updateIngredient(ingredient);
    }

    /**
     * Method to deal with edit step dialog box submit button
     * @param step to use
     */
    @Override
    public void onFinishEditStepDialog(StepEntity step) {
        // update step in database
        mStepViewModel.updateStep(step);
    }

    /**
     * Method to deal with edit cook note dialog box submit button
     * @param cookNote to use
     */
    @Override
    public void onFinishEditCookNoteDialog(CookNoteEntity cookNote) {
        // update cook note in database
        mCookNoteViewModel.updateCookNote(cookNote);
    }

    /**
     * Method to deal with cookbook edit fragment delete fab
     * @param recipe to delete
     */
    @Override
    public void onDeleteFabClickListener(RecipeEntity recipe) {
        // open alert dialog box to confirm or cancel delete recipe
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.alert_delete_recipe_message)
                .setTitle(R.string.alert_delete_recipe_title);
        builder.setPositiveButton(R.string.alert_delete, (dialog, id) -> {
            // delete recipe
            mRecipeViewModel.deleteRecipe(recipe);
            // pop cookbook edit and details fragments
            getSupportFragmentManager().popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        });
        builder.setNegativeButton(R.string.alert_cancel, (dialog, id) -> {
            // User cancelled the dialog do nothing
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}