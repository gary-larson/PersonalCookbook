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

import java.util.Locale;

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
     * Method to create an instance of this fragment
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

    public static ContentFragment newInstance(int state, RecipeEntity recipe) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putParcelable(ARG_RECIPE, recipe);
        fragment.setArguments(args);
        return fragment;
    }

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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = ContentFragmentBinding.inflate(inflater, container, false);
        RecipeViewModel mRecipeViewModel = new ViewModelProvider(requireActivity())
                .get(RecipeViewModel.class);
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipeId = savedInstanceState.getInt(RECIPE_ID);
            mRecipe = savedInstanceState.getParcelable(RECIPE);
        }
        if (mState == CookbookActivity.STATE_MANUAL || mState == CookbookActivity.STATE_IMPORT) {
            if (mRecipe == null) {
                mRecipe = new RecipeEntity();
            } else {
                updateUI();
            }
        } else {
            mRecipeViewModel.getRecipe(mRecipeId).observe(getViewLifecycleOwner(), newRecipe -> {
                if (newRecipe != null) {
                    mRecipe = newRecipe;
                    updateUI();
                }
            });
        }
        return mBinding.getRoot();
    }

    private void updateUI() {
        String temp = mRecipe.getName();
        if (!(temp == null || temp.isEmpty())) {
            mBinding.nameTextView.setText(temp);
        }
        temp = mRecipe.getShortDescription();
        if (!(temp == null || temp.isEmpty())) {
            mBinding.shortDescriptionTextView.setText(temp);
        }
        temp = mRecipe.getServingsString();
        mBinding.servingsTextView.setText(temp);
        temp = mRecipe.getPrepTimeString();
        mBinding.prepTimeTextView.setText(temp);
        temp = mRecipe.getCookTimeString();
        mBinding.cookTimeTextView.setText(temp);
        temp = mRecipe.getTotalTimeString();
        mBinding.totalTimeTextView.setText(temp);
        temp = mRecipe.getDescription();
        if (!(temp == null || temp.isEmpty())) {
            mBinding.descriptionTextView.setText(temp);
        }
        temp = mRecipe.getNotes();
        if (!(temp == null || temp.isEmpty())) {
            mBinding.notesTextView.setText(temp);
        }
        temp = mRecipe.getCopyright();
        if (!(temp == null || temp.isEmpty())) {
            mBinding.copyrightTextView.setText(temp);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE, mState);
        outState.putInt(RECIPE_ID, mRecipeId);
        outState.putParcelable(RECIPE, mRecipe);
    }
}