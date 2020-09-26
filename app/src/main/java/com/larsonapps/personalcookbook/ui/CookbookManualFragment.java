package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.CookbookIngredientsViewModel;
import com.larsonapps.personalcookbook.data.CookbookStepsViewModel;
import com.larsonapps.personalcookbook.databinding.CookbookEditFragmentBinding;
import com.larsonapps.personalcookbook.databinding.CookbookManualFragmentBinding;
import com.larsonapps.personalcookbook.databinding.ImageFragmentItemListBinding;
import com.larsonapps.personalcookbook.databinding.IngredientFragmentItemListBinding;
import com.larsonapps.personalcookbook.databinding.StepFragmentItemListBinding;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CookbookManualFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CookbookManualFragment extends Fragment {
    // Declare variables
    CookbookIngredientsViewModel mIngredientsViewModel;
    CookbookStepsViewModel mStepsViewModel;
    CookbookManualFragmentBinding mBinding;

    public CookbookManualFragment() {
        // Required empty public constructor
    }

    public static CookbookManualFragment newInstance() {
       return new CookbookManualFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = CookbookManualFragmentBinding.inflate(inflater, container, false);

        getChildFragmentManager().beginTransaction()
                .replace(mBinding.manualIngredientListContainer.getId(), IngredientFragment
                        .newInstance(CookbookActivity.STATE_MANUAL))
                .replace(mBinding.manualStepListContainer.getId(), StepFragment
                        .newInstance(CookbookActivity.STATE_MANUAL))
                .replace(mBinding.manualImageListContainer.getId(), ImageFragment.newInstance(CookbookActivity.STATE_MANUAL))
                .commit();
        mBinding.manualRecipeNameEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || event != null) {
                String text = v.getText().toString();
                Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
                return true;
            }
            return false;
        });
        mBinding.manualDescriptionEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || event != null) {
                String text = v.getText().toString();
                Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
                return true;
            }
            return false;
        });
        mBinding.manualAddIngredientButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add Ingredient clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.manualAddStepButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add Step clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.manualAddImageButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add Image clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.manualSubmitButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Submit clicked", Toast.LENGTH_LONG).show();
        });
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.manualToolbar);
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar())
                    .setDisplayHomeAsUpEnabled(true);
        }
    }
}