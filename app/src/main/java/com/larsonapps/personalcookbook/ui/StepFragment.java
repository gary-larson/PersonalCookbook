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

import com.larsonapps.personalcookbook.adapter.MyStepRecyclerViewAdapter;
import com.larsonapps.personalcookbook.data.Ingredient;
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
    private static final String ARG_IS_EDIT= "IsEdit";
    private static final String ARG_IS_MANUAL = "IsManual";
    // Declare variables
    private int mColumnCount = 1;
    private boolean isEdit;
    private boolean isManual;
    private StepFragmentItemListBinding mBinding;
    private OnListFragmentInteractionListener mListener;

    /**
     * Default constructor
     */
    public StepFragment() {
    }

    // TODO: Customize parameter initialization
    public static StepFragment newInstance(int columnCount, boolean isEdit, boolean isManual) {
        StepFragment fragment = new StepFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putBoolean(ARG_IS_EDIT, isEdit);
        args.putBoolean(ARG_IS_MANUAL, isManual);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            isEdit = getArguments().getBoolean(ARG_IS_EDIT);
            isManual = getArguments().getBoolean(ARG_IS_MANUAL);
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
        mBinding.stepList.setAdapter(new MyStepRecyclerViewAdapter(mListener, DummyContent.ITEMS));

        return mBinding.getRoot();
    }

    /**
     * Method that initializes the listener
     * @param context to use
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IngredientFragment.OnListFragmentInteractionListener) {
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
        void onListFragmentInteraction(Step step);
    }
}