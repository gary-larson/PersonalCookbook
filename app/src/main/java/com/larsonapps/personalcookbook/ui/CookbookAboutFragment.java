package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larsonapps.personalcookbook.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CookbookAboutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CookbookAboutFragment extends Fragment {

    /**
     * Default constructor
     */
    public CookbookAboutFragment() {}

    /**
     * Method to create a new instance of the about fragment
     * @return A new instance of fragment CookbookAboutFragment.
     */
    public static CookbookAboutFragment newInstance() {
        return new CookbookAboutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cookbook_about_fragment, container, false);
        getActivity().setTitle(R.string.about_title);
        return view;
    }
}