package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.databinding.CookbookDetailsContentFragmentBinding;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CookbookDetailsContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CookbookDetailsContentFragment extends Fragment {

    // Constants for parameter initialization
    private static final String ARG_IS_EDIT= "IsEdit";
    private static final String ARG_IS_MANUAL = "IsManual";

    // Declare variables
    private boolean isEdit;
    private boolean isManual;
    private CookbookDetailsContentFragmentBinding mBinding;

    public CookbookDetailsContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param isEdit Parameter 1.
     * @param isManual Parameter 2.
     * @return A new instance of fragment CookbookDetailsContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CookbookDetailsContentFragment newInstance(boolean isEdit, boolean isManual) {
        CookbookDetailsContentFragment fragment = new CookbookDetailsContentFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_IS_EDIT, isEdit);
        args.putBoolean(ARG_IS_MANUAL, isManual);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isEdit = getArguments().getBoolean(ARG_IS_EDIT);
            isManual = getArguments().getBoolean(ARG_IS_MANUAL);
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = CookbookDetailsContentFragmentBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }
}