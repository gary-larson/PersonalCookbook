package com.larsonapps.personalcookbook.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Ignore;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.adapter.KeywordRecyclerViewAdapter;
import com.larsonapps.personalcookbook.data.KeywordEntity;
import com.larsonapps.personalcookbook.databinding.KeywordFragmentItemListBinding;
import com.larsonapps.personalcookbook.model.KeywordViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class KeywordFragment extends Fragment {
    // Declare constants
    private static final String ARG_STATE = "state";
    private static final String ARG_RECIPE_ID = "recipeId";
    private static final String ARG_KEYWORDS = "keywords";
    private static final String STATE = "mState";
    private static final String RECIPE_ID = "mRecipeId";
    private static final String KEYWORDS = "mKeywordList";
    // Declare variables
    private int mState = 0;
    private int mRecipeId = 0;
    private List<KeywordEntity> mKeywordList;
    private KeywordFragment.OnListFragmentInteractionListener mListener;


    /**
     * Default constructor
     */
    @Ignore
    public KeywordFragment() {}

    /**
     * Method to create a new instance of this fragment
     * @param state of the app
     * @param recipeId of the ingredients
     * @return the new fragment
     */
    public static KeywordFragment newInstance(int state, int recipeId) {
        KeywordFragment fragment = new KeywordFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putInt(ARG_RECIPE_ID, recipeId);
        fragment.setArguments(args);
        return fragment;
    }

    public static KeywordFragment newInstance(int state, List<KeywordEntity> list) {
        KeywordFragment fragment = new KeywordFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putParcelableArrayList(ARG_KEYWORDS, (ArrayList<KeywordEntity>) list);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Method to save the state of the instance
     * @param savedInstanceState to save
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mState = getArguments().getInt(ARG_STATE);
            if (getArguments().containsKey(ARG_RECIPE_ID)) {
                mRecipeId = getArguments().getInt(ARG_RECIPE_ID);
            }
            if (getArguments().containsKey(ARG_KEYWORDS)) {
                mKeywordList = getArguments().getParcelableArrayList(ARG_KEYWORDS);
            }
        }
    }

    /**
     * Method to create the view of the fragment
     * @param inflater to inflate the view
     * @param container the view is created in
     * @param savedInstanceState of the app
     * @return the created view
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        com.larsonapps.personalcookbook.databinding.KeywordFragmentItemListBinding mBinding =
                KeywordFragmentItemListBinding.inflate(inflater, container, false);
        Context context = mBinding.getRoot().getContext();
        KeywordViewModel mKeywordViewModel = new ViewModelProvider(requireActivity()).get(KeywordViewModel.class);
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipeId = savedInstanceState.getInt(RECIPE_ID);
            mKeywordList = savedInstanceState.getParcelableArrayList(KEYWORDS);
        }
        // Set the adapter
        mBinding.keywordList.setLayoutManager(new GridLayoutManager(context,
                getResources().getInteger(R.integer.keyword_list_column_count)));
        KeywordRecyclerViewAdapter adapter = new KeywordRecyclerViewAdapter(mListener, mState);
        mBinding.keywordList.setAdapter(adapter);
        if (mState == CookbookActivity.STATE_MANUAL) {
            adapter.setData(mKeywordList);
        } else {
            mKeywordViewModel.getKeywords(mRecipeId).observe(getViewLifecycleOwner(), newKeywords -> {
                if (newKeywords != null && newKeywords.size() > 0) {
                    adapter.setData(newKeywords);
                }
            });
        }
        return mBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE, mState);
        outState.putInt(RECIPE_ID, mRecipeId);
        outState.putParcelableArrayList(KEYWORDS, (ArrayList<KeywordEntity>) mKeywordList);
    }

    /**
     * Method that initializes the listener
     * @param context to use
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof KeywordFragment.OnListFragmentInteractionListener) {
            mListener = (KeywordFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() );
        }
    }

    /**
     * Method to remove listener
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Interface for the click listener in the activity containing the fragment
     */
    public interface OnListFragmentInteractionListener {
        // set arguments type and name
        void onListFragmentInteraction(KeywordEntity keyword, int state, View view);
    }
}