package com.larsonapps.personalcookbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
        holder.mIngredientView.setText(mValues.get(position).toString());
        if (mState == CookbookActivity.STATE_EDIT) {
            holder.mEditImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(new IngredientEntity(), mState, v);
                }
            });
            holder.mDeleteImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(new IngredientEntity(), mState, v);
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
        public final TextView mIngredientView;
        public final ImageView mEditImageButton;
        public final ImageView mDeleteImageButton;
        public final CheckBox mCheckbox;
        public IngredientEntity mItem;

        public ViewHolder(IngredientFragmentItemBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            mIngredientView = binding.ingredientTextView;
            mEditImageButton = binding.ingredientEditImageButton;
            mDeleteImageButton = binding.ingredientDeleteImageButton;
            mCheckbox = binding.ingredientCheckbox;
        }
    }
}