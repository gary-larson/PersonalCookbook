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

import com.larsonapps.personalcookbook.adapter.StepRecyclerViewAdapter;
import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.databinding.StepFragmentItemListBinding;
import com.larsonapps.personalcookbook.model.StepViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for step fragment
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
    private OnListFragmentInteractionListener mListener;

    /**
     * Default constructor
     */
    public StepFragment() {
    }

    /**
     * Method to create step fragment instance
     * @param state of app
     * @param recipeId to use
     * @return step fragment
     */
    public static StepFragment newInstance(int state, int recipeId) {
        StepFragment fragment = new StepFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putInt(ARG_RECIPE_ID, recipeId);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Method to create step fragment instance
     * @param state of the app
     * @param list of steps
     * @return step fragment
     */
    public static StepFragment newInstance(int state, List<StepEntity> list) {
        StepFragment fragment = new StepFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putParcelableArrayList(ARG_STEPS, (ArrayList<StepEntity>) list);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Method to get arguments
     * @param savedInstanceState of the fragment state
     */
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

    /**
     * Method to create step fragment views
     * @param inflater for the views
     * @param container of the views
     * @param savedInstanceState of the fragment
     * @return step fragment views
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // set binding and inflate step fragment views
        com.larsonapps.personalcookbook.databinding.StepFragmentItemListBinding mBinding =
                StepFragmentItemListBinding.inflate(inflater, container, false);
        // Set context
        Context context = mBinding.getRoot().getContext();
        // set layout manager
        mBinding.stepList.setLayoutManager(new LinearLayoutManager(context));
        // get step view model
        StepViewModel mStepViewModel = new ViewModelProvider(requireActivity())
                .get(StepViewModel.class);
        // if saved instance state restore
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipeId = savedInstanceState.getInt(RECIPE_ID);
            mStepList = savedInstanceState.getParcelableArrayList(STEPS);
        }
        // create adapter
        StepRecyclerViewAdapter adapter = new StepRecyclerViewAdapter(mListener, mState);
        // set adapter
        mBinding.stepList.setAdapter(adapter);
        // test state
        if (mState == CookbookActivity.STATE_MANUAL) {
            // set data
            adapter.setData(mStepList);
        } else {
            // set observer for live data
            mStepViewModel.getSteps(mRecipeId).observe(getViewLifecycleOwner(), newSteps -> {
                // test new steps
                if (newSteps != null && newSteps.size() > 0) {
                    // set data
                    adapter.setData(newSteps);
                }
            });
        }
        return mBinding.getRoot();
    }

    /**
     * Method to save instance state
     * @param outState to save state in
     */
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