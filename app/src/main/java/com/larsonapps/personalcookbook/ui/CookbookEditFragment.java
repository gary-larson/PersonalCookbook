package com.larsonapps.personalcookbook.ui;

import android.content.Context;
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

import com.google.android.gms.ads.AdRequest;
import com.larsonapps.personalcookbook.data.CookNoteEntity;
import com.larsonapps.personalcookbook.data.ImageEntity;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.data.KeywordEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.databinding.CookbookEditFragmentBinding;
import com.larsonapps.personalcookbook.model.CookNoteViewModel;
import com.larsonapps.personalcookbook.model.ImageViewModel;
import com.larsonapps.personalcookbook.model.IngredientViewModel;
import com.larsonapps.personalcookbook.model.KeywordViewModel;
import com.larsonapps.personalcookbook.model.RecipeViewModel;
import com.larsonapps.personalcookbook.model.StepViewModel;

import java.io.IOException;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

/**
 * class for cookbook edit fragment
 */
public class CookbookEditFragment extends Fragment implements
        EditContentDialogFragment.EditContentDialogListener,
        EditCookNoteDialogFragment.EditCookNoteDialogAddListener,
        EditIngredientDialogFragment.EditIngredientDialogAddListener,
        EditStepDialogFragment.EditStepDialogAddListener,
        AddImageDialogFragment.AddImageDialogListener,
        AddKeywordDialogFragment.EditKeywordDialogListener {
    // Declare constants
    private static final String ARG_STATE = "state";
    private static final String ARG_RECIPE = "recipe";
    private static final String STATE = "mState";
    private static final String RECIPE = "mRecipe";
    private static final String CONTENT_TITLE = "Edit Content";
    private static final String COOK_NOTE_TITLE = "Add Cook's Note";
    private static final String INGREDIENT_TITLE = "Add Ingredient";
    private static final String STEP_TITLE = "Add Step";
    private static final String IMAGE_TITLE = "Add Image";
    private static final String KEYWORD_TITLE = "Add Keyword";
    private static final String GALLERY_TYPE = "Gallery";
    private static final String GALLERY_TITLE = "Select Picture";
    private static final int GALLERY_CODE = 170;
    private static final int CONTENT_CODE = 110;
    private static final int COOK_NOTE_CODE = 115;
    private static final int INGREDIENT_CODE = 120;
    private static final int STEP_CODE = 130;
    private static final int IMAGE_CODE = 140;
    private static final int KEYWORD_CODE = 150;
    private static final String TAG = CookbookEditFragment.class.getSimpleName();
    private RecipeEntity mRecipe;
    private int mState;
    private RecipeViewModel mRecipeViewModel;
    private CookNoteViewModel mCookNoteViewModel;
    private IngredientViewModel mIngredientViewModel;
    private StepViewModel mStepViewModel;
    private ImageViewModel mImageViewModel;
    private KeywordViewModel mKeywordViewModel;
    private CookbookEditFragment.OnCookbookEditDeleteFabListener mListener;

    /**
     * Method to create a new instance of cookbook edit fragment
     * @param state of the app
     * @param recipe to use
     * @return cookbook edit fragment
     */
    public static CookbookEditFragment newInstance(int state, RecipeEntity recipe) {
        CookbookEditFragment fragment = new CookbookEditFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putParcelable(ARG_RECIPE, recipe);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Method to get the arguments of the instance
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
     * Method to create the views of cookbook edit fragment
     * @param inflater for the views
     * @param container of the views
     * @param savedInstanceState of the fragment
     * @return cookbook edit fragment
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Declare variables
        // create binding and inflate views
        com.larsonapps.personalcookbook.databinding.CookbookEditFragmentBinding mBinding =
                CookbookEditFragmentBinding.inflate(inflater, container, false);
        // load view models to deal with data
        mRecipeViewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        mCookNoteViewModel = new ViewModelProvider(requireActivity()).get(CookNoteViewModel.class);
        mIngredientViewModel = new ViewModelProvider(requireActivity())
                .get(IngredientViewModel.class);
        mStepViewModel = new ViewModelProvider(requireActivity()).get(StepViewModel.class);
        mImageViewModel = new ViewModelProvider(requireActivity()).get(ImageViewModel.class);
        mKeywordViewModel = new ViewModelProvider(requireActivity()).get(KeywordViewModel.class);
        // load ad
        AdRequest adRequest = new AdRequest.Builder().build();
        mBinding.editAdView.loadAd(adRequest);
        // if state is saved restore
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipe = savedInstanceState.getParcelable(RECIPE);
        }
        // set title to recipe name
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity()))
                .getSupportActionBar()).setTitle(mRecipe.getName());
        // set fragments for cookbook edit fragment
        getChildFragmentManager().beginTransaction()
                // set content fragment for recipe
                .replace(mBinding.editContentContainer.getId(), ContentFragment
                        .newInstance(mState, mRecipe.getId()))
                // set cook note fragment
                .replace(mBinding.editCookNoteListContainer.getId(), CookNoteFragment
                        .newInstance(mState, mRecipe.getId()))
                // set ingredient fragment
                .replace(mBinding.editIngredientListContainer.getId(), IngredientFragment
                        .newInstance(mState, mRecipe.getId()))
                // set step fragment
                .replace(mBinding.editStepListContainer.getId(), StepFragment
                        .newInstance(mState, mRecipe.getId()))
                // set image fragment
                .replace(mBinding.editImageListContainer.getId(), ImageFragment
                        .newInstance(mState, mRecipe.getId()))
                // set keyword fragment
                .replace(mBinding.editKeywordListContainer.getId(), KeywordFragment
                        .newInstance(mState, mRecipe.getId()))
                .commit();
        // set listener for recipe edit button
        mBinding.editContentButton.setOnClickListener(v -> {
            // open edit content dialog box in edit mode
            EditContentDialogFragment editContentDialogFragment = EditContentDialogFragment
                    .newInstance(CONTENT_TITLE, mState, mRecipe);
            editContentDialogFragment.setTargetFragment(this, CONTENT_CODE);
            if (getActivity() != null) {
                editContentDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.EDIT_CONTENT_DIALOG);
            }
        });
        // set listener for add cook note button
        mBinding.editAddCookNoteButton.setOnClickListener(v -> {
            // open edit cook note dialog box in add mode
            EditCookNoteDialogFragment editCookNoteDialogFragment = EditCookNoteDialogFragment
                    .newInstance(COOK_NOTE_TITLE, CookbookActivity.STATE_ADD, null);
            editCookNoteDialogFragment.setTargetFragment(this, COOK_NOTE_CODE);
            if (getActivity() != null) {
                editCookNoteDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.EDIT_COOK_NOTE_DIALOG);
            }
        });
        // set listener for ingredient add button
        mBinding.editAddIngredientButton.setOnClickListener(v -> {
            // open edit ingredient dialog box in add mode
            EditIngredientDialogFragment editIngredientDialogFragment = EditIngredientDialogFragment
                    .newInstance(INGREDIENT_TITLE, CookbookActivity.STATE_ADD, null);
            editIngredientDialogFragment.setTargetFragment(this, INGREDIENT_CODE);
            if (getActivity() != null) {
                editIngredientDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.EDIT_INGREDIENT_DIALOG);
            }
        });
        // set listener for step add button
        mBinding.editAddStepButton.setOnClickListener(v -> {
            // open edit step dialog box in add mode
            EditStepDialogFragment editStepDialogFragment = EditStepDialogFragment
                    .newInstance(STEP_TITLE, CookbookActivity.STATE_ADD, null);
            editStepDialogFragment.setTargetFragment(this, STEP_CODE);
            if (getActivity() != null) {
                editStepDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.EDIT_STEP_DIALOG);
            }
        });
        // set listener for add image gallery button
        mBinding.editAddImageGalleryButton.setOnClickListener(v -> {
            // open intent to pick image from the gallery
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, GALLERY_TITLE), GALLERY_CODE);
        });
        // set listener for add image internet button
        mBinding.editAddImageInternetButton.setOnClickListener(v -> {
            // open add image dialog box in add mode
            AddImageDialogFragment addImageDialogFragment = AddImageDialogFragment
                    .newInstance(IMAGE_TITLE, CookbookActivity.STATE_ADD, null);
            addImageDialogFragment.setTargetFragment(this, IMAGE_CODE);
            if(getActivity() != null) {
                addImageDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.ADD_IMAGE_DIALOG);
            }
        });
        // set listener for add keyword button
        mBinding.editAddKeywordButton.setOnClickListener(v -> {
            // open add keyword dialog box in add mode
            AddKeywordDialogFragment addKeywordDialogFragment = AddKeywordDialogFragment
                    .newInstance(KEYWORD_TITLE, CookbookActivity.STATE_ADD, null);
            addKeywordDialogFragment.setTargetFragment(this, KEYWORD_CODE);
            if (getActivity() != null) {
                addKeywordDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.ADD_KEYWORD_DIALOG);
            }
        });
        // set listened for delet recipe fab
        mBinding.deleteFab.setOnClickListener(v -> {
            // send listener to activity
            if (null != mListener) {
                mListener.onDeleteFabClickListener(mRecipe);
            }
        });
        return mBinding.getRoot();
    }

    /**
     * Method to handle results of pick picture intent
     * @param requestCode of intent
     * @param resultCode of action
     * @param data to use
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // call super
        super.onActivityResult(requestCode, resultCode, data);
        // test result
        if (resultCode == RESULT_OK) {
            // test code
            if (requestCode == GALLERY_CODE) {
                // test data
                if (data != null) {
                    // get wri
                    Uri uri = data.getData();
                    // create image entity
                    ImageEntity image = new ImageEntity();
                    if (uri != null) {
                        // set image url
                        image.setImageUrl(uri.toString());
                        // set image type
                        image.setType(GALLERY_TYPE);
                        // get height and with
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        if (uri.getPath() != null) {
                            try {
                                if (getContext() != null) {
                                    BitmapFactory.decodeStream(
                                            getContext().getContentResolver().openInputStream(uri),
                                            null,
                                            options);
                                    // set image height
                                    image.setHeight(options.outHeight);
                                    // set image width
                                    image.setWidth(options.outWidth);
                                }
                            } catch (IOException e) {
                                // log error
                                Log.e(TAG, " error" + e.getMessage());
                            }
                        }
                        // set image recipe id
                        image.setRecipeId(mRecipe.getId());
                        // add image to database
                        mImageViewModel.insertImage(image);
                    }
                }
            }
        }
    }

    /**
     * Method to save state of cookbook edit fragment
     * @param outState to hold state
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE, mState);
        outState.putParcelable(RECIPE, mRecipe);
    }

    /**
     * Method to handle edit content dialog box submit button
     * @param recipe to use
     */
    @Override
    public void onFinishEditContentDialog(RecipeEntity recipe) {
        // set recipe to member variable
        mRecipe = recipe;
        // update recipe in database
        mRecipeViewModel.updateRecipe(mRecipe);
    }

    /**
     * Method to handle edit ingredient dialog box submit button
     * @param ingredient to use
     */
    @Override
    public void onFinishEditIngredientDialog(IngredientEntity ingredient) {
        // set ingredient recipe id
        ingredient.setRecipeId(mRecipe.getId());
        // add ingredient to database
        mIngredientViewModel.insertIngredient(ingredient);
    }

    /**
     * Method to handle edit step dialog box submit button
     * @param step to use
     */
    @Override
    public void onFinishEditStepDialog(StepEntity step) {
        // set step recipe id
        step.setRecipeId(mRecipe.getId());
        // add step to database
        mStepViewModel.insertStep(step);
    }

    /**
     * Method to handle keyword dialog box submit button
     * @param keyword to use
     */
    @Override
    public void onFinishEditKeywordDialog(KeywordEntity keyword) {
        // set keyword recipe id
        keyword.setRecipeId(mRecipe.getId());
        // add keyword to database
        mKeywordViewModel.insertKeyword(keyword);
    }

    /**
     * Method to handle add image dialog box submit button
     * @param image to use
     */
    @Override
    public void onFinishAddImageDialog(ImageEntity image) {
        // set image recipe id
        image.setRecipeId(mRecipe.getId());
        // add image to database
        mImageViewModel.insertImage(image);
    }

    /**
     * Method to handle edit cook note dialog box submit button
     * @param cookNote to use
     */
    @Override
    public void onFinishEditCookNoteDialog(CookNoteEntity cookNote) {
        // set cook note recipe id
        cookNote.setRecipeId(mRecipe.getId());
        // add cook note to database
        mCookNoteViewModel.insertCookNote(cookNote);
    }

    /**
     * Method that initializes the listener
     * @param context to use
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CookbookEditFragment.OnCookbookEditDeleteFabListener) {
            mListener = (CookbookEditFragment.OnCookbookEditDeleteFabListener) context;
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
     * Interface for delete recipe fab listener
     */
    public interface OnCookbookEditDeleteFabListener {
        void onDeleteFabClickListener(RecipeEntity recipe);
    }
}