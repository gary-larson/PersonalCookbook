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

/**
 * Class for cook note fragment
 */
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

    /**
     * Method to create a cook note fragment instance
     * @param state of the app
     * @param recipeId of the recipe the cook notes are for
     * @return cook note fragment
     */
    public static CookNoteFragment newInstance(int state, int recipeId) {
        CookNoteFragment fragment = new CookNoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putInt(ARG_RECIPE_ID, recipeId);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Methd to creat a cook note fragment instance
     * @param state of the app
     * @param list of cook notes
     * @return cook note gragment
     */
    public static CookNoteFragment newInstance(int state, List<CookNoteEntity> list) {
        CookNoteFragment fragment = new CookNoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putParcelableArrayList(ARG_COOK_NOTES, (ArrayList<CookNoteEntity>) list);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Method to get arguments
     * @param savedInstanceState of the fragment
     */
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

    /**
     * Method to create cook book note fragment views
     * @param inflater of the views
     * @param container for the views
     * @param savedInstanceState for fragment state
     * @return cook note fragment
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        com.larsonapps.personalcookbook.databinding.CookNoteFragmentItemListBinding mBinding =
                CookNoteFragmentItemListBinding.inflate(inflater, container, false);

        // Set the context
        Context context = mBinding.getRoot().getContext();
        // set the layout manager
        mBinding.cookNoteList.setLayoutManager(new LinearLayoutManager(context));
        // connect to cook note view model
        CookNoteViewModel mCookNoteViewModel = new ViewModelProvider(requireActivity())
                .get(CookNoteViewModel.class);
        // if saved instance state restore
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipeId = savedInstanceState.getInt(RECIPE_ID);
            mCookNoteList = savedInstanceState.getParcelableArrayList(COOK_NOTES);
        }
        // create the adapter
        CookNoteRecyclerViewAdapter adapter = new CookNoteRecyclerViewAdapter(mListener, mState);
        // set the adapter
        mBinding.cookNoteList.setAdapter(adapter);
        // test state
        if (mState == CookbookActivity.STATE_MANUAL) {
            // set data
            adapter.setData(mCookNoteList);
        } else {
            // set observer for cook notelive data
            mCookNoteViewModel.getNotes(mRecipeId).observe(getViewLifecycleOwner(), newCookNotes -> {
                // test new cook notes
                if (newCookNotes != null && newCookNotes.size() > 0) {
                    // set data
                    adapter.setData(newCookNotes);
                }
            });
        }
        return mBinding.getRoot();
    }

    /**
     * Method to set saved instance data
     * @param outState to save state data
     */
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
