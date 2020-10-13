package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.databinding.CookbookEditFragmentBinding;
import com.larsonapps.personalcookbook.model.RecipeViewModel;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CookbookEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CookbookEditFragment extends Fragment implements EditContentDialogFragment.EditContentDialogListener {
    // Declare constants
    private static final String ARG_STATE = "state";
    private static final String ARG_RECIPE = "recipe";
    private static final String STATE = "mState";
    private static final String RECIPE = "mRecipe";
    // Declare variables
    private CookbookEditFragmentBinding mBinding;
    private RecipeEntity mRecipe;
    private int mState;
    private RecipeViewModel mRecipeViewModel;

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
        mRecipeViewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipe = savedInstanceState.getParcelable(RECIPE);
        }
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity()))
                .getSupportActionBar()).setTitle(mRecipe.getName());
        getChildFragmentManager().beginTransaction()
                .replace(mBinding.editContentContainer.getId(), ContentFragment
                        .newInstance(mState, mRecipe.getId()))
                .replace(mBinding.editIngredientListContainer.getId(), IngredientFragment
                        .newInstance(mState, mRecipe.getId()))
                .replace(mBinding.editStepListContainer.getId(), StepFragment
                        .newInstance(mState, mRecipe.getId()))
                .replace(mBinding.editImageListContainer.getId(), ImageFragment
                        .newInstance(mState, mRecipe.getId()))
                .commit();
        mBinding.editContentButton.setOnClickListener(v -> {
            EditContentDialogFragment editContentDialogFragment = EditContentDialogFragment
                    .newInstance("Edit Content", mState, mRecipe);
            editContentDialogFragment.setTargetFragment(this, 200);
            if (getActivity() != null) {
                editContentDialogFragment.show(getActivity().getSupportFragmentManager(),
                        "edit_content_fragment");
            }
        });
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

    @Override
    public void onFinishEditContentDialog(RecipeEntity recipe) {
        mRecipe = recipe;
        mRecipeViewModel.updateRecipe(mRecipe);
    }
}