package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    private static final String ARG_RECIPE = "recipe";
    private static final String RECIPE = "mRecipe";

    // Declare variables
    private int mState;
    private RecipeEntity mRecipe;
    private ContentFragmentBinding mBinding;
    private RecipeViewModel mRecipeViewModel;

    /**
     * Default constructor
     */
    public ContentFragment() {}

    /**
     * Method to create an instance of this fragment
     * @param state the state of fragment
     * @param recipe to use
     * @return A new instance of fragment CookbookDetailsContentFragment.
     */
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
            mRecipe = getArguments().getParcelable(ARG_RECIPE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = ContentFragmentBinding.inflate(inflater, container, false);
        mRecipeViewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        if (savedInstanceState != null) {
            mRecipe = savedInstanceState.getParcelable(RECIPE);
        }
        if (mState == CookbookActivity.STATE_DISPLAY) {
            mBinding.descriptionImageButton.setVisibility(View.GONE);
        } else if (mState == CookbookActivity.STATE_EDIT) {
            mBinding.descriptionImageButton.setOnClickListener(v -> {
                Toast.makeText(getContext(), "Description Clicked", Toast.LENGTH_LONG).show();
            });
            mBinding.descriptionImageButton.setVisibility(View.VISIBLE);
        }
        if (mRecipe != null) {
            mBinding.nameTextView.setText(mRecipe.getName());
            mBinding.shortDescriptionTextView.setText(mRecipe.getShortDescription());
            mBinding.servingsTextView.setText(mRecipe.getServings());
            mBinding.prepTimeTextView.setText(mRecipe.getPrepTimeString());
            mBinding.cookTimeTextView.setText(mRecipe.getCookTimeString());
            mBinding.totalTimeTextView.setText(mRecipe.getTotalTimeString());
            mBinding.descriptionTextView.setText(mRecipe.getDescription());
            mBinding.notesTextView.setText((mRecipe.getNotes()));
            mBinding.copyrightTextView.setText(mRecipe.getCopyright());
        }
        return mBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECIPE, mRecipe);
    }
}