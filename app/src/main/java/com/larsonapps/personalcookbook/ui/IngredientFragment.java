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

import com.larsonapps.personalcookbook.adapter.IngredientRecyclerViewAdapter;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.databinding.IngredientFragmentItemListBinding;
import com.larsonapps.personalcookbook.model.IngredientViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Class for ingredient fragment
 */
public class IngredientFragment extends Fragment {
    // Declare constants
    private static final String ARG_STATE = "state";
    private static final String ARG_RECIPE_ID = "recipeId";
    private static final String ARG_INGREDIENTS = "ingredients";
    private static final String STATE = "mState";
    private static final String RECIPE_ID = "mRecipeId";
    private static final String INGREDIENTS = "mIngredientList";
    // Declare variables
    private int mState = 0;
    private int mRecipeId = 0;
    private List<IngredientEntity> mIngredientList;
    private OnListFragmentInteractionListener mListener;


    /**
     * Default constructor
     */
    public IngredientFragment() {}

    /**
     * Method to create a new instance of this fragment
     * @param state of the app
     * @param recipeId of the ingredients
     * @return the new fragment
     */
    public static IngredientFragment newInstance(int state, int recipeId) {
        IngredientFragment fragment = new IngredientFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putInt(ARG_RECIPE_ID, recipeId);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Method to create a new ingredient fragment instance
     * @param state of thepp
     * @param list of ingredients
     * @return ingredient fragment
     */
    public static IngredientFragment newInstance(int state, List<IngredientEntity> list) {
        IngredientFragment fragment = new IngredientFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putParcelableArrayList(ARG_INGREDIENTS, (ArrayList<IngredientEntity>) list);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Method to get arguments
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
            if (getArguments().containsKey(ARG_INGREDIENTS)) {
                mIngredientList = getArguments().getParcelableArrayList(ARG_INGREDIENTS);
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
        // set binding and inflate views
        com.larsonapps.personalcookbook.databinding.IngredientFragmentItemListBinding mBinding =
                IngredientFragmentItemListBinding.inflate(inflater, container, false);
        // set context
        Context context = mBinding.getRoot().getContext();
        // get ingredient view model
        IngredientViewModel mIngredientViewModel = new ViewModelProvider(requireActivity())
                .get(IngredientViewModel.class);
        // if saved instance state restore
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipeId = savedInstanceState.getInt(RECIPE_ID);
            mIngredientList = savedInstanceState.getParcelableArrayList(INGREDIENTS);
        }
        // Set the layout manager
        mBinding.ingredientList.setLayoutManager(new LinearLayoutManager(context));
        // create the adapter
        IngredientRecyclerViewAdapter adapter =
                new IngredientRecyclerViewAdapter(mListener, mState);
        // set adapter
        mBinding.ingredientList.setAdapter(adapter);
        // test state
        if (mState == CookbookActivity.STATE_MANUAL) {
            // set data
            adapter.setData(mIngredientList);
        } else {
            // set observer for live data
            mIngredientViewModel.getIngredients(mRecipeId).observe(getViewLifecycleOwner(),
                    newIngredients -> {
                //test new ingredients
                if (newIngredients != null && newIngredients.size() > 0) {
                    // set data
                    adapter.setData(newIngredients);
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
        outState.putParcelableArrayList(INGREDIENTS, (ArrayList<IngredientEntity>) mIngredientList);
    }

    /**
     * Method that initializes the listener
     * @param context to use
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
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
        void onListFragmentInteraction(IngredientEntity ingredient, int state, View view);
    }
}