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
    // Declare variables
    EditContentFragmentBinding mBinding;
    int mState;

    /**
     * Default constructor
     */
    public EditContentDialogFragment() {}

    /**
     * Method to create a new instance of edit content dialog fragment
     * @param title to display
     * @return created dialog fragment
     */
    public static EditContentDialogFragment newInstance(String title, int state) {
        EditContentDialogFragment dialogFragment = new EditContentDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putInt(ARG_STATE, state);
        dialogFragment.setArguments(args);
        return dialogFragment;
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
        if (getArguments() != null) {
            String title = getArguments().getString(ARG_TITLE);
            if (getDialog() != null) {
                getDialog().setTitle(title);
            }
            mState = getArguments().getInt(ARG_STATE);
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
     * Method to send the recipe to the calling fragment
     */
    public void sendRecipeToParent() {
        // set listener
        EditContentDialogListener listener = (EditContentDialogListener) getTargetFragment();
        // build recipe
        RecipeEntity recipe = new RecipeEntity();
        recipe.setName(mBinding.editContentNameEditText.getText().toString());
        recipe.setShortDescription(mBinding.editContentShortDescriptionEditText.getText().toString());
        recipe.setServings(Integer.parseInt(mBinding.editContentServingsEditText.getText().toString()));
        recipe.setPrepTime(Integer.parseInt(mBinding.editContentPrepTimeEditText.getText().toString()));
        recipe.setCookTime(Integer.parseInt(mBinding.editContentCookTimeEditText.getText().toString()));
        recipe.setTotalTime(Integer.parseInt(mBinding.editContentTotalTimeEditText.getText().toString()));
        String temp = mBinding.editContentDescriptionEditText.getText().toString();
        if (temp.isEmpty()) {
            recipe.setDescription(mBinding.editContentShortDescriptionEditText.getText().toString());
        } else {
            recipe.setDescription(temp);
        }
        recipe.setNotes(mBinding.editContentNotesEditText.getText().toString());
        recipe.setCopyright(mBinding.editContentCopyrightEditText.getText().toString());
        // send recipe
        if (listener != null) {
            listener.onFinishEditContentDialog(recipe);
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
