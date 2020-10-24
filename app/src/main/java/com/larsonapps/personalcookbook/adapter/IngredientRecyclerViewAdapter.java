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

    private List<IngredientEntity> mValues = new ArrayList<>();
    private final int mState;
    private final IngredientFragment.OnListFragmentInteractionListener mListener;

    public IngredientRecyclerViewAdapter(
            IngredientFragment.OnListFragmentInteractionListener listener, int state) {
        mListener = listener;
        mState = state;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(IngredientFragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
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
        holder.mTextView.setText(tempString);
        if (holder.mItem.getPreparation() == null || holder.mItem.getPreparation().isEmpty()) {
            holder.mPreparationView.setVisibility(View.GONE);
        } else {
            holder.mPreparationView.setVisibility(View.VISIBLE);
            holder.mPreparationView.setText(holder.mItem.getPreparation());
        }
        if (holder.mItem.getPersonalNote() == null || holder.mItem.getPersonalNote().isEmpty()) {
            holder.mPersonalNoteView.setVisibility(View.GONE);
        } else {
            holder.mPersonalNoteView.setVisibility(View.VISIBLE);
            holder.mPersonalNoteView.setText(holder.mItem.getPersonalNote());
        }
        if (mState == CookbookActivity.STATE_EDIT) {
            holder.mEditImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem, mState, v);
                }
            });
            holder.mDeleteImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem, mState, v);
                }
            });
            holder.mEditImageButton.setVisibility(View.VISIBLE);
            holder.mDeleteImageButton.setVisibility(View.VISIBLE);
        } else {
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

    @Override
    public int getItemCount() {
        if (mValues == null) {
            return 0;
        }
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTextView;
        public final TextView mPreparationView;
        public final TextView mPersonalNoteView;
        public final ImageView mEditImageButton;
        public final ImageView mDeleteImageButton;
        public IngredientEntity mItem;

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