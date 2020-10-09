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

/**
 * A fragment representing a list of Items.
 */
public class StepFragment extends Fragment {

    // Declare constants
    private static final String ARG_STATE= "state";
    private static final String ARG_RECIPE_ID = "recipeId";
    // Declare variables
    private int mState;
    private int mRecipeId;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mState = getArguments().getInt(ARG_STATE);
            mRecipeId = getArguments().getInt(ARG_RECIPE_ID);
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
        StepRecyclerViewAdapter adapter = new StepRecyclerViewAdapter(mListener, mState);
        mBinding.stepList.setAdapter(adapter);
        mStepViewModel.getSteps(mRecipeId).observe(getViewLifecycleOwner(), newSteps -> {
            if (newSteps != null && newSteps.size() > 0) {
                adapter.setData(newSteps);
            }
        });

        return mBinding.getRoot();
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