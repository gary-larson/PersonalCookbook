package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.databinding.EditIngredientFragmentBinding;

public class EditIngredientDialogFragment extends DialogFragment {
    // Declare constants
    private static final String ARG_TITLE = "title";
    private static final String ARG_STATE = "state";
    private static final String ARG_INGREDIENT = "ingredient";
    private static final String TITLE = "mTitle";
    private static final String STATE = "mState";
    private static final String INGREDIENT = "mIngredient";
    // Declare variables
    private EditIngredientFragmentBinding mBinding;
    private int mState;
    private IngredientEntity mIngredient;
    private String mTitle;


    /**
     * Default constructor
     */
    public EditIngredientDialogFragment() {
    }

    /**
     * Method to create a new instance of edit ingredient dialog fragment
     *
     * @param title      to display
     * @param state      to save
     * @param ingredient to use
     * @return created dialog fragment
     */
    public static EditIngredientDialogFragment newInstance(String title, int state,
                                                           IngredientEntity ingredient) {
        EditIngredientDialogFragment dialogFragment = new EditIngredientDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putInt(ARG_STATE, state);
        args.putParcelable(ARG_INGREDIENT, ingredient);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            mState = getArguments().getInt(ARG_STATE);
            mIngredient = getArguments().getParcelable(ARG_INGREDIENT);
        }
    }

    /**
     * Method to create the view for edit ingredient dialog fragment
     *
     * @param inflater           to inflate the view
     * @param container          the view is in
     * @param savedInstanceState of the fragment
     * @return that created view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = EditIngredientFragmentBinding.inflate(inflater, container, false);
        if (savedInstanceState != null) {
            mTitle = savedInstanceState.getString(TITLE);
            mState = savedInstanceState.getInt(STATE);
            mIngredient = savedInstanceState.getParcelable(INGREDIENT);
        }
        if (mIngredient != null) {
            String temp = mIngredient.getName();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editIngredientNameEditText.setText(temp);
            }
            temp = mIngredient.getAmount();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editIngredientAmountEditText.setText(temp);
            }
            temp = mIngredient.getMeasure();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editIngredientMeasureEditText.setText(temp);
            }
            temp = mIngredient.getPreparation();
            if (temp != null && !temp.isEmpty()) {
                mBinding.editIngredientPreparationEditText.setText(temp);
            }
        } else {
            mIngredient = new IngredientEntity();
        }
        mBinding.editIngredientSubmitButton.setOnClickListener(v -> sendIngredientToParent());
        return mBinding.getRoot();
    }

    /**
     * Method to display the dialog fragment
     *
     * @param view               to display
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
        mBinding.editIngredientNameEditText.requestFocus();
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
        outState.putParcelable(INGREDIENT, mIngredient);
    }

    /**
     * Method to send the ingredient to the calling fragment
     */
    public void sendIngredientToParent() {
        // set listener
        if (mState == CookbookActivity.STATE_ADD) {
            // Fragment listener
            EditIngredientDialogFragment.EditIngredientDialogAddListener listener =
                    (EditIngredientDialogFragment.EditIngredientDialogAddListener)
                            getTargetFragment();
            // build ingredient
            buildIngredient();
            // send ingredient
            if (listener != null) {
                listener.onFinishEditIngredientDialog(mIngredient);
            }
        } else {
            // Activity Listener
            EditIngredientDialogFragment.EditIngredientDialogEditListener listener =
                    (EditIngredientDialogFragment.EditIngredientDialogEditListener) getActivity();
            // build ingredient
            buildIngredient();
            // send ingredient
            if (listener != null) {
                listener.onFinishEditIngredientDialog(mIngredient);
            }
        }
        // close dialog
        dismiss();
    }

    /**
     * Interface for the fragment listener
     */
    public interface EditIngredientDialogAddListener {
        void onFinishEditIngredientDialog(IngredientEntity ingredient);
    }

    public interface EditIngredientDialogEditListener {
        void onFinishEditIngredientDialog(IngredientEntity ingredient);
    }

    private void buildIngredient() {
        // build ingredient
        String tempString = mBinding.editIngredientNameEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mIngredient.getName()))) {
            mIngredient.setName(tempString);
        }
        tempString = mBinding.editIngredientAmountEditText.getText().toString();

        if (!(tempString.isEmpty() || tempString.equals(mIngredient.getAmount()))) {
            mIngredient.setAmount(tempString);
        }
        tempString = mBinding.editIngredientMeasureEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mIngredient.getMeasure()))) {
            mIngredient.setMeasure(tempString);
        }
        tempString = mBinding.editIngredientPreparationEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mIngredient.getPreparation()))) {
            mIngredient.setPreparation(tempString);
        }
    }
}
