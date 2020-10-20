package com.larsonapps.personalcookbook.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.larsonapps.personalcookbook.adapter.CookNoteRecyclerViewAdapter;
import com.larsonapps.personalcookbook.data.CookNoteEntity;
import com.larsonapps.personalcookbook.databinding.CookNoteFragmentItemListBinding;
import com.larsonapps.personalcookbook.model.CookNoteViewModel;

import java.util.ArrayList;
import java.util.List;

public class CookNoteFragment extends Fragment {
    // Declare constants
    private static final String ARG_STATE= "state";
    private static final String ARG_RECIPE_ID = "recipeId";
    private static final String ARG_COOK_NOTES = "cookNotes";
    private static final String STATE= "mState";
    private static final String RECIPE_ID = "mRecipeId";
    private static final String COOK_NOTES = "mCookNote";
    // Declare variables
    private int mState;
    private int mRecipeId;
    private List<CookNoteEntity> mCookNoteList;
    private CookNoteFragment.OnListFragmentInteractionListener mListener;

    /**
     * Default constructor
     */
    public CookNoteFragment() {
    }

    public static CookNoteFragment newInstance(int state, int recipeId) {
        CookNoteFragment fragment = new CookNoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putInt(ARG_RECIPE_ID, recipeId);
        fragment.setArguments(args);
        return fragment;
    }

    public static CookNoteFragment newInstance(int state, List<CookNoteEntity> list) {
        CookNoteFragment fragment = new CookNoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putParcelableArrayList(ARG_COOK_NOTES, (ArrayList<CookNoteEntity>) list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mState = getArguments().getInt(ARG_STATE);
            if (getArguments().containsKey(ARG_RECIPE_ID)) {
                mRecipeId = getArguments().getInt(ARG_RECIPE_ID);
            }
            if (getArguments().containsKey(ARG_COOK_NOTES)) {
                mCookNoteList = getArguments().getParcelableArrayList(ARG_COOK_NOTES);
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        com.larsonapps.personalcookbook.databinding.CookNoteFragmentItemListBinding mBinding =
                CookNoteFragmentItemListBinding.inflate(inflater, container, false);

        // Set the adapter
        Context context = mBinding.getRoot().getContext();

        mBinding.cookNoteList.setLayoutManager(new LinearLayoutManager(context));
        CookNoteViewModel mCookNoteViewModel = new ViewModelProvider(requireActivity())
                .get(CookNoteViewModel.class);
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipeId = savedInstanceState.getInt(RECIPE_ID);
            mCookNoteList = savedInstanceState.getParcelableArrayList(COOK_NOTES);
        }
        CookNoteRecyclerViewAdapter adapter = new CookNoteRecyclerViewAdapter(mListener, mState);
        mBinding.cookNoteList.setAdapter(adapter);
        if (mState == CookbookActivity.STATE_MANUAL) {
            adapter.setData(mCookNoteList);
        } else {
            mCookNoteViewModel.getNotes(mRecipeId).observe(getViewLifecycleOwner(), newCookNotes -> {
                if (newCookNotes != null && newCookNotes.size() > 0) {
                    adapter.setData(newCookNotes);
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
        outState.putParcelableArrayList(COOK_NOTES, (ArrayList<CookNoteEntity>) mCookNoteList);
    }

    /**
     * Method that initializes the listener
     * @param context to use
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CookNoteFragment.OnListFragmentInteractionListener) {
            mListener = (CookNoteFragment.OnListFragmentInteractionListener) context;
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
        void onListFragmentInteraction(CookNoteEntity cookNote, int state, View view);
    }
}
