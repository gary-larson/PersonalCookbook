package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.databinding.ContentFragmentBinding;
import com.larsonapps.personalcookbook.model.RecipeViewModel;

/**
 * Class to handle content
 */
public class ContentFragment extends Fragment {

    // Constants
    private static final String ARG_STATE = "state";
    private static final String ARG_RECIPE_ID = "recipeId";
    private static final String ARG_RECIPE = "recipe";
    private static final String STATE = "mState";
    private static final String RECIPE_ID = "mRecipeId";
    private static final String RECIPE = "mRecipe";

    // Declare variables
    private int mState;
    private int mRecipeId;
    private RecipeEntity mRecipe;
    private ContentFragmentBinding mBinding;

    /**
     * Default constructor
     */
    public ContentFragment() {}

    /**
     * Method to create an instance of this fragment with recipe id
     * @param state the state of fragment
     * @param recipeId to use
     * @return A new instance of fragment CookbookDetailsContentFragment.
     */
    public static ContentFragment newInstance(int state, int recipeId) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putInt(ARG_RECIPE_ID, recipeId);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Method to create a new instance of fragment with recipe (no id)
     * @param state state of app
     * @param recipe to use
     * @return fragment
     */
    public static ContentFragment newInstance(int state, RecipeEntity recipe) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putParcelable(ARG_RECIPE, recipe);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Method to get arguments
     * @param savedInstanceState to maintain
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mState = getArguments().getInt(ARG_STATE);
            if (getArguments().containsKey(ARG_RECIPE_ID)) {
                mRecipeId = getArguments().getInt(ARG_RECIPE_ID);
            }
            if (getArguments().containsKey(ARG_RECIPE)) {
                mRecipe = getArguments().getParcelable(ARG_RECIPE);
            }
        }
    }

    /**
     * Method to create views
     * @param inflater views
     * @param container for views
     * @param savedInstanceState to maintain
     * @return root view
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = ContentFragmentBinding.inflate(inflater, container, false);
        RecipeViewModel mRecipeViewModel = new ViewModelProvider(requireActivity())
                .get(RecipeViewModel.class);
        // if state saved restore
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipeId = savedInstanceState.getInt(RECIPE_ID);
            mRecipe = savedInstanceState.getParcelable(RECIPE);
        }
        // test state
        if (mState == CookbookActivity.STATE_MANUAL) {
            if (mRecipe == null) {
                // create new recipe
                mRecipe = new RecipeEntity();
            } else {
                // load user interface
                updateUI();
            }
        } else {
            // set observer for recipe
            mRecipeViewModel.getRecipe(mRecipeId).observe(getViewLifecycleOwner(), newRecipe -> {
                if (newRecipe != null) {
                    // set recipe and update user interface
                    mRecipe = newRecipe;
                    updateUI();
                }
            });
        }
        return mBinding.getRoot();
    }

    /**
     * Method to update user interface
     */
    private void updateUI() {
        // set recipe name
        String temp = mRecipe.getName();
        if (!(temp == null || temp.isEmpty())) {
            mBinding.nameTextView.setText(temp);
        }
        // set short description
        temp = mRecipe.getShortDescription();
        if (!(temp == null || temp.isEmpty())) {
            mBinding.shortDescriptionTextView.setText(temp);
        }
        // set servings
        temp = String.valueOf(mRecipe.getServings());
        mBinding.servingsTextView.setText(temp);
        // set prep time
        temp = mRecipe.getPrepTimeString();
        mBinding.prepTimeTextView.setText(temp);
        // set cook time
        temp = mRecipe.getCookTimeString();
        mBinding.cookTimeTextView.setText(temp);
        // set total time
        temp = mRecipe.getTotalTimeString();
        mBinding.totalTimeTextView.setText(temp);
        // set description
        temp = mRecipe.getDescription();
        if (!(temp == null || temp.isEmpty())) {
            mBinding.descriptionTextView.setText(temp);
        }
        // set copyright
        temp = mRecipe.getCopyright();
        if (!(temp == null || temp.isEmpty())) {
            mBinding.copyrightTextView.setText(temp);
        }
        // set personal note
        temp = mRecipe.getPersonalNote();
        if (!(temp == null || temp.isEmpty())) {
            mBinding.personalNoteTextView.setText(temp);
        }
    }

    /**
     * Method to save fragment state
     * @param outState to save in
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE, mState);
        outState.putInt(RECIPE_ID, mRecipeId);
        outState.putParcelable(RECIPE, mRecipe);
    }
}