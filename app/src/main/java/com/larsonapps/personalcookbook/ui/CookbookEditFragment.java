package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.model.IngredientViewModel;
import com.larsonapps.personalcookbook.model.StepViewModel;
import com.larsonapps.personalcookbook.databinding.CookbookEditFragmentBinding;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CookbookEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CookbookEditFragment extends Fragment {

    // Declare variables
    private IngredientViewModel mIngredientsViewModel;
    private StepViewModel mStepsViewModel;
    private CookbookEditFragmentBinding mBinding;
    private RecipeEntity mRecipe;

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
                        .newInstance(CookbookActivity.STATE_EDIT, mRecipe))
                .replace(mBinding.editIngredientListContainer.getId(), IngredientFragment
                        .newInstance(CookbookActivity.STATE_EDIT, mRecipe.getId()))
                .replace(mBinding.editStepListContainer.getId(), StepFragment
                        .newInstance(CookbookActivity.STATE_EDIT, mRecipe.getId()))
                .replace(mBinding.editImageListContainer.getId(), ImageFragment
                        .newInstance(CookbookActivity.STATE_EDIT, mRecipe.getId()))
                .commit();
        mBinding.editRecipeNameEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || event != null) {
                String text = v.getText().toString();
                Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
                return true;
            }
            return false;
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
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mIngredientsViewModel = new ViewModelProvider(requireActivity())
                .get(IngredientViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.editToolbar);
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar())
                    .setDisplayHomeAsUpEnabled(true);
        }
    }

}