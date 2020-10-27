package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larsonapps.personalcookbook.R;

import java.util.Objects;

/**
 * Class for cookbook about fragment
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

    /**
     * Method to create views for about fragment
     * @param inflater for views
     * @param container to hold views
     * @param savedInstanceState to maintain state
     * @return fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cookbook_about_fragment, container, false);
        // set title
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity()))
                .getSupportActionBar()).setTitle(getString(R.string.about_title));

        return view;
    }
}