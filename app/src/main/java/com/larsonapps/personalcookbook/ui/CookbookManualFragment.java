package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.CookbookIngredientsViewModel;
import com.larsonapps.personalcookbook.data.CookbookStepsViewModel;
import com.larsonapps.personalcookbook.databinding.CookbookManualFragmentBinding;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = CookbookManualFragmentBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }
}