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
import com.larsonapps.personalcookbook.data.CookNoteEntity;
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
        EditCookNoteDialogFragment.EditCookNoteDialogAddListener,
        EditIngredientDialogFragment.EditIngredientDialogAddListener,
        EditStepDialogFragment.EditStepDialogAddListener,
        AddImageDialogFragment.AddImageDialogListener,
        AddKeywordDialogFragment.EditKeywordDialogListener {
    // Declare constants
    private static final String ARG_STATE = "state";
    private static final String STATE = "mState";
    private static final String RECIPE = "mRecipe";
    private static final String COOK_NOTES = "mCookNotes";
    private static final String IMAGES = "mImageList";
    private static final String INGREDIENTS = "mIngredientList";
    private static final String STEPS = "mStepList";
    private static final String CONTENT_TITLE = "Add Content";
    private static final String COOK_NOTE_TITLE = "Add Cook's Note";
    private static final String INGREDIENT_TITLE = "Add Ingredient";
    private static final String STEP_TITLE = "Add Step";
    private static final String IMAGE_TITLE = "Add Image";
    private static final String KEYWORD_TITLE = "Add Keyword";
    private static final String TAG = CookbookManualFragment.class.getSimpleName();
    private static final int CONTENT_CODE = 210;
    private static final int COOK_NOTE_CODE = 215;
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
    private List<CookNoteEntity> mCookNoteList = new ArrayList<>();
    private List<ImageEntity> mImageList = new ArrayList<>();
    private List<IngredientEntity> mIngredientList = new ArrayList<>();
    private List<StepEntity> mStepList = new ArrayList<>();
    private List<KeywordEntity> mKeywordList = new ArrayList<>();

    /**
     * Default constructor
     */
    public CookbookManualFragment() {}

    /**
     * Method for cookbook manual fragment new instance
     * @param state of the app
     * @return cookbook manual fragment
     */
    public static CookbookManualFragment newInstance(int state) {
        CookbookManualFragment fragment = new CookbookManualFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Method to get the arguments of the instance
     * @param savedInstanceState to save state of the fragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mState = getArguments().getInt(ARG_STATE);
        }
    }

    /**
     * Method to create the views for cookbook manual fragment
     * @param inflater for the views
     * @param container of the views
     * @param savedInstanceState of the fragment
     * @return cookbook manual fragment
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment and get binding
        mBinding = CookbookManualFragmentBinding.inflate(inflater, container, false);
        // get view model to handle data
        mRecipeViewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        // set title
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity()))
                .getSupportActionBar()).setTitle(getString(R.string.manual_entry_menu_title));
        // if state is saved restore
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipe = savedInstanceState.getParcelable(RECIPE);
            mCookNoteList = savedInstanceState.getParcelable(COOK_NOTES);
            mImageList = savedInstanceState.getParcelableArrayList(IMAGES);
            mIngredientList = savedInstanceState.getParcelableArrayList(INGREDIENTS);
            mStepList = savedInstanceState.getParcelableArrayList(STEPS);
        }
        // set fragment for cookbook manual fragment
        getChildFragmentManager().beginTransaction()
                // set content fragment in add mode
                .replace(mBinding.manualContentContainer.getId(), ContentFragment
                        .newInstance(mState, mRecipe))
                // set cook note fragment in add mode
                .replace(mBinding.manualCookNoteListContainer.getId(), CookNoteFragment
                        .newInstance(mState, mCookNoteList))
                // set ingredient fragment in add mode
                .replace(mBinding.manualIngredientListContainer.getId(), IngredientFragment
                        .newInstance(mState, mIngredientList))
                // set step fragment in add m ode
                .replace(mBinding.manualStepListContainer.getId(), StepFragment
                        .newInstance(mState, mStepList))
                // set image fragment in add mode
                .replace(mBinding.manualImageListContainer.getId(), ImageFragment
                        .newInstance(mState, mImageList))
                // set keyword fragment in add mode
                .replace(mBinding.manualKeywordListContainer.getId(), KeywordFragment
                        .newInstance(mState, mKeywordList))
                .commit();
        // set listener for content add button
        mBinding.manualAddContentButton.setOnClickListener(v -> {
            // open edit content dialog box in add mode
            EditContentDialogFragment editContentDialogFragment = EditContentDialogFragment
                    .newInstance(CONTENT_TITLE, CookbookActivity.STATE_ADD, mRecipe);
            editContentDialogFragment.setTargetFragment(this, CONTENT_CODE);
            if (getActivity() != null) {
                editContentDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.EDIT_CONTENT_DIALOG);
            }
        });
        // set listener for add cook note button
        mBinding.manualAddCookNoteButton.setOnClickListener(v -> {
            // open add cook note dialog box in add mode
            EditCookNoteDialogFragment editCookNoteDialogFragment = EditCookNoteDialogFragment
                    .newInstance(COOK_NOTE_TITLE, CookbookActivity.STATE_ADD, null);
            editCookNoteDialogFragment.setTargetFragment(this, COOK_NOTE_CODE);
            if (getActivity() != null) {
                editCookNoteDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.EDIT_COOK_NOTE_DIALOG);
            }
        });
        // set listener for add ingredient button
        mBinding.manualAddIngredientButton.setOnClickListener(v -> {
            // open edit ingredient dialog box in add mode
            EditIngredientDialogFragment editIngredientDialogFragment = EditIngredientDialogFragment
                    .newInstance(INGREDIENT_TITLE, CookbookActivity.STATE_ADD, null);
            editIngredientDialogFragment.setTargetFragment(this, INGREDIENT_CODE);
            if (getActivity() != null) {
                editIngredientDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.EDIT_INGREDIENT_DIALOG);
            }
        });
        // set listener for add step button
        mBinding.manualAddStepButton.setOnClickListener(v -> {
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
        mBinding.manualAddImageGalleryButton.setOnClickListener(v -> {
            // open intent to pick image
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, GALLERY_TITLE), GALLERY_CODE);
        });
        // set listener for add image internet button
        mBinding.manualAddImageInternetButton.setOnClickListener(v -> {
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
        mBinding.manualAddKeywordButton.setOnClickListener(v -> {
            // open add keyword dialog box in add mode
            AddKeywordDialogFragment addKeywordDialogFragment = AddKeywordDialogFragment
                    .newInstance(KEYWORD_TITLE, CookbookActivity.STATE_ADD, null);
            addKeywordDialogFragment.setTargetFragment(this, KEYWORD_CODE);
            if (getActivity() != null) {
                addKeywordDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.ADD_KEYWORD_DIALOG);
            }
        });
        // set listener for submit button
        mBinding.manualSubmitButton.setOnClickListener(v -> {
            // add recipe and all associated elements to the database
            mRecipeViewModel.insertAll(mRecipe, mCookNoteList, mIngredientList, mStepList,
                    mImageList, mKeywordList);
            getActivity().getSupportFragmentManager().popBackStack();
        });
        return mBinding.getRoot();
    }

    /**
     * Method to get results from pick image intent
     * @param requestCode to use
     * @param resultCode to use
     * @param data to get
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // test result code
        if (resultCode == RESULT_OK) {
            // test request code
            if (requestCode == GALLERY_CODE) {
                // test data
                if (data != null) {
                    // get uri
                    Uri uri = data.getData();
                    // create image entity
                    ImageEntity image = new ImageEntity();
                    // test uri
                    if (uri != null) {
                        // get image height and width
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
                                    // set image height
                                    image.setHeight(options.outHeight);
                                    // set image width
                                    image.setWidth(options.outWidth);
                                }
                            } catch (IOException e) {
                                Log.e(TAG, " error" + e.getMessage());
                            }
                        }
                        // add image to image list
                        mImageList.add(image);
                        // reset image fragment
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

    /**
     * Method to save state of cookbook manual fragment
     * @param outState to save state in
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        // call super
        super.onSaveInstanceState(outState);
        // save state
        outState.putInt(STATE, mState);
        outState.putParcelable(RECIPE, mRecipe);
        outState.putParcelableArrayList(COOK_NOTES, (ArrayList<CookNoteEntity>) mCookNoteList);
        outState.putParcelableArrayList(IMAGES, (ArrayList<ImageEntity>) mImageList);
        outState.putParcelableArrayList(INGREDIENTS, (ArrayList<IngredientEntity>) mIngredientList);
        outState.putParcelableArrayList(STEPS, (ArrayList<StepEntity>) mStepList);
    }

    /**
     * Method to handle edit content dialog box submit button
     * @param recipe to use
     */
    @Override
    public void onFinishEditContentDialog(RecipeEntity recipe) {
        // set recipe
        mRecipe = recipe;
        // reset content fragment to show changes
        getChildFragmentManager().beginTransaction()
                .replace(mBinding.manualContentContainer.getId(),
                        ContentFragment.newInstance(mState, mRecipe))
                .commit();
    }

    /**
     * Method to handle edit ingredient dialog box submit button
     * @param ingredient to use
     */
    @Override
    public void onFinishEditIngredientDialog(IngredientEntity ingredient) {
        // add ingredient to ingredient list
        mIngredientList.add(ingredient);
        // reset ingredient fragment to show ingredient added
        getChildFragmentManager().beginTransaction()
                .replace(mBinding.manualIngredientListContainer.getId(),
                        IngredientFragment.newInstance(mState, mIngredientList))
                .commit();
    }

    /**
     * Method to handle edit step dialog box submit button
     * @param step to use
     */
    @Override
    public void onFinishEditStepDialog(StepEntity step) {
        // add step to step list
        mStepList.add(step);
        // reset step fragment to show added step
        getChildFragmentManager()
                .beginTransaction()
                .replace(mBinding.manualStepListContainer.getId(),
                        StepFragment.newInstance(mState, mStepList))
                .commit();
    }

    /**
     * Method to handle edit keyword dialog box submit button
     * @param keyword to use
     */
    @Override
    public void onFinishEditKeywordDialog(KeywordEntity keyword) {
        // add keyword to keyword list
        mKeywordList.add(keyword);
        // reset keyword fragment to show added keyword
        getChildFragmentManager()
                .beginTransaction()
                .replace(mBinding.manualKeywordListContainer.getId(),
                        KeywordFragment.newInstance(mState, mKeywordList))
                .commit();
    }

    /**
     * Method to handle edit image dialog box submit button
     * @param image to use
     */
    @Override
    public void onFinishAddImageDialog(ImageEntity image) {
        // add image to image list
        mImageList.add(image);
        // reset image fragment to show added image
        getChildFragmentManager()
                .beginTransaction()
                .replace(mBinding.manualImageListContainer.getId(),
                        ImageFragment.newInstance(mState,mImageList))
                .commit();
    }

    /**
     * Method to handle edit cook note dialog box
     * @param cookNote to use
     */
    @Override
    public void onFinishEditCookNoteDialog(CookNoteEntity cookNote) {
        // add cook note to cook note list
        mCookNoteList.add(cookNote);
        // reset cook note fragment to show added cook note
        getChildFragmentManager()
                .beginTransaction()
                .replace(mBinding.manualCookNoteListContainer.getId(),
                        CookNoteFragment.newInstance(mState, mCookNoteList))
                .commit();
    }
}