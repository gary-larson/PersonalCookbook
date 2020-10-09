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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.CategoryEntity;
import com.larsonapps.personalcookbook.model.RecipeViewModel;
import com.larsonapps.personalcookbook.adapter.RecipeRecyclerViewAdapter;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.databinding.RecipeFragmentItemListBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.text.TextUtils.isEmpty;


public class RecipeFragment extends Fragment {
    // Declare variables
    private RecipeViewModel mRecipeViewModel;
    private com.larsonapps.personalcookbook.databinding.RecipeFragmentBinding mBinding;
    private RecipeFragmentItemListBinding mListBinding;
    private OnListFragmentInteractionListener mListener;
    private String mMealSpinnerSelection;
    private String mProteinSpinnerSelection;
    private String mCategorySpinnerSelection;
    private boolean isMealSpinnerInitialized = false;
    private boolean isProteinSpinnerInitialized = false;
    private boolean isCategorySpinnerInitialized = false;

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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                            R.layout.spinner_custom_item, categories);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mBinding.categorySpinner.setAdapter(adapter);
                }
            }
        });
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
                List<String> keywords = getKeywords(null);
                if (keywords.size() == 0) {
                    mRecipeViewModel.getRecipes(true)
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else if (keywords.size() == 1) {
                    mRecipeViewModel.getRecipes(true, keywords.get(0))
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else {
                    mRecipeViewModel.getRecipes(true, (String[]) keywords.toArray())
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
                List<String> keywords = getKeywords(null);
                if (keywords.size() == 0) {
                    mRecipeViewModel.getRecipes(true)
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else if (keywords.size() == 1) {
                    mRecipeViewModel.getRecipes(true, keywords.get(0))
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else {
                    mRecipeViewModel.getRecipes(true, (String[]) keywords.toArray())
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
                List<String> keywords = getKeywords(null);
                if (keywords.size() == 0) {
                    mRecipeViewModel.getRecipes(true)
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else if (keywords.size() == 1) {
                    mRecipeViewModel.getRecipes(true, keywords.get(0))
                            .observe(getViewLifecycleOwner(), recipeObserver);
                } else {
                    mRecipeViewModel.getRecipes(true, (String[]) keywords.toArray())
                            .observe(getViewLifecycleOwner(), recipeObserver);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return mBinding.getRoot();
    }

    private List<String> getKeywords(String[] strings) {
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
        return temp;
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