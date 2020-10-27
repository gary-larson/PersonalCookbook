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

/**
 * Class for edit step dialog fragment
 */
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

    /**
     * Method to get arguments
     * @param savedInstanceState for dialog fragment state
     */
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
        // if saved instance state restore
        if (savedInstanceState != null) {
            mTitle = savedInstanceState.getString(TITLE);
            mState = savedInstanceState.getInt(STATE);
            mStep = savedInstanceState.getParcelable(STEP);
        }
        // test step
        if (mStep != null) {
            // set number
            String temp = String.valueOf(mStep.getNumber());
            mBinding.editStepNumberEditText.setText(temp);
            // set instruction
            temp = mStep.getInstruction();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editStepInstructionEditText.setText(temp);
            }
            // set personal note
            temp = mStep.getPersonalNote();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editStepPersonalNoteEditText.setText(temp);
            }
        } else {
            // create step
            mStep = new StepEntity();
        }
        // set listener for edit step submit button
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

    /**
     * Method to save instance state of the dialog fragment
     * @param outState to save state in
     */
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
        if (mState == CookbookActivity.STATE_ADD) {
            // fragment listener
            EditStepDialogFragment.EditStepDialogAddListener listener =
                    (EditStepDialogFragment.EditStepDialogAddListener) getTargetFragment();
            // build step
            buildStep();
            // send step
            if (listener != null) {
                listener.onFinishEditStepDialog(mStep);
            }
        } else {
            // activity listener
            EditStepDialogFragment.EditStepDialogEditListener listener =
                    (EditStepDialogFragment.EditStepDialogEditListener) getActivity();
            // build step
            buildStep();
            // send step
            if (listener != null) {
                listener.onFinishEditStepDialog(mStep);
            }
        }
        dismiss();
    }

    /**
     * Method to build the step
     */
    private void buildStep() {
        // build step
        // set number
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
        // set instruction
        tempString = mBinding.editStepInstructionEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mStep.getInstruction()))) {
            mStep.setInstruction(tempString);
        }
        // set personal note
        tempString = mBinding.editStepPersonalNoteEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mStep.getPersonalNote()))) {
            mStep.setPersonalNote(tempString);
        }
    }

    /**
     * Interface for the fragment listener
     */
    public interface EditStepDialogAddListener {
        void onFinishEditStepDialog(StepEntity step);
    }

    /**
     * Interface for the activity listener
     */
    public interface EditStepDialogEditListener {
        void onFinishEditStepDialog(StepEntity step);
    }
}
