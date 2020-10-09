package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.larsonapps.personalcookbook.model.IngredientViewModel;
import com.larsonapps.personalcookbook.model.StepViewModel;
import com.larsonapps.personalcookbook.databinding.CookbookManualFragmentBinding;

import java.util.Objects;

/**
 * Class to deal with manual entry of an ingredient
 */
public class CookbookManualFragment extends Fragment {
    // Declare constants
    private static final String ARG_STATE = "state";
    private static final String STATE = "mState";
    private static final String RECIPE = "mRecipe";
    // Declare variables
    IngredientViewModel mIngredientsViewModel;
    StepViewModel mStepsViewModel;
    CookbookManualFragmentBinding mBinding;
    private int mState;
    private int mRecipeId = -1;

    /**
     * Default constructor
     */
    public CookbookManualFragment() {}

    public static CookbookManualFragment newInstance(int state) {
        CookbookManualFragment fragment = new CookbookManualFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Method to save the state of the instance
     * @param savedInstanceState to save
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mState = getArguments().getInt(ARG_STATE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = CookbookManualFragmentBinding.inflate(inflater, container, false);
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipeId = savedInstanceState.getInt(RECIPE);
        }
        getChildFragmentManager().beginTransaction()
                .replace(mBinding.manualIngredientListContainer.getId(), IngredientFragment
                        .newInstance(CookbookActivity.STATE_MANUAL, mRecipeId))
                .replace(mBinding.manualStepListContainer.getId(), StepFragment
                        .newInstance(CookbookActivity.STATE_MANUAL, mRecipeId))
                .replace(mBinding.manualImageListContainer.getId(), ImageFragment
                        .newInstance(CookbookActivity.STATE_MANUAL, mRecipeId))
                .commit();
        mBinding.manualRecipeNameEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || event != null) {
                String text = v.getText().toString();
                Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
                return true;
            }
            return false;
        });
        mBinding.manualDescriptionEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || event != null) {
                String text = v.getText().toString();
                Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
                return true;
            }
            return false;
        });
        mBinding.manualAddIngredientButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add Ingredient clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.manualAddStepButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add Step clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.manualAddImageButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add Image clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.manualSubmitButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Submit clicked", Toast.LENGTH_LONG).show();
        });
        return mBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE, mState);
        outState.putInt(RECIPE, mRecipeId);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.manualToolbar);
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar())
                    .setDisplayHomeAsUpEnabled(true);
        }
    }
}