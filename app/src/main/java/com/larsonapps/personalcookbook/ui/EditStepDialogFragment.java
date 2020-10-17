package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.databinding.EditStepFragmentBinding;

public class EditStepDialogFragment extends DialogFragment {
    // Declare constants
    private static final String ARG_TITLE = "title";
    private static final String ARG_STATE = "state";
    private static final String ARG_STEP = "step";
    private static final String TITLE = "mTitle";
    private static final String STATE = "mState";
    private static final String STEP = "mStep";
    // Declare variables
    private EditStepFragmentBinding mBinding;
    private int mState;
    private StepEntity mStep;
    private String mTitle;


    /**
     * Default constructor
     */
    public EditStepDialogFragment() {}

    /**
     * Method to create a new instance of edit step dialog fragment
     * @param title to display
     * @param state to save
     * @param step to use
     * @return created dialog fragment
     */
    public static EditStepDialogFragment newInstance(String title, int state,
                                                           StepEntity step) {
        EditStepDialogFragment dialogFragment = new EditStepDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putInt(ARG_STATE, state);
        args.putParcelable(ARG_STEP, step);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            mState = getArguments().getInt(ARG_STATE);
            mStep = getArguments().getParcelable(ARG_STEP);
        }
    }

    /**
     * Method to create the view for edit step dialog fragment
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
        mBinding = EditStepFragmentBinding.inflate(inflater, container, false);
        if (savedInstanceState != null) {
            mTitle = savedInstanceState.getString(TITLE);
            mState = savedInstanceState.getInt(STATE);
            mStep = savedInstanceState.getParcelable(STEP);
        }
        if (mStep != null) {
            String temp = String.valueOf(mStep.getNumber());
            mBinding.editStepNumberEditText.setText(temp);
            temp = mStep.getInstruction();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editStepInstructionEditText.setText(temp);
            }
        } else {
            mStep = new StepEntity();
        }
        mBinding.editStepSubmitButton.setOnClickListener(v -> sendStepToParent());
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
        mBinding.editStepNumberEditText.requestFocus();
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
        outState.putParcelable(STEP, mStep);
    }

    /**
     * Method to send the step to the calling fragment
     */
    public void sendStepToParent() {
        // set listener
        EditStepDialogFragment.EditStepDialogListener listener =
                (EditStepDialogFragment.EditStepDialogListener) getTargetFragment();
        // build step
        String tempString = mBinding.editStepNumberEditText.getText().toString();
        int tempInt;
        try {
            tempInt = Integer.parseInt(tempString);
        } catch (NumberFormatException e) {
            tempInt = 0;
        }
        if (!(tempInt == 0 || tempInt == mStep.getNumber())) {
            mStep.setNumber(tempInt);
        }
        tempString = mBinding.editStepInstructionEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mStep.getInstruction()))) {
            mStep.setInstruction(tempString);
        }
        // send step
        if (listener != null) {
            listener.onFinishEditStepDialog(mStep);
        }
        dismiss();
    }

    /**
     * Interface for the listener
     */
    public interface EditStepDialogListener {
        void onFinishEditStepDialog(StepEntity step);
    }
}
