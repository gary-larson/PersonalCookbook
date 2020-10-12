package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.databinding.CookbookEditFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CookbookEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CookbookEditFragment extends Fragment {
    // Declare constants
    private static final String ARG_STATE = "state";
    private static final String ARG_RECIPE = "recipe";
    private static final String STATE = "mState";
    private static final String RECIPE = "mRecipe";
    // Declare variables
    private CookbookEditFragmentBinding mBinding;
    private RecipeEntity mRecipe;
    private int mState;

    public static CookbookEditFragment newInstance(int state, RecipeEntity recipe) {
        CookbookEditFragment fragment = new CookbookEditFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putParcelable(ARG_RECIPE, recipe);
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
            mRecipe = getArguments().getParcelable(ARG_RECIPE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = CookbookEditFragmentBinding.inflate(inflater, container, false);
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipe = savedInstanceState.getParcelable(RECIPE);
        }
        getChildFragmentManager().beginTransaction()
                .replace(mBinding.editContentContainer.getId(), ContentFragment
                        .newInstance(mState, mRecipe))
                .replace(mBinding.editIngredientListContainer.getId(), IngredientFragment
                        .newInstance(mState, mRecipe.getId()))
                .replace(mBinding.editStepListContainer.getId(), StepFragment
                        .newInstance(mState, mRecipe.getId()))
                .replace(mBinding.editImageListContainer.getId(), ImageFragment
                        .newInstance(mState, mRecipe.getId()))
                .commit();
        mBinding.editReorderIngredientButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Reorder Ingredient clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.editAddIngredientButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add Ingredient clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.editReorderStepButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Reorder Step clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.editAddStepButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add Step clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.editAddImageButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add Image clicked", Toast.LENGTH_LONG).show();
        });
        return mBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE, mState);
        outState.putParcelable(RECIPE, mRecipe);
    }

}