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

import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.data.KeywordEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.databinding.CookbookEditFragmentBinding;
import com.larsonapps.personalcookbook.model.ImageViewModel;
import com.larsonapps.personalcookbook.model.IngredientViewModel;
import com.larsonapps.personalcookbook.model.KeywordViewModel;
import com.larsonapps.personalcookbook.model.RecipeViewModel;
import com.larsonapps.personalcookbook.model.StepViewModel;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CookbookEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CookbookEditFragment extends Fragment implements
        EditContentDialogFragment.EditContentDialogListener,
        EditIngredientDialogFragment.EditIngredientDialogListener,
        EditStepDialogFragment.EditStepDialogListener,
        EditKeywordDialogFragment.EditKeywordDialogListener {
    // Declare constants
    private static final String ARG_STATE = "state";
    private static final String ARG_RECIPE = "recipe";
    private static final String STATE = "mState";
    private static final String RECIPE = "mRecipe";
    private static final String EDIT_CONTENT_TAG = "edit_content_fragment";
    private static final String EDIT_INGREDIENT_TAG = "edit_ingredient_fragment";
    private static final String EDIT_STEP_TAG = "edit_step_fragment";
    private static final String EDIT_IMAGE_TAG = "edit_image_fragment";
    private static final String EDIT_KEYWORD_TAG = "edit_keyword_fragment";
    // Declare variables
    private CookbookEditFragmentBinding mBinding;
    private RecipeEntity mRecipe;
    private int mState;
    private RecipeViewModel mRecipeViewModel;
    private IngredientViewModel mIngredientViewModel;
    private StepViewModel mStepViewModel;
    private ImageViewModel mImageViewModel;
    private KeywordViewModel mKeywordViewModel;

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
        mIngredientViewModel = new ViewModelProvider(requireActivity())
                .get(IngredientViewModel.class);
        mStepViewModel = new ViewModelProvider(requireActivity()).get(StepViewModel.class);
        mImageViewModel = new ViewModelProvider(requireActivity()).get(ImageViewModel.class);
        mKeywordViewModel = new ViewModelProvider(requireActivity()).get(KeywordViewModel.class);
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
            editContentDialogFragment.setTargetFragment(this, 100);
            if (getActivity() != null) {
                editContentDialogFragment.show(getActivity().getSupportFragmentManager(),
                        EDIT_CONTENT_TAG);
            }
        });
        mBinding.editReorderIngredientButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Reorder Ingredient clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.editAddIngredientButton.setOnClickListener(v -> {
            EditIngredientDialogFragment editIngredientDialogFragment = EditIngredientDialogFragment
                    .newInstance("Add Ingredient", mState, null);
            editIngredientDialogFragment.setTargetFragment(this, 110);
            if (getActivity() != null) {
                editIngredientDialogFragment.show(getActivity().getSupportFragmentManager(),
                        EDIT_INGREDIENT_TAG);
            }
        });
        mBinding.editReorderStepButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Reorder Step clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.editAddStepButton.setOnClickListener(v -> {
            EditStepDialogFragment editStepDialogFragment = EditStepDialogFragment
                    .newInstance("Add Step", mState, null);
            editStepDialogFragment.setTargetFragment(this, 120);
            if (getActivity() != null) {
                editStepDialogFragment.show(getActivity().getSupportFragmentManager(),
                        EDIT_STEP_TAG);
            }
        });
        mBinding.editAddImageButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add Image clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.editAddKeywordButton.setOnClickListener(v -> {
            EditKeywordDialogFragment editKeywordDialogFragment = EditKeywordDialogFragment
                    .newInstance("Add Keyword", mState, null);
            editKeywordDialogFragment.setTargetFragment(this, 140);
            if (getActivity() != null) {
                editKeywordDialogFragment.show(getActivity().getSupportFragmentManager(),
                        EDIT_KEYWORD_TAG);
            }
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

    @Override
    public void onFinishEditIngredientDialog(IngredientEntity ingredient) {
        ingredient.setRecipeId(mRecipe.getId());
        mIngredientViewModel.insertIngredient(ingredient);
    }

    @Override
    public void onFinishEditStepDialog(StepEntity step) {
        step.setRecipeId(mRecipe.getId());
        mStepViewModel.insertStep(step);
    }

    @Override
    public void onFinishEditKeywordDialog(KeywordEntity keyword) {
        keyword.setRecipeId(mRecipe.getId());
        mKeywordViewModel.insertKeyword(keyword);
    }
}