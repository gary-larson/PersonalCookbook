package com.larsonapps.personalcookbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.larsonapps.personalcookbook.data.StepEntity;
import com.larsonapps.personalcookbook.databinding.StepFragmentItemBinding;
import com.larsonapps.personalcookbook.ui.CookbookActivity;
import com.larsonapps.personalcookbook.ui.StepFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to display atep information
 */
public class StepRecyclerViewAdapter extends RecyclerView.Adapter<StepRecyclerViewAdapter.ViewHolder> {
    // Declare variables
    private List<StepEntity> mValues = new ArrayList<>();
    private StepFragment.OnListFragmentInteractionListener mListener;
    private final int mState;

    public StepRecyclerViewAdapter(StepFragment.OnListFragmentInteractionListener listener,
                                   int state) {
        mListener = listener;
        mState = state;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(StepFragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mStepView.setText(mValues.get(position).toString());
        if (mState == CookbookActivity.STATE_EDIT) {
            holder.mEditImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(new StepEntity(), mState, v);
                }
            });
            holder.mDeleteImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(new StepEntity(), mState, v);
                }
            });
            holder.mEditImageButton.setVisibility(View.VISIBLE);
            holder.mDeleteImageButton.setVisibility(View.VISIBLE);
        } else {
            holder.mEditImageButton.setVisibility(View.GONE);
            holder.mDeleteImageButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (mValues == null) {
            return 0;
        }
        return mValues.size();
    }

    /**
     * Method to set data and notify adapter
     * @param steps to set
     */
    public void setData(List<StepEntity> steps) {
        mValues = steps;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mStepView;
        public final ImageView mEditImageButton;
        public final ImageView mDeleteImageButton;
        public StepEntity mItem;

        public ViewHolder(StepFragmentItemBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            mStepView = binding.stepTextView;
            mEditImageButton = binding.stepEditImageButton;
            mDeleteImageButton = binding.stepDeleteImageButton;
        }
    }
}