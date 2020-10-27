package com.larsonapps.personalcookbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.databinding.IngredientFragmentItemBinding;
import com.larsonapps.personalcookbook.ui.CookbookActivity;
import com.larsonapps.personalcookbook.ui.IngredientFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to display ingredients list
 */
public class IngredientRecyclerViewAdapter extends
        RecyclerView.Adapter<IngredientRecyclerViewAdapter.ViewHolder> {
    // Declare variables
    private List<IngredientEntity> mValues = new ArrayList<>();
    private final int mState;
    private final IngredientFragment.OnListFragmentInteractionListener mListener;

    /**
     * Constructor for listener and state
     * @param listener to set
     * @param state to set
     */
    public IngredientRecyclerViewAdapter(
            IngredientFragment.OnListFragmentInteractionListener listener, int state) {
        mListener = listener;
        mState = state;
    }

    /**
     * Method to creat view holder for ingredient adaptor
     * @param parent of the view holder
     * @param viewType of the view holder
     * @return view holder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(IngredientFragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    /**
     * Method to bind data to the view holder
     * @param holder to bind data for
     * @param position of the data
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // get ingredient for this position
        holder.mItem = mValues.get(position);
        // build string for ingredient
        String tempString;
        if (holder.mItem.getAmount() == null || holder.mItem.getAmount().isEmpty()) {
            if (holder.mItem.getMeasure() == null || holder.mItem.getMeasure().isEmpty()) {
                tempString = holder.mItem.getName();
            } else {
                tempString = holder.mItem.getMeasure() + " " + holder.mItem.getName();
            }
        } else if (holder.mItem.getMeasure() == null || holder.mItem.getMeasure().isEmpty()) {
            tempString = holder.mItem.getAmount() + holder.mItem.getName();
        } else {
            tempString = holder.mItem.getAmount() + " " + holder.mItem.getMeasure() + " " +
                    holder.mItem.getName();
        }
        // set ingredient text
        holder.mTextView.setText(tempString);
        // test if ingredient preparation exists
        if (holder.mItem.getPreparation() == null || holder.mItem.getPreparation().isEmpty()) {
            // set visibility
            holder.mPreparationView.setVisibility(View.GONE);
        } else {
            // set visibility and preparation text
            holder.mPreparationView.setVisibility(View.VISIBLE);
            holder.mPreparationView.setText(holder.mItem.getPreparation());
        }
        // test if personal note exists
        if (holder.mItem.getPersonalNote() == null || holder.mItem.getPersonalNote().isEmpty()) {
            // set visibility
            holder.mPersonalNoteView.setVisibility(View.GONE);
        } else {
            // set visibility and personal note text
            holder.mPersonalNoteView.setVisibility(View.VISIBLE);
            holder.mPersonalNoteView.setText(holder.mItem.getPersonalNote());
        }
        // test state
        if (mState == CookbookActivity.STATE_EDIT) {
            // set edit icon listener
            holder.mEditImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem, mState, v);
                }
            });
            // set delete icon listener
            holder.mDeleteImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem, mState, v);
                }
            });
            // set visibility
            holder.mEditImageButton.setVisibility(View.VISIBLE);
            holder.mDeleteImageButton.setVisibility(View.VISIBLE);
        } else {
            // set visibility
            holder.mEditImageButton.setVisibility(View.GONE);
            holder.mDeleteImageButton.setVisibility(View.GONE);
        }
    }

    /**
     * Method to set data and notify adapter
     * @param ingredients to set
     */
    public void setData(List<IngredientEntity> ingredients) {
        mValues = ingredients;
        notifyDataSetChanged();
    }

    /**
     * Method to set number of ingredients
     * @return number of ingredients
     */
    @Override
    public int getItemCount() {
        if (mValues == null) {
            return 0;
        }
        return mValues.size();
    }

    /**
     * Class for ingredients view holder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTextView;
        public final TextView mPreparationView;
        public final TextView mPersonalNoteView;
        public final ImageView mEditImageButton;
        public final ImageView mDeleteImageButton;
        public IngredientEntity mItem;

        /**
         * Constructor for ingredient binding
         * @param binding to use
         */
        public ViewHolder(IngredientFragmentItemBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            mTextView = binding.ingredientTextView;
            mPreparationView = binding.ingredientPreparationTextView;
            mPersonalNoteView = binding.ingredientPersonalNoteTextView;
            mEditImageButton = binding.ingredientEditImageButton;
            mDeleteImageButton = binding.ingredientDeleteImageButton;
        }
    }
}