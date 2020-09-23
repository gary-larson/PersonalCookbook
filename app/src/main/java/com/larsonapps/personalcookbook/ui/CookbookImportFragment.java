package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.larsonapps.personalcookbook.databinding.CookbookImportFragmentBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CookbookImportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CookbookImportFragment extends Fragment {
    // Declare variables
    CookbookImportFragmentBinding mBinding;



    public CookbookImportFragment() {
        // Required empty public constructor
    }


    public static CookbookImportFragment newInstance() {
        return new CookbookImportFragment();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = CookbookImportFragmentBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.importToolbar);
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar())
                    .setDisplayHomeAsUpEnabled(true);
        }
    }
}