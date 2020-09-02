package com.larsonapps.personalcookbook.ui;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.CookbookIngredientsViewModel;

public class CookbookDetailsFragment extends Fragment {

    private CookbookIngredientsViewModel mViewModel;

    public static CookbookDetailsFragment newInstance() {
        return new CookbookDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cookbook_details_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CookbookIngredientsViewModel.class);
        // TODO: Use the ViewModel
    }

}