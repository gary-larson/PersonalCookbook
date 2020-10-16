package com.larsonapps.personalcookbook.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.larsonapps.personalcookbook.adapter.StepRecyclerViewAdapter;
import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.databinding.StepFragmentItemListBinding;
import com.larsonapps.personalcookbook.model.StepViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class StepFragment extends Fragment {

    // Declare constants
    private static final String ARG_STATE= "state";
    private static final String ARG_RECIPE_ID = "recipeId";
    private static final String ARG_STEPS = "steps";
    private static final String STATE= "mState";
    private static final String RECIPE_ID = "mRecipeId";
    private static final String STEPS = "mStepList";
    // Declare variables
    private int mState;
    private int mRecipeId;
    private List<StepEntity> mStepList;
    private StepFragmentItemListBinding mBinding;
    private OnListFragmentInteractionListener mListener;
    private StepViewModel mStepViewModel;

    /**
     * Default constructor
     */
    public StepFragment() {
    }

    public static StepFragment newInstance(int state, int recipeId) {
        StepFragment fragment = new StepFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putInt(ARG_RECIPE_ID, recipeId);
        fragment.setArguments(args);
        return fragment;
    }

    public static StepFragment newInstance(int state, List<StepEntity> list) {
        StepFragment fragment = new StepFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putParcelableArrayList(ARG_STEPS, (ArrayList<StepEntity>) list);
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
            if (getArguments().containsKey(ARG_STEPS)) {
                mStepList = getArguments().getParcelableArrayList(ARG_STEPS);
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = StepFragmentItemListBinding.inflate(inflater, container, false);

        // Set the adapter
        Context context = mBinding.getRoot().getContext();

        mBinding.stepList.setLayoutManager(new LinearLayoutManager(context));
        mStepViewModel = new ViewModelProvider(requireActivity()).get(StepViewModel.class);
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipeId = savedInstanceState.getInt(RECIPE_ID);
            mStepList = savedInstanceState.getParcelableArrayList(STEPS);
        }
        StepRecyclerViewAdapter adapter = new StepRecyclerViewAdapter(mListener, mState);
        mBinding.stepList.setAdapter(adapter);
        if (mState == CookbookActivity.STATE_MANUAL) {
            adapter.setData(mStepList);
        } else {
            mStepViewModel.getSteps(mRecipeId).observe(getViewLifecycleOwner(), newSteps -> {
                if (newSteps != null && newSteps.size() > 0) {
                    adapter.setData(newSteps);
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
        outState.putParcelableArrayList(STEPS, (ArrayList<StepEntity>) mStepList);
    }

    /**
     * Method that initializes the listener
     * @param context to use
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof StepFragment.OnListFragmentInteractionListener) {
            mListener = (StepFragment.OnListFragmentInteractionListener) context;
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
        void onListFragmentInteraction(StepEntity step, int state, View view);
    }
}