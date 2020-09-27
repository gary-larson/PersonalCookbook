/*
This program saves all recipes on a device with search and editing capabilities.
Copyright (C) 2020  Larson Apps - Gary Larson

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.larsonapps.personalcookbook.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.larsonapps.personalcookbook.model.RecipeViewModel;
import com.larsonapps.personalcookbook.adapter.RecipeRecyclerViewAdapter;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.databinding.RecipeFragmentItemListBinding;
import com.larsonapps.personalcookbook.ui.dummy.DummyContent;

import java.util.List;


public class RecipeFragment extends Fragment {
    // Declare variables
    private RecipeViewModel mRecipeViewModel;
    private com.larsonapps.personalcookbook.databinding.RecipeFragmentBinding mBinding;
    private RecipeFragmentItemListBinding mListBinding;
    private OnListFragmentInteractionListener mListener;
    public static RecipeFragment newInstance() {
        return new RecipeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = com.larsonapps.personalcookbook.databinding.RecipeFragmentBinding.inflate(inflater, container, false);
        mListBinding = mBinding.content;
        Context context = mListBinding.recipeList.getContext();
        // Set adapter
        mListBinding.recipeList.setLayoutManager(new LinearLayoutManager(context));
        mRecipeViewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        mBinding.shareFab.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Shared FAB Clicked", Toast.LENGTH_LONG).show();
        });
        // Create the observer which updates the UI and sets the adapter
        final Observer<List<RecipeEntity>> recipeObserver = newRecipes -> {
            if (newRecipes != null) {
                mListBinding.recipeList.setAdapter(new RecipeRecyclerViewAdapter(mListener, newRecipes));
            }
        };
        mRecipeViewModel.getRecipes(false).observe(getViewLifecycleOwner(), recipeObserver);
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
            mListener = (RecipeFragment.OnListFragmentInteractionListener) context;
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
        void onListFragmentInteraction(RecipeEntity recipe);
    }

}