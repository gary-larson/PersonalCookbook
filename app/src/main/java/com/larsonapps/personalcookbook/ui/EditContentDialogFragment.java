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
        if (savedInstanceState != null) {
            mTitle = savedInstanceState.getString(TITLE);
            mState = savedInstanceState.getInt(STATE);
            mRecipe = savedInstanceState.getParcelable(RECIPE);
        }
        if (mRecipe != null) {
            String temp = mRecipe.getName();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editContentNameEditText.setText(temp);
            }
            temp = mRecipe.getShortDescription();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editContentShortDescriptionEditText.setText(temp);
            }
            temp = String.valueOf(mRecipe.getServings());
            mBinding.editContentServingsEditText.setText(temp);
            temp = String.valueOf(mRecipe.getPrepTime());
            mBinding.editContentPrepTimeEditText.setText(temp);
            temp = String.valueOf(mRecipe.getCookTime());
            mBinding.editContentCookTimeEditText.setText(temp);
            temp = String.valueOf(mRecipe.getTotalTime());
            mBinding.editContentTotalTimeEditText.setText(temp);
            temp = mRecipe.getDescription();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editContentDescriptionEditText.setText(temp);
            }
            temp = mRecipe.getNotes();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editContentNotesEditText.setText(temp);
            }
            temp = mRecipe.getCopyright();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editContentCopyrightEditText.setText(temp);
            }
        } else {
            mRecipe = new RecipeEntity();
        }
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
        String tempString = mBinding.editContentNameEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mRecipe.getName()))) {
            mRecipe.setName(tempString);
        }
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
        tempString = mBinding.editContentPrepTimeEditText.getText().toString();
        try {
            tempInt = Integer.parseInt(tempString);
        } catch (NumberFormatException e) {
            tempInt = 0;
        }
        if (!(tempInt == 0 || tempInt == mRecipe.getPrepTime())) {
            mRecipe.setPrepTime(tempInt);
        }
        tempString = mBinding.editContentCookTimeEditText.getText().toString();
        try {
            tempInt = Integer.parseInt(tempString);
        } catch (NumberFormatException e) {
            tempInt = 0;
        }
        if (!(tempInt == 0 || tempInt == mRecipe.getCookTime())) {
            mRecipe.setCookTime(tempInt);
        }
        tempString = mBinding.editContentTotalTimeEditText.getText().toString();
        try {
            tempInt = Integer.parseInt(tempString);
        } catch (NumberFormatException e) {
            tempInt = 0;
        }
        if (!(tempInt == 0 || tempInt == mRecipe.getTotalTime())) {
            mRecipe.setTotalTime(tempInt);
        }
        tempString = mBinding.editContentShortDescriptionEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mRecipe.getShortDescription()))) {
            mRecipe.setShortDescription(tempString);
            if (mRecipe.getShortDescription().isEmpty()) {
                mRecipe.setDescription(tempString);
            }
        }
        tempString = mBinding.editContentDescriptionEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mRecipe.getDescription()))) {
            mRecipe.setDescription(tempString);
        }
        tempString = mBinding.editContentNotesEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mRecipe.getNotes()))) {
            mRecipe.setNotes(tempString);
        }
        tempString = mBinding.editContentCopyrightEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mRecipe.getName()))) {
            mRecipe.setCopyright(tempString);
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
