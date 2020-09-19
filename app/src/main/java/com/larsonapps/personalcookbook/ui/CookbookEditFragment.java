package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
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
import com.larsonapps.personalcookbook.data.Step;
import com.larsonapps.personalcookbook.databinding.CookbookEditFragmentBinding;
import com.larsonapps.personalcookbook.utilities.GlideApp;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CookbookEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CookbookEditFragment extends Fragment {

    // Declare variables
    private CookbookIngredientsViewModel mIngredientsViewModel;
    private CookbookStepsViewModel mStepsViewModel;
    private CookbookEditFragmentBinding mBinding;

    public static CookbookEditFragment newInstance() {
        return new CookbookEditFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = CookbookEditFragmentBinding.inflate(inflater, container, false);
        String assetUrl = "file:///android_asset/ApplePie.jpg";
       // mBinding.toolbar.setTitle(getString(R.string.app_name));
        getChildFragmentManager().beginTransaction()
                .replace(mBinding.editContentContainer.getId(), ContentFragment
                        .newInstance(1))
                .replace(mBinding.editImageListContainer.getId(), IngredientFragment
                        .newInstance(1, 1))
                .replace(mBinding.editStepListContainer.getId(), StepFragment
                        .newInstance(1, 1))
                .replace(mBinding.editImageListContainer.getId(), ImageFragment.newInstance(1, 1))
                .commit();
//        mBinding.editRecipeNameEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Toast.makeText(getContext(), "Before text changed", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Toast.makeText(getContext(), "On text changed", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                Toast.makeText(getContext(), "After text changed", Toast.LENGTH_SHORT).show();
//            }
//        });
        mBinding.editRecipeNameEditText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event != null) {
                    String text = v.getText().toString();
                    Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });
        mBinding.editReorderIngredientButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Reorder Ingredient clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.editAddIngredientButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add Ingredient clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.editReorderStepButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Reorder Step clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.editAddStepButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add Step clicked", Toast.LENGTH_LONG).show();
        });
        mBinding.editAddImageButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Add Image clicked", Toast.LENGTH_LONG).show();
        });
//        CookbookActivity activity = (CookbookActivity) getActivity();
//        int height = 0;
//        if (activity != null) {
//            height = activity.getHeight();
//            float h = getResources().getDimension(R.dimen.details_photo_height);
//            height -= h - getResources().getInteger(R.integer.list_size_adjuster);
//            height /= getResources().getInteger(R.integer.list_size_divisor);
//        }
//        ViewGroup.LayoutParams params = mBinding.ingredientListContainer.getLayoutParams();
//        params.height = height;
//        mBinding.ingredientListContainer.setLayoutParams(params);
//        params = mBinding.stepListContainer.getLayoutParams();
//        params.height = height;
//        mBinding.stepListContainer.setLayoutParams(params);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mIngredientsViewModel = new ViewModelProvider(requireActivity())
                .get(CookbookIngredientsViewModel.class);
        // TODO: Use the ViewModel
    }

}