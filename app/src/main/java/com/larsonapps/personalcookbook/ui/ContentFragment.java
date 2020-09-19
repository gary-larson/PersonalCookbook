package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.larsonapps.personalcookbook.data.Ingredient;
import com.larsonapps.personalcookbook.databinding.ContentFragmentBinding;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentFragment extends Fragment {

    // Constants for parameter initialization
    private static final String ARG_STATE= "state";
    private  static final int STATE_NONE = 0;
    private static final int STATE_EDIT= 1;
    private static final int STATE_MANUAL = 2;
    private static final int STATE_IMPORT = 3;

    // Declare variables
    private int mState;
    private ContentFragmentBinding mBinding;

    public ContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param state the state of fragment
     * @return A new instance of fragment CookbookDetailsContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContentFragment newInstance(int state) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mState = getArguments().getInt(ARG_STATE);
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = ContentFragmentBinding.inflate(inflater, container, false);
        if (mState == STATE_NONE) {
            mBinding.descriptionImageButton.setVisibility(View.GONE);
        } else if (mState == STATE_EDIT) {
            mBinding.descriptionImageButton.setOnClickListener(v -> {
                Toast.makeText(getContext(), "Description Clicked", Toast.LENGTH_LONG).show();
            });
            mBinding.descriptionImageButton.setVisibility(View.VISIBLE);
        }
        return mBinding.getRoot();
    }
}