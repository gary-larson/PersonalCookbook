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
package com.larsonapps.personalcookbook.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.databinding.RecipeFragmentItemBinding;
import com.larsonapps.personalcookbook.ui.RecipeFragment;

import java.util.List;
import java.util.Locale;

/**
 * Class to display recipe list
 */
public class RecipeRecyclerViewAdapter extends
        RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder> {

    private List<RecipeEntity> mValues;
    private RecipeFragment.OnListFragmentInteractionListener mListener;

    /**
     * Constructor for listener and list of recipes
     * @param listener to set
     * @param items to set
     */
    public RecipeRecyclerViewAdapter(RecipeFragment.OnListFragmentInteractionListener listener,
                                     List<RecipeEntity> items) {
        mListener = listener;
        mValues = items;
    }

    /**
     * Method to create recipe view holder
     * @param parent of the view holder
     * @param viewType of the view holder
     * @return view holder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                RecipeFragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false));
    }

    /**
     * Method to bind recipe data to view holder
     * @param holder to use
     * @param position of the data
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // get recipe at position
        holder.mItem = mValues.get(position);
        // Set name
        holder.mNameView.setText(mValues.get(position).getName());
        // set short description
        holder.mShortDescriptionView.setText(mValues.get(position).getShortDescription());
        // format and set number of servings
        String temp = String.format(Locale.getDefault(), "Servings: %d",
                holder.mItem.getServings());
        holder.mServingsView.setText(temp);
        // format and set total time
        temp = String.format(Locale.getDefault(),"Time: %s",
                holder.mItem.getTotalTimeString());
        holder.mTotalTimeView.setText(temp);
        // set listener on view holder
        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    /**
     * Method to get number of recipes
     * @return number of recipes
     */
    @Override
    public int getItemCount() {
        if (mValues == null) {
            return 0;
        }
        return mValues.size();
    }

    /**
     * Method to set data and notify of data change
     * @param list to set
     */
    public void setData(List<RecipeEntity> list) {
        mValues = list;
        notifyDataSetChanged();
    }

    /**
     * Class for recipe view holder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mShortDescriptionView;
        public final TextView mServingsView;
        public final TextView mTotalTimeView;
        public RecipeEntity mItem;

        /**
         * Constructor for binding
         * @param binding to use
         */
        public ViewHolder(RecipeFragmentItemBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            mNameView = binding.recipeNameTextView;
            mShortDescriptionView = binding.recipeShortDescriptionView;
            mServingsView = binding.recipeServingsTextView;
            mTotalTimeView = binding.recipeTotalTimeTextView;
        }
    }
}