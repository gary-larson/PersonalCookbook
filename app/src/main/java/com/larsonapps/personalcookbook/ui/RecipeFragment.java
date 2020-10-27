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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.adapter.RecipeRecyclerViewAdapter;
import com.larsonapps.personalcookbook.data.CategoryEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.databinding.RecipeFragmentItemListBinding;
import com.larsonapps.personalcookbook.model.RecipeViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.text.TextUtils.isEmpty;

/**
 * Class for recipe fragment
 */
public class RecipeFragment extends Fragment {
    // Declare constants
    private static final String RECIPES = "mRecipes";
    // Declare variables
    private RecipeViewModel mRecipeViewModel;
    private RecipeFragmentItemListBinding mBinding;
    private OnListFragmentInteractionListener mListener;
    private String mMealSpinnerSelection;
    private String mProteinSpinnerSelection;
    private String mCategorySpinnerSelection;
    private boolean isMealSpinnerInitialized = false;
    private boolean isProteinSpinnerInitialized = false;
    private boolean isCategorySpinnerInitialized = false;
    private List<String> mCategories;
    private List<RecipeEntity> mRecipes;

    /**
     * Method to create a new instance of recipe fragment
     * @return recipe fragment
     */
    public static RecipeFragment newInstance() {
        return new RecipeFragment();
    }

    /**
     * Method to create recipe fragment views
     * @param inflater for the views
     * @param container of the views
     * @param savedInstanceState of the fragment state
     * @return recipe fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // get binding and inflate views
        mBinding = RecipeFragmentItemListBinding.inflate(inflater, container, false);
        // set context
        Context context = getContext();
        // get recipe view model
        mRecipeViewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        // set title
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity()))
                .getSupportActionBar()).setTitle(getString(R.string.app_name));
        // if saved instance state restore
        if (savedInstanceState != null) {
            mRecipes = savedInstanceState.getParcelableArrayList(RECIPES);
        }
        // Set layout manager
        mBinding.recipeList.setLayoutManager(new LinearLayoutManager(context));
        // create adapter
        RecipeRecyclerViewAdapter adapter = new RecipeRecyclerViewAdapter(mListener, mRecipes);
        // set adapter
        mBinding.recipeList.setAdapter(adapter);
        // Create the observer for live data
        final Observer<List<RecipeEntity>> recipeObserver = newRecipes -> {
            // test new recipes
            if (newRecipes != null) {
                // set data
                adapter.setData(newRecipes);
            }
        };
        // get recipes and call observer
        mRecipeViewModel.getRecipes(false).observe(getViewLifecycleOwner(),
                recipeObserver);
        // set observer for live data
        mRecipeViewModel.getCategories().observe(getViewLifecycleOwner(), newCategories -> {
            // test categories
            if (newCategories != null) {
                // create categories list
                mCategories = new ArrayList<>();
                // add default category entry
                mCategories.add("Category");
                // loop through categories
                for (CategoryEntity category : newCategories) {
                    // test category name
                    if (!isEmpty(category.getCategoryName())) {
                        // add category name to category list
                        mCategories.add(category.getCategoryName());
                    }
                }
                // test activity
                if (getActivity() != null) {
                    // create spinner adapter for category spinner
                    ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<>(getActivity(),
                            R.layout.spinner_custom_item, mCategories);
                    // set spinner drop down layout
                    spinnerAdapter2.setDropDownViewResource(
                            android.R.layout.simple_spinner_dropdown_item);
                    // set adapter
                    mBinding.categorySpinner.setAdapter(spinnerAdapter2);
                }
            }
        });
        // test activity
        if (getActivity() != null) {
            // create spinner adapter for meal spinner
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(),
                    R.layout.spinner_custom_item, getResources()
                    .getStringArray(R.array.meal_array));
            // set dropdown layout
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // set adapter
            mBinding.mealSpinner.setAdapter(spinnerAdapter);
            // create spinner adapter for protein spinner
            ArrayAdapter<String> spinnerAdapter1 = new ArrayAdapter<>(getActivity(),
                    R.layout.spinner_custom_item, getResources()
                    .getStringArray(R.array.protein_array));
            // set drop down layout
            spinnerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // set adapter
            mBinding.proteinSpinner.setAdapter(spinnerAdapter1);
            // create spinner adapter for category spinner
            ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<>(getActivity(),
                    R.layout.spinner_custom_item, getResources()
                    .getStringArray(R.array.category_array));
            // set drop down layout
            spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // set adapter
            mBinding.categorySpinner.setAdapter(spinnerAdapter2);
        }
        // set listener for meal spinner
        mBinding.mealSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // test meal spinner initialization
                if (!isMealSpinnerInitialized) {
                    // set meal spinner initialized
                    isMealSpinnerInitialized = true;
                    return;
                }
                // test position
                if (position == 0) {
                    // ignore position 0
                    mMealSpinnerSelection = "";
                } else {
                    // get meal spinner array
                    String[] temp = getResources().getStringArray(R.array.meal_array);
                    // get selected item
                    mMealSpinnerSelection = temp[position];
                }
                // get keywords
                String[] keywords = getKeywords();
                // test keywords
                if (keywords.length == 0) {
                    // call get recipes no keywords to observer
                    mRecipeViewModel.getRecipes(true)
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else if (keywords.length == 1) {
                    // call get recipes one keyword to observer
                    mRecipeViewModel.getRecipes(true, keywords[0])
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else {
                    // call get recipes 2+ keywords to observer
                    mRecipeViewModel.getRecipes(true, keywords)
                            .observe(getViewLifecycleOwner(), recipeObserver);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // set listener for protein spinner
        mBinding.proteinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // test protein spinner initialized
                if (!isProteinSpinnerInitialized) {
                    // set protein spinner initialized
                    isProteinSpinnerInitialized = true;
                    return;
                }
                if (position == 0) {
                    // if position is 0 ignore
                    mProteinSpinnerSelection = "";
                } else {
                    // get protein items array
                    String[] temp = getResources().getStringArray(R.array.protein_array);
                    // get selected
                    mProteinSpinnerSelection = temp[position];
                }
                // get keywords
                String[] keywords = getKeywords();
                // test keywords
                if (keywords.length == 0) {
                    // get recipes 0 keywords to observer
                    mRecipeViewModel.getRecipes(true)
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else if (keywords.length == 1) {
                    // get recipes 1 keyword to observer
                    mRecipeViewModel.getRecipes(true, keywords[0])
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else {
                    // get recipes 2+ keywords to observer
                    mRecipeViewModel.getRecipes(true, keywords)
                            .observe(getViewLifecycleOwner(), recipeObserver);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // set listener for category spinner
        mBinding.categorySpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // test category spinner initialized
                if (!isCategorySpinnerInitialized) {
                    // set category spinner initialized
                    isCategorySpinnerInitialized = true;
                    return;
                }
                // test position
                if (position == 0) {
                    // if position 0 ignore
                    mCategorySpinnerSelection = "";
                } else {
                    // get category spinner selection at position
                    mCategorySpinnerSelection = mCategories.get(position);
                }
                // get keywords
                String[] keywords = getKeywords();
                // test keywords
                if (keywords.length == 0) {
                    // get recipes 0 keywords to observer
                    mRecipeViewModel.getRecipes(true)
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else if (keywords.length == 1) {
                    //v get recipes 1 keyword to observer
                    mRecipeViewModel.getRecipes(true, keywords[0])
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else {
                    // get recipes 2+ keywords to observer
                    mRecipeViewModel.getRecipes(true, keywords)
                            .observe(getViewLifecycleOwner(), recipeObserver);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return mBinding.getRoot();
    }

    /**
     * Method to save instance state
     * @param outState to save state in
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RECIPES, (ArrayList<RecipeEntity>) mRecipes);
    }

    /**
     * Method to get keywords
     * @return string array of keywords
     */
    private String[] getKeywords() {
        // create list of strings
        List<String> temp = new ArrayList<>();
        // test meal spinner selection
        if (!isEmpty(mMealSpinnerSelection)) {
            // add meal selection
            temp.add(mMealSpinnerSelection);
        }
        // test protein spinner selection
        if(!isEmpty(mCategorySpinnerSelection)) {
            // add protein selection
            temp.add(mCategorySpinnerSelection);
        }
        // test category spinner selection
        if(!isEmpty(mProteinSpinnerSelection)) {
            // add category selection
            temp.add(mProteinSpinnerSelection);
        }
        // create string array
        String[] strings1 = new String[temp.size()];
        // loop through string list
        for (int i = 0; i < temp.size(); i++) {
            // add items to string array
            strings1[i] = temp.get(i);
        }
        return strings1;
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