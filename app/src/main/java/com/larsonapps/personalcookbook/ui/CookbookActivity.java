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

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toast;


import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.Ingredient;
import com.larsonapps.personalcookbook.data.Recipe;
import com.larsonapps.personalcookbook.data.RecipeImage;
import com.larsonapps.personalcookbook.data.Step;
import com.larsonapps.personalcookbook.data.CookbookRecipesViewModel;
import com.larsonapps.personalcookbook.databinding.CookbookActivityBinding;

public class CookbookActivity extends AppCompatActivity implements
        CookbookFragment.OnListFragmentInteractionListener,
        StepFragment.OnListFragmentInteractionListener,
        IngredientFragment.OnListFragmentInteractionListener,
        ImageFragment.OnListFragmentInteractionListener {
    // Declare variables
    private CookbookRecipesViewModel mCookbookRecipesViewModel;
    private CookbookActivityBinding mBinding;
    private int mHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = CookbookActivityBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    //.replace(mBinding.container.getId(), CookbookFragment.newInstance())
                    //.replace(mBinding.container.getId(), CookbookDetailsFragment.newInstance())
                    //.replace(mBinding.container.getId(), CookbookEditFragment.newInstance())
                    .replace(mBinding.container.getId(), CookbookManualFragment.newInstance())
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
    public void onListFragmentInteraction(Recipe recipe) {
        Toast.makeText(this, "Recipe clicked", Toast.LENGTH_LONG).show();
}

    @Override
    public void onListFragmentInteraction(Ingredient ingredient, int state) {
        Toast.makeText(this, "Ingredient clicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onListFragmentInteraction(Step step, int state) {
        Toast.makeText(this, "Step clicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onListFragmentInteraction(RecipeImage recipeImage, int state) {
        Toast.makeText(this, "Image clicked", Toast.LENGTH_LONG).show();
    }
}