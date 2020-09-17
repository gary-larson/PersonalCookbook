package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.CookbookIngredientsViewModel;
import com.larsonapps.personalcookbook.databinding.CookbookDetailsFragmentBinding;
import com.larsonapps.personalcookbook.databinding.CookbookEditFragmentBinding;
import com.larsonapps.personalcookbook.utilities.GlideApp;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CookbookEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CookbookEditFragment extends Fragment {

    // Declare variables
    private CookbookIngredientsViewModel mViewModel;
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
                .replace(mBinding.contentContainer.getId(), ContentFragment
                        .newInstance(0))
                .replace(mBinding.ingredientListContainer.getId(), IngredientFragment
                        .newInstance(1, 1))
                .replace(mBinding.stepListContainer.getId(), StepFragment
                        .newInstance(1, 1))
                .replace(mBinding.imageListContainer.getId(), ImageFragment.newInstance(1, 1))
                .commit();
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
        mViewModel = new ViewModelProvider(requireActivity())
                .get(CookbookIngredientsViewModel.class);
        // TODO: Use the ViewModel
    }

}