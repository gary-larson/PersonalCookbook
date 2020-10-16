package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.ImageEntity;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.data.KeywordEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.databinding.ImageFragmentItemListBinding;
import com.larsonapps.personalcookbook.model.ImageViewModel;
import com.larsonapps.personalcookbook.model.IngredientViewModel;
import com.larsonapps.personalcookbook.model.KeywordViewModel;
import com.larsonapps.personalcookbook.model.RecipeViewModel;
import com.larsonapps.personalcookbook.model.StepViewModel;
import com.larsonapps.personalcookbook.databinding.CookbookManualFragmentBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class to deal with manual entry of an ingredient
 */
public class CookbookManualFragment extends Fragment implements
        EditContentDialogFragment.EditContentDialogListener,
        EditIngredientDialogFragment.EditIngredientDialogListener,
        EditStepDialogFragment.EditStepDialogListener,
        EditKeywordDialogFragment.EditKeywordDialogListener {
    // Declare constants
    private static final String ARG_STATE = "state";
    private static final String STATE = "mState";
    private static final String RECIPE = "mRecipe";
    private static final String IMAGES = "mImageList";
    private static final String INGREDIENTS = "mIngredientList";
    private static final String STEPS = "mStepList";
    private static final String EDIT_CONTENT_TAG = "edit_content_fragment";
    private static final String EDIT_INGREDIENT_TAG = "edit_ingredient_fragment";
    private static final String EDIT_STEP_TAG = "edit_step_fragment";
    private static final String EDIT_IMAGE_TAG = "edit_image_fragment";
    private static final String EDIT_KEYWORD_TAG = "edit_keyword_fragment";
    // Declare variables
    private RecipeViewModel mRecipeViewModel;
    CookbookManualFragmentBinding mBinding;
    private int mState;
    private RecipeEntity mRecipe;
    private List<ImageEntity> mImageList = new ArrayList<>();
    private List<IngredientEntity> mIngredientList = new ArrayList<>();
    private List<StepEntity> mStepList = new ArrayList<>();
    private List<KeywordEntity> mKeywordList = new ArrayList<>();

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
        mRecipeViewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity()))
                .getSupportActionBar()).setTitle(getString(R.string.manual_entry_menu_title));
        int recipeId = 0;
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipe = savedInstanceState.getParcelable(RECIPE);
            mImageList = savedInstanceState.getParcelableArrayList(IMAGES);
            mIngredientList = savedInstanceState.getParcelableArrayList(INGREDIENTS);
            mStepList = savedInstanceState.getParcelableArrayList(STEPS);
        }
        getChildFragmentManager().beginTransaction()
                .replace(mBinding.manualContentContainer.getId(), ContentFragment
                        .newInstance(mState, mRecipe))
                .replace(mBinding.manualIngredientListContainer.getId(), IngredientFragment
                        .newInstance(mState, mIngredientList))
                .replace(mBinding.manualStepListContainer.getId(), StepFragment
                        .newInstance(mState, mStepList))
                .replace(mBinding.manualImageListContainer.getId(), ImageFragment
                        .newInstance(mState, mImageList))
                .replace(mBinding.manualKeywordListContainer.getId(), KeywordFragment
                        .newInstance(mState, mKeywordList))
                .commit();
        mBinding.manualAddContentButton.setOnClickListener(v -> {
            EditContentDialogFragment editContentDialogFragment = EditContentDialogFragment
                    .newInstance("Add Manual", mState, mRecipe);
            editContentDialogFragment.setTargetFragment(this, 200);
            if (getActivity() != null) {
                editContentDialogFragment.show(getActivity().getSupportFragmentManager(),
                        EDIT_CONTENT_TAG);
            }
        });
        mBinding.manualAddIngredientButton.setOnClickListener(v -> {
            EditIngredientDialogFragment editIngredientDialogFragment = EditIngredientDialogFragment
                    .newInstance("Add Manual", mState, null);
            editIngredientDialogFragment.setTargetFragment(this, 210);
            if (getActivity() != null) {
                editIngredientDialogFragment.show(getActivity().getSupportFragmentManager(),
                        EDIT_INGREDIENT_TAG);
            }
        });
        mBinding.manualAddStepButton.setOnClickListener(v -> {
            EditStepDialogFragment editStepDialogFragment = EditStepDialogFragment
                    .newInstance("Add Manual", mState, null);
            editStepDialogFragment.setTargetFragment(this, 220);
            if (getActivity() != null) {
                editStepDialogFragment.show(getActivity().getSupportFragmentManager(),
                        EDIT_STEP_TAG);
            }
        });
        mBinding.manualAddImageButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add Image clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.manualAddKeywordButton.setOnClickListener(v -> {
            EditKeywordDialogFragment editKeywordDialogFragment = EditKeywordDialogFragment
                    .newInstance("Add manual", mState, null);
            editKeywordDialogFragment.setTargetFragment(this, 240);
            if (getActivity() != null) {
                editKeywordDialogFragment.show(getActivity().getSupportFragmentManager(),
                        EDIT_KEYWORD_TAG);
            }
        });
        mBinding.manualSubmitButton.setOnClickListener(v -> {
            mRecipeViewModel.insertAll(mRecipe, mIngredientList, mStepList, mImageList,
                    mKeywordList);
        });
        return mBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE, mState);
        outState.putParcelable(RECIPE, mRecipe);
        outState.putParcelableArrayList(IMAGES, (ArrayList<ImageEntity>) mImageList);
        outState.putParcelableArrayList(INGREDIENTS, (ArrayList<IngredientEntity>) mIngredientList);
        outState.putParcelableArrayList(STEPS, (ArrayList<StepEntity>) mStepList);
    }

    @Override
    public void onFinishEditContentDialog(RecipeEntity recipe) {
        mRecipe = recipe;
    }

    @Override
    public void onFinishEditIngredientDialog(IngredientEntity ingredient) {
        mIngredientList.add(ingredient);
        getChildFragmentManager().beginTransaction()
                .replace(mBinding.manualIngredientListContainer.getId(),
                        IngredientFragment.newInstance(mState, mIngredientList))
                .commit();
    }

    @Override
    public void onFinishEditStepDialog(StepEntity step) {
        mStepList.add(step);
        getChildFragmentManager()
                .beginTransaction()
                .replace(mBinding.manualStepListContainer.getId(),
                        StepFragment.newInstance(mState, mStepList))
                .commit();
    }

    @Override
    public void onFinishEditKeywordDialog(KeywordEntity keyword) {
        mKeywordList.add(keyword);
        getChildFragmentManager()
                .beginTransaction()
                .replace(mBinding.manualKeywordListContainer.getId(),
                        KeywordFragment.newInstance(mState, mKeywordList))
                .commit();
    }
}