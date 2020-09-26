package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;


import com.larsonapps.personalcookbook.databinding.CookbookImportFragmentBinding;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = CookbookImportFragmentBinding.inflate(inflater, container, false);
        mBinding.importUrlEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || event != null) {
                String text = v.getText().toString();
                Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
                return true;
            }
            return false;
        });
        mBinding.importSubmitButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Submit clicked", Toast.LENGTH_LONG).show();
        });
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