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
import androidx.recyclerview.widget.RecyclerView;

import com.larsonapps.personalcookbook.adapter.CategoryRecyclerViewAdapter;
import com.larsonapps.personalcookbook.data.CategoryEntity;
import com.larsonapps.personalcookbook.databinding.CategoryFragmentItemListBinding;
import com.larsonapps.personalcookbook.model.RecipeViewModel;

/**
 * A fragment representing a list of Items.
 */
public class CategoryFragment extends Fragment {
    // declare variables
    CategoryFragmentItemListBinding mBinding;
    RecipeViewModel mRecipeViewModel;
    OnListFragmentInteractionListener mListener;

    /**
     * Default constructor
     */
    public CategoryFragment() {}

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = CategoryFragmentItemListBinding.inflate(inflater, container, false);
        mRecipeViewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        // Set the adapter
        Context context = mBinding.getRoot().getContext();
        RecyclerView recyclerView = mBinding.categoryList;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(mListener);
        recyclerView.setAdapter(adapter);
        mRecipeViewModel.getCategories().observe(getViewLifecycleOwner(), newCategories -> {
            if (newCategories != null) {
                adapter.setData(newCategories);
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
        if (context instanceof CategoryFragment.OnListFragmentInteractionListener) {
            mListener = (CategoryFragment.OnListFragmentInteractionListener) context;
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
        void onListFragmentInteraction(CategoryEntity category, View view);
    }
}