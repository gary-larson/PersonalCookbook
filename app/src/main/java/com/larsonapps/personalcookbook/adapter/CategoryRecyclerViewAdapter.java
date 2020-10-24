package com.larsonapps.personalcookbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.larsonapps.personalcookbook.data.CategoryEntity;
import com.larsonapps.personalcookbook.databinding.CategoryFragmentItemBinding;
import com.larsonapps.personalcookbook.ui.CategoryFragment;

import java.util.List;

/**
 * Class to display categories
 */
public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {
    // Declare variables
    private List<CategoryEntity> mValues;
    private CategoryFragment.OnListFragmentInteractionListener mListener;

    public CategoryRecyclerViewAdapter(CategoryFragment.OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(CategoryFragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mCategoryView.setText(mValues.get(position).getCategoryName());
        holder.mDeleteImageButton.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mItem, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mValues == null) {
            return 0;
        }
        return mValues.size();
    }

    /**
     * Method to set data and notify adapter
     * @param categories to set
     */
    public void setData(List<CategoryEntity> categories) {
        mValues = categories;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mCategoryView;
        public final ImageView mDeleteImageButton;
        public CategoryEntity mItem;

        public ViewHolder(CategoryFragmentItemBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            mCategoryView = binding.categoryTextView;
            mDeleteImageButton = binding.categoryDeleteImageButton;
        }


    }
}