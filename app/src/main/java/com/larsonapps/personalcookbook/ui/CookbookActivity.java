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

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.CookbookRecipeViewModel;
import com.larsonapps.personalcookbook.data.ImageEntity;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.databinding.CookbookActivityBinding;

public class CookbookActivity extends AppCompatActivity implements
        RecipeFragment.OnListFragmentInteractionListener,
        StepFragment.OnListFragmentInteractionListener,
        IngredientFragment.OnListFragmentInteractionListener,
        ImageFragment.OnListFragmentInteractionListener,
        FragmentManager.OnBackStackChangedListener {
    // Declare constants
    public static final int STATE_DISPLAY = 0;
    public static final int STATE_EDIT= 1;
    public static final int STATE_MANUAL = 2;
    public static final int STATE_IMPORT = 3;
    // Declare variables
    private CookbookRecipeViewModel mCookbookRecipeViewModel;
    private CookbookActivityBinding mBinding;
    private int mHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = CookbookActivityBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(mBinding.container.getId(), RecipeFragment.newInstance())
                    //.replace(mBinding.container.getId(), CookbookDetailsFragment.newInstance())
                    //.replace(mBinding.container.getId(), CookbookEditFragment.newInstance())
                    //.replace(mBinding.container.getId(), CookbookManualFragment.newInstance())
                    //.replace(mBinding.container.getId(), CookbookImportFragment.newInstance())
                    .commitNow();
        }

        int height;
        if (android.os.Build.VERSION.SDK_INT >= 30){
            height = getWindowManager().getMaximumWindowMetrics().getBounds().height();
        } else{
            height = getWindowManager().getDefaultDisplay().getHeight();
        }
        mHeight = height;
    }

    public int getHeight() {return mHeight;}

    @Override
    public void onListFragmentInteraction(RecipeEntity recipe) {
        Toast.makeText(this, "Recipe clicked", Toast.LENGTH_LONG).show();
}

    @Override
    public void onListFragmentInteraction(IngredientEntity ingredient, int state, View view) {
        if (view.getId() == R.id.ingredient_edit_image_button) {
            Toast.makeText(this, "Ingredient edit clicked", Toast.LENGTH_LONG).show();
        } else if (view.getId() == R.id.ingredient_delete_image_button) {
            Toast.makeText(this, "Ingredient delete clicked", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Ingredient clicked", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onListFragmentInteraction(StepEntity step, int state, View view) {
        if (view.getId() == R.id.step_edit_image_button) {
            Toast.makeText(this, "Step edit clicked", Toast.LENGTH_LONG).show();
        } else if (view.getId() == R.id.step_delete_image_button) {
            Toast.makeText(this, "Step delete clicked", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Step clicked", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onListFragmentInteraction(ImageEntity image, int state, View view) {
        if (view.getId() == R.id.image_button) {
            Toast.makeText(this, "Image delete clicked", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Image clicked", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackStackChanged() {
        getSupportFragmentManager().popBackStack();
    }
}