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
 * A simple {@link Fragment} subclass.
 * Use the {@link CookbookEditFragment#newInstance} factory method to
 * create an instance of this fragment.
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
        // Declare variables
        com.larsonapps.personalcookbook.databinding.CookbookEditFragmentBinding mBinding =
                CookbookEditFragmentBinding.inflate(inflater, container, false);
        mRecipeViewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        mCookNoteViewModel = new ViewModelProvider(requireActivity()).get(CookNoteViewModel.class);
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
                .replace(mBinding.editCookNoteListContainer.getId(), CookNoteFragment
                        .newInstance(mState, mRecipe.getId()))
                .replace(mBinding.editIngredientListContainer.getId(), IngredientFragment
                        .newInstance(mState, mRecipe.getId()))
                .replace(mBinding.editStepListContainer.getId(), StepFragment
                        .newInstance(mState, mRecipe.getId()))
                .replace(mBinding.editImageListContainer.getId(), ImageFragment
                        .newInstance(mState, mRecipe.getId()))
                .replace(mBinding.editKeywordListContainer.getId(), KeywordFragment
                        .newInstance(mState, mRecipe.getId()))
                .commit();
        mBinding.editContentButton.setOnClickListener(v -> {
            EditContentDialogFragment editContentDialogFragment = EditContentDialogFragment
                    .newInstance(CONTENT_TITLE, mState, mRecipe);
            editContentDialogFragment.setTargetFragment(this, CONTENT_CODE);
            if (getActivity() != null) {
                editContentDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.EDIT_CONTENT_DIALOG);
            }
        });
        mBinding.editAddCookNoteButton.setOnClickListener(v -> {
            EditCookNoteDialogFragment editCookNoteDialogFragment = EditCookNoteDialogFragment
                    .newInstance(COOK_NOTE_TITLE, CookbookActivity.STATE_ADD, null);
            editCookNoteDialogFragment.setTargetFragment(this, COOK_NOTE_CODE);
            if (getActivity() != null) {
                editCookNoteDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.EDIT_COOK_NOTE_DIALOG);
            }
        });
        mBinding.editAddIngredientButton.setOnClickListener(v -> {
            EditIngredientDialogFragment editIngredientDialogFragment = EditIngredientDialogFragment
                    .newInstance(INGREDIENT_TITLE, CookbookActivity.STATE_ADD, null);
            editIngredientDialogFragment.setTargetFragment(this, INGREDIENT_CODE);
            if (getActivity() != null) {
                editIngredientDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.EDIT_INGREDIENT_DIALOG);
            }
        });
        mBinding.editAddStepButton.setOnClickListener(v -> {
            EditStepDialogFragment editStepDialogFragment = EditStepDialogFragment
                    .newInstance(STEP_TITLE, CookbookActivity.STATE_ADD, null);
            editStepDialogFragment.setTargetFragment(this, STEP_CODE);
            if (getActivity() != null) {
                editStepDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.EDIT_STEP_DIALOG);
            }
        });
        mBinding.editAddImageGalleryButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, GALLERY_TITLE), GALLERY_CODE);
        });
        mBinding.editAddImageInternetButton.setOnClickListener(v -> {
            AddImageDialogFragment addImageDialogFragment = AddImageDialogFragment
                    .newInstance(IMAGE_TITLE, CookbookActivity.STATE_ADD, null);
            addImageDialogFragment.setTargetFragment(this, IMAGE_CODE);
            if(getActivity() != null) {
                addImageDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.ADD_IMAGE_DIALOG);
            }
        });
        mBinding.editAddKeywordButton.setOnClickListener(v -> {
            AddKeywordDialogFragment addKeywordDialogFragment = AddKeywordDialogFragment
                    .newInstance(KEYWORD_TITLE, CookbookActivity.STATE_ADD, null);
            addKeywordDialogFragment.setTargetFragment(this, KEYWORD_CODE);
            if (getActivity() != null) {
                addKeywordDialogFragment.show(getActivity().getSupportFragmentManager(),
                        CookbookActivity.ADD_KEYWORD_DIALOG);
            }
        });
        mBinding.deleteFab.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onDeleteFabClickListener(mRecipe);
            }
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
                        image.setRecipeId(mRecipe.getId());
                        mImageViewModel.insertImage(image);
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

    @Override
    public void onFinishAddImageDialog(ImageEntity image) {
        image.setRecipeId(mRecipe.getId());
        mImageViewModel.insertImage(image);
    }

    @Override
    public void onFinishEditCookNoteDialog(CookNoteEntity cookNote) {
        cookNote.setRecipeId(mRecipe.getId());
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

    public interface OnCookbookEditDeleteFabListener {
        void onDeleteFabClickListener(RecipeEntity recipe);
    }
}