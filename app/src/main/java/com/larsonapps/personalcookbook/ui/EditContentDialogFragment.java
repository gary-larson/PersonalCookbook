package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.databinding.EditContentFragmentBinding;

public class EditContentDialogFragment extends DialogFragment {
    // Declare constants
    private static final String ARG_TITLE = "title";
    private static final String ARG_STATE = "state";
    private static final String ARG_RECIPE = "recipe";
    private static final String TITLE = "mTitle";
    private static final String STATE = "mState";
    private static final String RECIPE = "mRecipe";
    // Declare variables
    private EditContentFragmentBinding mBinding;
    private int mState;
    private RecipeEntity mRecipe;
    private String mTitle;


    /**
     * Default constructor
     */
    public EditContentDialogFragment() {}

    /**
     * Method to create a new instance of edit content dialog fragment
     * @param title to display
     * @param state to save
     * @param recipe to use
     * @return created dialog fragment
     */
    public static EditContentDialogFragment newInstance(String title, int state, RecipeEntity recipe) {
        EditContentDialogFragment dialogFragment = new EditContentDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putInt(ARG_STATE, state);
        args.putParcelable(ARG_RECIPE, recipe);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    /**
     * Method to get arguments
     * @param savedInstanceState of the fragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            mState = getArguments().getInt(ARG_STATE);
            mRecipe = getArguments().getParcelable(ARG_RECIPE);
        }
    }

    /**
     * Method to create the view for edit content dialog fragment
     * @param inflater to inflate the view
     * @param container the view is in
     * @param savedInstanceState of the fragment
     * @return that created view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = EditContentFragmentBinding.inflate(inflater, container, false);
        // if saved instance state restore
        if (savedInstanceState != null) {
            mTitle = savedInstanceState.getString(TITLE);
            mState = savedInstanceState.getInt(STATE);
            mRecipe = savedInstanceState.getParcelable(RECIPE);
        }
        // test recipe
        if (mRecipe != null) {
            // set name
            String temp = mRecipe.getName();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editContentNameEditText.setText(temp);
            }
            // set short description
            temp = mRecipe.getShortDescription();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editContentShortDescriptionEditText.setText(temp);
            }
            // set servings
            temp = String.valueOf(mRecipe.getServings());
            mBinding.editContentServingsEditText.setText(temp);
            // set prep time
            temp = String.valueOf(mRecipe.getPrepTime());
            mBinding.editContentPrepTimeEditText.setText(temp);
            // set cook time
            temp = String.valueOf(mRecipe.getCookTime());
            mBinding.editContentCookTimeEditText.setText(temp);
            // set total time
            temp = String.valueOf(mRecipe.getTotalTime());
            mBinding.editContentTotalTimeEditText.setText(temp);
            // set description
            temp = mRecipe.getDescription();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editContentDescriptionEditText.setText(temp);
            }
            // set copyright
            temp = mRecipe.getCopyright();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editContentCopyrightEditText.setText(temp);
            }
            // set personal note
            temp = mRecipe.getPersonalNote();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editContentPersonalNoteEditText.setText(temp);
            }
        } else {
            // create a recipe entity
            mRecipe = new RecipeEntity();
        }
        // set listener for edit content submit button
        mBinding.editContentSubmitButton.setOnClickListener(v -> sendRecipeToParent());
        return mBinding.getRoot();
    }

    /**
     * Method to display the dialog fragment
     * @param view to display
     * @param savedInstanceState of the fragment
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // set title
        if (getDialog() != null) {
            getDialog().setTitle(mTitle);
        }
        // set focus
        mBinding.editContentNameEditText.requestFocus();
        // display the virtual keyboard
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    /**
     * Method to save instance data
     * @param outState to save instance data
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TITLE, mTitle);
        outState.putInt(STATE, mState);
        outState.putParcelable(RECIPE, mRecipe);
    }

    /**
     * Method to send the recipe to the calling fragment
     */
    public void sendRecipeToParent() {
        // set listener
        EditContentDialogListener listener = (EditContentDialogListener) getTargetFragment();
        // build recipe
        // set name
        String tempString = mBinding.editContentNameEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mRecipe.getName()))) {
            mRecipe.setName(tempString);
        }
        // set servings
        tempString = mBinding.editContentServingsEditText.getText().toString();
        int tempInt;
        try {
            tempInt = Integer.parseInt(tempString);
        } catch (NumberFormatException e) {
            tempInt = 0;
        }
        if (!(tempInt == 0 || tempInt == mRecipe.getServings())) {
            mRecipe.setServings(tempInt);
        }
        // set prep time
        tempString = mBinding.editContentPrepTimeEditText.getText().toString();
        try {
            tempInt = Integer.parseInt(tempString);
        } catch (NumberFormatException e) {
            tempInt = 0;
        }
        if (!(tempInt == 0 || tempInt == mRecipe.getPrepTime())) {
            mRecipe.setPrepTime(tempInt);
        }
        // set cook time
        tempString = mBinding.editContentCookTimeEditText.getText().toString();
        try {
            tempInt = Integer.parseInt(tempString);
        } catch (NumberFormatException e) {
            tempInt = 0;
        }
        if (!(tempInt == 0 || tempInt == mRecipe.getCookTime())) {
            mRecipe.setCookTime(tempInt);
        }
        // set total time
        tempString = mBinding.editContentTotalTimeEditText.getText().toString();
        try {
            tempInt = Integer.parseInt(tempString);
        } catch (NumberFormatException e) {
            tempInt = 0;
        }
        if (!(tempInt == 0 || tempInt == mRecipe.getTotalTime())) {
            mRecipe.setTotalTime(tempInt);
        }
        // set short description
        tempString = mBinding.editContentShortDescriptionEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mRecipe.getShortDescription()))) {
            mRecipe.setShortDescription(tempString);
            if (mRecipe.getShortDescription().isEmpty()) {
                mRecipe.setDescription(tempString);
            }
        }
        // set description
        tempString = mBinding.editContentDescriptionEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mRecipe.getDescription()))) {
            mRecipe.setDescription(tempString);
        }
        // set copyright
        tempString = mBinding.editContentCopyrightEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mRecipe.getCopyright()))) {
            mRecipe.setCopyright(tempString);
        }
        // set personal note
        tempString = mBinding.editContentPersonalNoteEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mRecipe.getPersonalNote()))) {
            mRecipe.setPersonalNote(tempString);
        }
        // send recipe
        if (listener != null) {
            listener.onFinishEditContentDialog(mRecipe);
        }
        dismiss();
    }

    /**
     * Interface for the listener
     */
    public interface EditContentDialogListener {
        void onFinishEditContentDialog(RecipeEntity recipe);
    }
}
