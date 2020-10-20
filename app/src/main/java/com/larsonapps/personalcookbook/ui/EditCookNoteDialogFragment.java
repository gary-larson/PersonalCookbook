package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.larsonapps.personalcookbook.data.CookNoteEntity;
import com.larsonapps.personalcookbook.databinding.EditCookNoteFragmentBinding;

public class EditCookNoteDialogFragment extends DialogFragment {
    // Declare constants
    private static final String ARG_TITLE = "title";
    private static final String ARG_STATE = "state";
    private static final String ARG_COOK_NOTE = "cookNote";
    private static final String TITLE = "mTitle";
    private static final String STATE = "mState";
    private static final String COOK_NOTE = "mCookNote";
    // Declare variables
    private EditCookNoteFragmentBinding mBinding;
    private int mState;
    private CookNoteEntity mCookNote;
    private String mTitle;


    /**
     * Default constructor
     */
    public EditCookNoteDialogFragment() {}

    /**
     * Method to create a new instance of edit cook's note dialog fragment
     * @param title to display
     * @param state to save
     * @param cookNote to use
     * @return created dialog fragment
     */
    public static EditCookNoteDialogFragment newInstance(String title, int state,
                                                     CookNoteEntity cookNote) {
        EditCookNoteDialogFragment dialogFragment = new EditCookNoteDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putInt(ARG_STATE, state);
        args.putParcelable(ARG_COOK_NOTE, cookNote);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            mState = getArguments().getInt(ARG_STATE);
            mCookNote = getArguments().getParcelable(ARG_COOK_NOTE);
        }
    }

    /**
     * Method to create the view for edit cook's note dialog fragment
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
        mBinding = EditCookNoteFragmentBinding.inflate(inflater, container, false);
        if (savedInstanceState != null) {
            mTitle = savedInstanceState.getString(TITLE);
            mState = savedInstanceState.getInt(STATE);
            mCookNote = savedInstanceState.getParcelable(COOK_NOTE);
        }
        if (mCookNote != null) {
            String temp = String.valueOf(mCookNote.getNumber());
            mBinding.editCookNoteNumberEditText.setText(temp);
            temp = mCookNote.getNote();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editCookNoteEditText.setText(temp);
            }
        } else {
            mCookNote = new CookNoteEntity();
        }
        mBinding.editCookNoteSubmitButton.setOnClickListener(v -> sendCookNoteToParent());
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
        mBinding.editCookNoteNumberEditText.requestFocus();
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
        outState.putParcelable(COOK_NOTE, mCookNote);
    }

    /**
     * Method to send the step to the calling fragment
     */
    public void sendCookNoteToParent() {
        // set listener
        if (mState == CookbookActivity.STATE_ADD) {
            // fragment listener
            EditCookNoteDialogFragment.EditCookNoteDialogAddListener listener =
                    (EditCookNoteDialogFragment.EditCookNoteDialogAddListener) getTargetFragment();
            // build cook note
            buildCookNote();
            // send cook note
            if (listener != null) {
                listener.onFinishEditCookNoteDialog(mCookNote);
            }
        } else {
            // activity listener
            EditCookNoteDialogFragment.EditCookNoteDialogEditListener listener =
                    (EditCookNoteDialogFragment.EditCookNoteDialogEditListener) getActivity();
            // build cook note
            buildCookNote();
            // send cook note
            if (listener != null) {
                listener.onFinishEditCookNoteDialog(mCookNote);
            }
        }
        dismiss();
    }

    /**
     * Method to build the step
     */
    private void buildCookNote() {
        // build step
        String tempString = mBinding.editCookNoteNumberEditText.getText().toString();
        int tempInt;
        try {
            tempInt = Integer.parseInt(tempString);
        } catch (NumberFormatException e) {
            tempInt = 0;
        }
        if (!(tempInt == 0 || tempInt == mCookNote.getNumber())) {
            mCookNote.setNumber(tempInt);
        }
        tempString = mBinding.editCookNoteEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mCookNote.getNote()))) {
            mCookNote.setNote(tempString);
        }
    }

    /**
     * Interface for the fragment listener
     */
    public interface EditCookNoteDialogAddListener {
        void onFinishEditCookNoteDialog(CookNoteEntity cookNote);
    }

    /**
     * Interface for the activity listener
     */
    public interface EditCookNoteDialogEditListener {
        void onFinishEditCookNoteDialog(CookNoteEntity cookNote);
    }
}
