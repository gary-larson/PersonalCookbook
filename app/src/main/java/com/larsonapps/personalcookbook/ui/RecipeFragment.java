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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.CategoryEntity;
import com.larsonapps.personalcookbook.databinding.RecipeFragmentItemListBinding;
import com.larsonapps.personalcookbook.model.RecipeViewModel;
import com.larsonapps.personalcookbook.adapter.RecipeRecyclerViewAdapter;
import com.larsonapps.personalcookbook.data.RecipeEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static android.text.TextUtils.isEmpty;


public class RecipeFragment extends Fragment {
    // Declare constants
    private static final String RECIPES = "mRecipes";
    private static final String TAG = RecipeFragment.class.getSimpleName();
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
    private List<RecipeEntity> mRecipes;

    /**
     * Method to create a new instance of recipe fragment
     * @return recipe fragment
     */
    public static RecipeFragment newInstance() {
        return new RecipeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = RecipeFragmentItemListBinding.inflate(inflater, container, false);
        Log.i(TAG, "onCreatView entered");
        Context context = getContext();
        mRecipeViewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity()))
                .getSupportActionBar()).setTitle(getString(R.string.app_name));
        if (savedInstanceState != null) {
            mRecipes = savedInstanceState.getParcelableArrayList(RECIPES);
        }
        // Set adapter
        mBinding.recipeList.setLayoutManager(new LinearLayoutManager(context));
        RecipeRecyclerViewAdapter adapter = new RecipeRecyclerViewAdapter(mListener, mRecipes);
        mBinding.recipeList.setAdapter(adapter);
        mBinding.shareFab.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Shared FAB Clicked", Toast.LENGTH_LONG).show();

        });
        // Create the observer which updates the UI and sets the adapter
        final Observer<List<RecipeEntity>> recipeObserver = newRecipes -> {
            if (newRecipes != null) {
                adapter.setData(newRecipes);
            }
        };
        mRecipeViewModel.getRecipes(false).observe(getViewLifecycleOwner(), recipeObserver);
        mRecipeViewModel.getCategories().observe(getViewLifecycleOwner(), newCategories -> {
            if (newCategories != null) {
                List<String> categories = new ArrayList<>();
                categories.add("Category");
                categories.add("Favorite");
                for (CategoryEntity category : newCategories) {
                    if (!isEmpty(category.getCategoryName())) {
                        categories.add(category.getCategoryName());
                    }
                }
                if (getActivity() != null) {
                    ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<>(getActivity(),
                            R.layout.spinner_custom_item, categories);
                    spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mBinding.categorySpinner.setAdapter(spinnerAdapter2);
                }
            }
        });
        if (getActivity() != null) {
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(),
                    R.layout.spinner_custom_item, getResources().getStringArray(R.array.meal_array));
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mBinding.mealSpinner.setAdapter(spinnerAdapter);
            ArrayAdapter<String> spinnerAdapter1 = new ArrayAdapter<>(getActivity(),
                    R.layout.spinner_custom_item, getResources().getStringArray(R.array.protein_array));
            spinnerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mBinding.proteinSpinner.setAdapter(spinnerAdapter1);
            ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<>(getActivity(),
                    R.layout.spinner_custom_item, getResources().getStringArray(R.array.category_array));
            spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mBinding.categorySpinner.setAdapter(spinnerAdapter2);
        }
        mBinding.mealSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isMealSpinnerInitialized) {
                    isMealSpinnerInitialized = true;
                    return;
                }
                if (position == 0) {
                    mMealSpinnerSelection = "";
                } else {
                    String[] temp = getResources().getStringArray(R.array.meal_array);
                    mMealSpinnerSelection = temp[position];
                }
                String[] keywords = getKeywords(null);
                if (keywords.length == 0) {
                    mRecipeViewModel.getRecipes(true)
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else if (keywords.length == 1) {
                    mRecipeViewModel.getRecipes(true, keywords[0])
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else {
                    mRecipeViewModel.getRecipes(true, keywords)
                            .observe(getViewLifecycleOwner(), recipeObserver);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mBinding.proteinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isProteinSpinnerInitialized) {
                    isProteinSpinnerInitialized = true;
                    return;
                }
                if (position == 0) {
                    mProteinSpinnerSelection = "";
                } else {
                    String[] temp = getResources().getStringArray(R.array.meal_array);
                    mProteinSpinnerSelection = temp[position];
                }
                String[] keywords = getKeywords(null);
                if (keywords.length == 0) {
                    mRecipeViewModel.getRecipes(true)
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else if (keywords.length == 1) {
                    mRecipeViewModel.getRecipes(true, keywords[0])
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else {
                    mRecipeViewModel.getRecipes(true, keywords)
                            .observe(getViewLifecycleOwner(), recipeObserver);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mBinding.categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isCategorySpinnerInitialized) {
                    isCategorySpinnerInitialized = true;
                    return;
                }
                if (position == 0) {
                    mCategorySpinnerSelection = "";
                } else {
                    String[] temp = getResources().getStringArray(R.array.meal_array);
                    mCategorySpinnerSelection = temp[position];
                }
                String[] keywords = getKeywords(null);
                if (keywords.length == 0) {
                    mRecipeViewModel.getRecipes(true)
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else if (keywords.length == 1) {
                    mRecipeViewModel.getRecipes(true, keywords[0])
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else {
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RECIPES, (ArrayList<RecipeEntity>) mRecipes);
    }

    private String[] getKeywords(String[] strings) {
        List<String> temp = new ArrayList<>();
        if (strings != null && strings.length > 0) {
            temp.addAll(Arrays.asList(strings));
        }
        if (!isEmpty(mMealSpinnerSelection)) {
            temp.add(mMealSpinnerSelection);
        }
        if(!isEmpty(mCategorySpinnerSelection)) {
            temp.add(mCategorySpinnerSelection);
        }
        if(!isEmpty(mProteinSpinnerSelection)) {
            temp.add(mProteinSpinnerSelection);
        }
        String[] strings1 = new String[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
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