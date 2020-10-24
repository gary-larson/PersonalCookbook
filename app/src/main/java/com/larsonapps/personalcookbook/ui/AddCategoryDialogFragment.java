package com.larsonapps.personalcookbook.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.larsonapps.personalcookbook.databinding.AddCategoryFragmentBinding;

/**
 * Class to deal with add category dialog fragment
 */
public class AddCategoryDialogFragment extends DialogFragment {
    // Declare constants
    private static final String ARG_TITLE = "title";
    // Declare variables
    AddCategoryFragmentBinding mBinding;
    String mCategory;

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
        mBinding.addCategorySubmitButton.setOnClickListener(v -> sendCategoryToParent());
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
        // set focus
        mBinding.addCategoryEditText.requestFocus();
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

    /**
     * Method to send the keyword to the calling activity
     */
    public void sendCategoryToParent() {
        // set listener
        AddCategoryDialogFragment.OnAddCategoryDialogListener listener =
                (AddCategoryDialogFragment.OnAddCategoryDialogListener) getActivity();
        // build cook note
        String tempString = mBinding.addCategoryEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mCategory))) {
            mCategory = tempString;
        }
        // send cook note
        if (listener != null) {
            listener.onFinishedAddDialog(mCategory);
        }
        dismiss();
    }

    /**
     * Interface for the listener to pass the category to the activity
     */
    public interface OnAddCategoryDialogListener {
        void onFinishedAddDialog(String category);
    }
}
