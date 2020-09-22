package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.CookbookIngredientsViewModel;

import com.larsonapps.personalcookbook.databinding.CookbookDetailsFragmentBinding;
import com.larsonapps.personalcookbook.utilities.GlideApp;

public class CookbookDetailsFragment extends Fragment {
    // Declare variables
    private CookbookIngredientsViewModel mViewModel;
    private CookbookDetailsFragmentBinding mBinding;

    public static CookbookDetailsFragment newInstance() {
        return new CookbookDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = CookbookDetailsFragmentBinding.inflate(inflater, container, false);
        String assetUrl = "file:///android_asset/ApplePie.jpg";
        GlideApp.with(this)
                .load(assetUrl)
                .into(mBinding.photo);
        mBinding.toolbar.setTitle(getString(R.string.app_name));
        getChildFragmentManager().beginTransaction()
                .replace(mBinding.contentContainer.getId(), ContentFragment
                        .newInstance(CookbookActivity.STATE_DISPLAY))
                .replace(mBinding.ingredientListContainer.getId(), IngredientFragment
                        .newInstance(CookbookActivity.STATE_DISPLAY))
                .replace(mBinding.stepListContainer.getId(), StepFragment
                        .newInstance(CookbookActivity.STATE_DISPLAY))
                .commit();
        CookbookActivity activity = (CookbookActivity) getActivity();
        int height = 0;
        if (activity != null) {
            height = activity.getHeight();
            float h = getResources().getDimension(R.dimen.details_photo_height);
            height -= h - getResources().getInteger(R.integer.list_size_adjuster);
            height /= getResources().getInteger(R.integer.list_size_divisor);
        }
        ViewGroup.LayoutParams params = mBinding.ingredientListContainer.getLayoutParams();
        params.height = height;
        mBinding.ingredientListContainer.setLayoutParams(params);
        params = mBinding.stepListContainer.getLayoutParams();
        params.height = height;
        mBinding.stepListContainer.setLayoutParams(params);
        mBinding.editFab.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Edit FAB clicked", Toast.LENGTH_LONG).show();
        });
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity())
                .get(CookbookIngredientsViewModel.class);
        // TODO: Use the ViewModel
    }

}