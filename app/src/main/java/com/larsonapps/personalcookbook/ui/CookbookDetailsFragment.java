package com.larsonapps.personalcookbook.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.databinding.CookbookDetailsFragmentBinding;

import java.util.Objects;

/**
 * Class for cookbook details fragment
 */
public class CookbookDetailsFragment extends Fragment {
    // Declare constants
    private static final String ARG_STATE = "state";
    private static final String ARG_RECIPE = "recipe";
    private static final String STATE = "mState";
    private static final String RECIPE = "mRecipe";
    private RecipeEntity mRecipe;
    private int mState;
    private OnCookbookDetailsEditFabListener mListener;

    /**
     * Default constructor
     */
    public CookbookDetailsFragment() {}

    /**
     * Method to create a new instance of this fragment
     * @param state of the app
     * @param recipe of data
     * @return the new fragment
     */
    public static CookbookDetailsFragment newInstance(int state, RecipeEntity recipe) {
        CookbookDetailsFragment fragment = new CookbookDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putParcelable(ARG_RECIPE, recipe);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Method to get arguments of the instance
     * @param savedInstanceState to save
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mState = getArguments().getInt(ARG_STATE);
            mRecipe = getArguments().getParcelable(ARG_RECIPE);
        }
    }

    /**
     * Method to create views for cookbook details fragment
     * @param inflater for the views
     * @param container of the views
     * @param savedInstanceState to maintain state of the fragment
     * @return root view
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // create binding and inflate views
        // Declare variables
        com.larsonapps.personalcookbook.databinding.CookbookDetailsFragmentBinding mBinding =
                CookbookDetailsFragmentBinding.inflate(inflater, container, false);
        // if state saved restore
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipe = savedInstanceState.getParcelable(RECIPE);
        }
        // set title to recipe name
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity()))
                .getSupportActionBar()).setTitle(mRecipe.getName());
        // set fragments for the recipe
        getChildFragmentManager().beginTransaction()
                // set recipe content fragment
                .replace(mBinding.detailsContentContainer.getId(), ContentFragment
                        .newInstance(mState, mRecipe.getId()))
                // set cook note fragment
                .replace(mBinding.detailsCookNoteListContainer.getId(), CookNoteFragment
                        .newInstance(mState, mRecipe.getId()))
                // set ingredient fragment
                .replace(mBinding.detailsIngredientListContainer.getId(), IngredientFragment
                        .newInstance(mState, mRecipe.getId()))
                // set step fragment
                .replace(mBinding.detailsStepListContainer.getId(), StepFragment
                        .newInstance(mState, mRecipe.getId()))
                // set image fragment
                .replace(mBinding.detailsImageListContainer.getId(), ImageFragment
                        .newInstance(mState, mRecipe.getId()))
                // set keyword fragment
                .replace(mBinding.detailsKeywordListContainer.getId(), KeywordFragment
                        .newInstance(mState, mRecipe.getId()))
                .commit();
        // set edit fab listener
        mBinding.editFab.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onEditFabClickListener(mRecipe);
            }
        });
        return mBinding.getRoot();
    }

    /**
     * Method to save state of the fragment
     * @param outState to hold state
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE, mState);
        outState.putParcelable(RECIPE, mRecipe);
    }

    /**
     * Method that initializes the listener
     * @param context to use
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CookbookDetailsFragment.OnCookbookDetailsEditFabListener) {
            mListener = (CookbookDetailsFragment.OnCookbookDetailsEditFabListener) context;
        } else {
            throw new RuntimeException(context.toString() );
        }
    }

    /**
     * Method to remove listener
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Interface of the edit fab listener
     */
    public interface OnCookbookDetailsEditFabListener {
        void onEditFabClickListener(RecipeEntity recipe);
    }
}