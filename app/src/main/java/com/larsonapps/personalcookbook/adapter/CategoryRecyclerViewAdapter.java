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

    /**
     * Constructor to set listener
     * @param listener to set
     */
    public CategoryRecyclerViewAdapter(CategoryFragment.OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    /**
     * Method to create a view holder for categories
     * @param parent view
     * @param viewType of the view holder
     * @return view holder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(CategoryFragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    /**
     * Method to bind data to the view holder
     * @param holder to bind data to
     * @param position of the data to bind
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // get item for this position
        holder.mItem = mValues.get(position);
        // set category name
        holder.mCategoryView.setText(mValues.get(position).getCategoryName());
        //set listener for the delete icon
        holder.mDeleteImageButton.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mItem, v);
            }
        });
    }

    /**
     * Method to get a count of categories
     * @return count
     */
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

    /**
     * Class for categorie adapter view holder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mCategoryView;
        public final ImageView mDeleteImageButton;
        public CategoryEntity mItem;

        /**
         * Constructor to bring in binding
         * @param binding to use
         */
        public ViewHolder(CategoryFragmentItemBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            mCategoryView = binding.categoryTextView;
            mDeleteImageButton = binding.categoryDeleteImageButton;
        }


    }
}