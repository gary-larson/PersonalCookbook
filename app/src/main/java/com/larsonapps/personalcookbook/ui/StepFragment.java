package com.larsonapps.personalcookbook.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.larsonapps.personalcookbook.adapter.StepRecyclerViewAdapter;
import com.larsonapps.personalcookbook.data.Step;
import com.larsonapps.personalcookbook.databinding.StepFragmentItemListBinding;
import com.larsonapps.personalcookbook.ui.dummy.DummyContent;

import org.jetbrains.annotations.NotNull;

/**
 * A fragment representing a list of Items.
 */
public class StepFragment extends Fragment {

    // Declare constants
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_STATE= "state";
    private static final int STATE_EDIT= 1;
    private static final int STATE_MANUAL = 2;
    private static final int STATE_IMPORT = 3;
    // Declare variables
    private int mColumnCount = 1;
    private int mState;
    private StepFragmentItemListBinding mBinding;
    private OnListFragmentInteractionListener mListener;

    /**
     * Default constructor
     */
    public StepFragment() {
    }

    // TODO: Customize parameter initialization
    public static StepFragment newInstance(int columnCount, int state) {
        StepFragment fragment = new StepFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(ARG_STATE, state);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mState = getArguments().getInt(ARG_STATE);
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = StepFragmentItemListBinding.inflate(inflater, container, false);

        // Set the adapter
        Context context = mBinding.getRoot().getContext();
        if (mColumnCount <= 1) {
            mBinding.stepList.setLayoutManager(new LinearLayoutManager(context));
        } else {
            mBinding.stepList.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        mBinding.stepList.setAdapter(new StepRecyclerViewAdapter(mListener, DummyContent.ITEMS, mState));

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
        void onListFragmentInteraction(Step step, int state);
    }
}