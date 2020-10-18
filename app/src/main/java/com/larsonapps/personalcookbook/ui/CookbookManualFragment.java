package com.larsonapps.personalcookbook.ui;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.ImageEntity;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.data.KeywordEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.databinding.CookbookManualFragmentBinding;
import com.larsonapps.personalcookbook.model.RecipeViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

/**
 * Class to deal with manual entry of an ingredient
 */
public class CookbookManualFragment extends Fragment implements
        EditContentDialogFragment.EditContentDialogListener,
        EditIngredientDialogFragment.EditIngredientDialogAddListener,
        EditStepDialogFragment.EditStepDialogAddListener,
        AddImageDialogFragment.AddImageDialogListener,
        AddKeywordDialogFragment.EditKeywordDialogListener {
    // Declare constants
    private static final String ARG_STATE = "state";
    private static final String STATE = "mState";
    private static final String RECIPE = "mRecipe";
    private static final String IMAGES = "mImageList";
    private static final String INGREDIENTS = "mIngredientList";
    private static final String STEPS = "mStepList";
    private static final String CONTENT_TITLE = "Add Content";
    private static final String INGREDIENT_TITLE = "Add Ingredient";
    private static final String STEP_TITLE = "Add Step";
    private static final String IMAGE_TITLE = "Add Image";
    private static final String KEYWORD_TITLE = "Add Keyword";
    private static final String TAG = CookbookManualFragment.class.getSimpleName();
    private static final int CONTENT_CODE = 210;
    private static final int INGREDIENT_CODE = 220;
    private static final int STEP_CODE = 230;
    private static final int IMAGE_CODE = 240;
    private static final int KEYWORD_CODE = 250;
    private static final String GALLERY_TYPE = "Gallery";
    private static final String GALLERY_TITLE = "Select Picture";
    private static final int GALLERY_CODE = 270;
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
                    .newInstance(CONTENT_TITLE, CookbookActivity.STATE_ADD, mRecipe);
            editContentDialogFragment.setTargetFragment(this, CONTENT_CODE);
            if (getActivity() != null) {
                editContentDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.EDIT_CONTENT_DIALOG);
            }
        });
        mBinding.manualAddIngredientButton.setOnClickListener(v -> {
            EditIngredientDialogFragment editIngredientDialogFragment = EditIngredientDialogFragment
                    .newInstance(INGREDIENT_TITLE, CookbookActivity.STATE_ADD, null);
            editIngredientDialogFragment.setTargetFragment(this, INGREDIENT_CODE);
            if (getActivity() != null) {
                editIngredientDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.EDIT_INGREDIENT_DIALOG);
            }
        });
        mBinding.manualAddStepButton.setOnClickListener(v -> {
            EditStepDialogFragment editStepDialogFragment = EditStepDialogFragment
                    .newInstance(STEP_TITLE, CookbookActivity.STATE_ADD, null);
            editStepDialogFragment.setTargetFragment(this, STEP_CODE);
            if (getActivity() != null) {
                editStepDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.EDIT_STEP_DIALOG);
            }
        });
        mBinding.manualAddImageGalleryButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, GALLERY_TITLE), GALLERY_CODE);
        });
        mBinding.manualAddImageInternetButton.setOnClickListener(v -> {
            AddImageDialogFragment addImageDialogFragment = AddImageDialogFragment
                    .newInstance(IMAGE_TITLE, CookbookActivity.STATE_ADD, null);
            addImageDialogFragment.setTargetFragment(this, IMAGE_CODE);
            if(getActivity() != null) {
                addImageDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.ADD_IMAGE_DIALOG);
            }
        });
        mBinding.manualAddKeywordButton.setOnClickListener(v -> {
            AddKeywordDialogFragment addKeywordDialogFragment = AddKeywordDialogFragment
                    .newInstance(KEYWORD_TITLE, CookbookActivity.STATE_ADD, null);
            addKeywordDialogFragment.setTargetFragment(this, KEYWORD_CODE);
            if (getActivity() != null) {
                addKeywordDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.ADD_KEYWORD_DIALOG);
            }
        });
        mBinding.manualSubmitButton.setOnClickListener(v -> {
            mRecipeViewModel.insertAll(mRecipe, mIngredientList, mStepList, mImageList,
                    mKeywordList);
            getActivity().getSupportFragmentManager().popBackStack();
        });
        return mBinding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_CODE) {
                if (data != null) {
                    Uri uri = data.getData();
                    ImageEntity image = new ImageEntity();
                    if (uri != null) {
                        image.setImageUrl(uri.toString());
                        image.setType(GALLERY_TYPE);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        if (uri.getPath() != null) {
                            try {
                                if (getContext() != null) {
                                    BitmapFactory.decodeStream(
                                            getContext().getContentResolver().openInputStream(uri),
                                            null,
                                            options);
                                    image.setHeight(options.outHeight);
                                    image.setWidth(options.outWidth);
                                }
                            } catch (IOException e) {
                                Log.e(TAG, " error" + e.getMessage());
                            }
                        }
                        mImageList.add(image);
                        getChildFragmentManager()
                                .beginTransaction()
                                .replace(mBinding.manualImageListContainer.getId(),
                                        ImageFragment.newInstance(mState, mImageList))
                                .commit();
                    }
                }
            }
        }
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
        getChildFragmentManager().beginTransaction()
                .replace(mBinding.manualContentContainer.getId(),
                        ContentFragment.newInstance(mState, mRecipe))
                .commit();
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

    @Override
    public void onFinishAddImageDialog(ImageEntity image) {
        mImageList.add(image);
        getChildFragmentManager()
                .beginTransaction()
                .replace(mBinding.manualImageListContainer.getId(),
                        ImageFragment.newInstance(mState,mImageList))
                .commit();
    }
}