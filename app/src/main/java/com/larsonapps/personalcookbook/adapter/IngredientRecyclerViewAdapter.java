package com.larsonapps.personalcookbook.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.larsonapps.personalcookbook.data.Ingredient;
import com.larsonapps.personalcookbook.databinding.IngredientFragmentItemBinding;
import com.larsonapps.personalcookbook.ui.CookbookActivity;
import com.larsonapps.personalcookbook.ui.IngredientFragment;
import com.larsonapps.personalcookbook.ui.dummy.DummyContent.DummyItem;


import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class IngredientRecyclerViewAdapter extends RecyclerView.Adapter<IngredientRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final int mState;
    private final IngredientFragment.OnListFragmentInteractionListener mListener;

    public IngredientRecyclerViewAdapter(IngredientFragment.OnListFragmentInteractionListener listener, List<DummyItem> items, int state) {
        mListener = listener;
        mValues = items;
        mState = state;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(IngredientFragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
        if (mState == CookbookActivity.STATE_EDIT) {
            holder.mEditImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(new Ingredient(), mState, v);
                }
            });
            holder.mDeleteImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(new Ingredient(), mState, v);
                }
            });
            holder.mEditImageButton.setVisibility(View.VISIBLE);
            holder.mDeleteImageButton.setVisibility(View.VISIBLE);
            holder.mCheckbox.setVisibility(View.GONE);
        } else if (mState == CookbookActivity.STATE_IMPORT) {
            holder.mEditImageButton.setVisibility(View.GONE);
            holder.mDeleteImageButton.setVisibility(View.GONE);
            holder.mCheckbox.setVisibility(View.VISIBLE);
        } else {
            holder.mEditImageButton.setVisibility(View.GONE);
            holder.mDeleteImageButton.setVisibility(View.GONE);
            holder.mCheckbox.setVisibility(View.GONE);
        }
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
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView mEditImageButton;
        public final ImageView mDeleteImageButton;
        public final CheckBox mCheckbox;
        public DummyItem mItem;

        public ViewHolder(IngredientFragmentItemBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            mEditImageButton = binding.ingredientEditImageButton;
            mDeleteImageButton = binding.ingredientDeleteImageButton;
            mCheckbox = binding.ingredientCheckbox;
        }

        @NotNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}