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

    public RecipeRecyclerViewAdapter(RecipeFragment.OnListFragmentInteractionListener listener,
                                     List<RecipeEntity> items) {
        mListener = listener;
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                RecipeFragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).getName());
        holder.mShortDescriptionView.setText(mValues.get(position).getShortDescription());
        String temp = String.format(Locale.getDefault(), "Servings: %d",
                holder.mItem.getServings());
        holder.mServingsView.setText(temp);
        temp = String.format(Locale.getDefault(),"Time: %s",
                holder.mItem.getTotalTimeString());
        holder.mTotalTimeView.setText(temp);
        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mValues == null) {
            return 0;
        }
        return mValues.size();
    }

    public void setData(List<RecipeEntity> list) {
        mValues = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mShortDescriptionView;
        public final TextView mServingsView;
        public final TextView mTotalTimeView;
        public RecipeEntity mItem;

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