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
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.model.ImageViewModel;
import com.larsonapps.personalcookbook.model.IngredientViewModel;
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
        EditContentDialogFragment.EditContentDialogListener {
    // Declare constants
    private static final String ARG_STATE = "state";
    private static final String STATE = "mState";
    private static final String RECIPE = "mRecipe";
    private static final String IMAGES = "mImageList";
    private static final String INGREDIENTS = "mIngredientList";
    private static final String STEPS = "mStepList";
    // Declare variables
    RecipeViewModel mRecipeViewModel;
    IngredientViewModel mIngredientsViewModel;
    StepViewModel mStepsViewModel;
    CookbookManualFragmentBinding mBinding;
    private int mState;
    private RecipeEntity mRecipe;
    private List<ImageEntity> mImageList = new ArrayList<>();
    private List<IngredientEntity> mIngredientList = new ArrayList<>();
    private List<StepEntity> mStepList = new ArrayList<>();

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
                .commit();
        mBinding.manualAddContentButton.setOnClickListener(v -> {
            EditContentDialogFragment editContentDialogFragment = EditContentDialogFragment
                    .newInstance("Add Manual", mState);
            editContentDialogFragment.setTargetFragment(this, 100);
            if (getActivity() != null) {
                editContentDialogFragment.show(getActivity().getSupportFragmentManager(),
                        "edit_content_fragment");
            }
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
        outState.putParcelable(RECIPE, mRecipe);
        outState.putParcelableArrayList(IMAGES, (ArrayList<ImageEntity>) mImageList);
        outState.putParcelableArrayList(INGREDIENTS, (ArrayList<IngredientEntity>) mIngredientList);
        outState.putParcelableArrayList(STEPS, (ArrayList<StepEntity>) mStepList);
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (getActivity() != null) {
//            ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.manualToolbar);
//            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar())
//                    .setDisplayHomeAsUpEnabled(true);
//        }
    }

    @Override
    public void onFinishEditContentDialog(RecipeEntity recipe) {
        mRecipe = recipe;
    }
}