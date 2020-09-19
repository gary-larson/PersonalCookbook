package com.larsonapps.personalcookbook.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.Ingredient;
import com.larsonapps.personalcookbook.databinding.IngredientFragmentItemBinding;
import com.larsonapps.personalcookbook.ui.IngredientFragment;
import com.larsonapps.personalcookbook.ui.dummy.DummyContent.DummyItem;


import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class IngredientRecyclerViewAdapter extends RecyclerView.Adapter<IngredientRecyclerViewAdapter.ViewHolder> {
    // Declare Constants
    private static final int STATE_EDIT= 1;
    private static final int STATE_MANUAL = 2;
    private static final int STATE_IMPORT = 3;

    private final List<DummyItem> mValues;
    private final int mState;
    private static IngredientFragmentItemBinding mBinding;
    private final IngredientFragment.OnListFragmentInteractionListener mListener;

    public IngredientRecyclerViewAdapter(IngredientFragment.OnListFragmentInteractionListener listener, List<DummyItem> items, int state) {
        mListener = listener;
        mValues = items;
        mState = state;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        mBinding = IngredientFragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent, false);
        return new ViewHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
        if (mState == 0) {
            holder.mView.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(new Ingredient(), mState);
                }
            });
            mBinding.ingredientImageButton.setVisibility(View.GONE);
        } else if (mState == 1) {
            holder.mImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(new Ingredient(), mState);
                }
            });
            mBinding.ingredientImageButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView mImageButton;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = mBinding.itemNumber;
            mContentView = mBinding.content;
            mImageButton = mBinding.ingredientImageButton;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}