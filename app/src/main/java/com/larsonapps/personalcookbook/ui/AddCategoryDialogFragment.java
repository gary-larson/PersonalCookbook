package com.larsonapps.personalcookbook.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.larsonapps.personalcookbook.databinding.AddCategoryFragmentBinding;

/**
 * Class to deal with add category dialog fragment
 */
public class AddCategoryDialogFragment extends DialogFragment implements
        EditText.OnEditorActionListener {
    // Declare constants
    private static final String ARG_TITLE = "title";
    // Declare variables
    AddCategoryFragmentBinding mBinding;

    /**
     * Default constructor
     */
    public AddCategoryDialogFragment() {}

    /**
     * Method to create a new instance of add category dialog fragment
     * @param title to display
     * @return created dialog fragment
     */
    public static AddCategoryDialogFragment newInstance(String title) {
        AddCategoryDialogFragment dialogFragment = new AddCategoryDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    /**
     * Method to create the view for add category dialog fragment
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
        mBinding = AddCategoryFragmentBinding.inflate(inflater, container, false);
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
        mBinding.addCategoryEditText.setOnEditorActionListener(this);
        // set focus
        mBinding.addCategoryEditText.requestFocus();
        // display the virtual keyboard
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (getArguments() != null) {
            // set title
            dialog.setTitle(getArguments().getString(ARG_TITLE));
        }
        return dialog;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text back to activity through the implemented listener
            OnAddCategoryDialogListener listener = (OnAddCategoryDialogListener) getActivity();
            if (listener != null) {
                listener.onFinishedAddDialog(mBinding.addCategoryEditText.getText().toString());
            }
            // Close the dialog and return back to the parent activity
            dismiss();
            return true;
        }
        return false;
    }

    /**
     * Interface for the listener to pass the category to the activity
     */
    public interface OnAddCategoryDialogListener {
        void onFinishedAddDialog(String category);
    }
}
