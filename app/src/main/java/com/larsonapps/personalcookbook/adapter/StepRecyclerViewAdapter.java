package com.larsonapps.personalcookbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    /**
     * Constructor for listener and state
     * @param listener to set
     * @param state to set
     */
    public StepRecyclerViewAdapter(StepFragment.OnListFragmentInteractionListener listener,
                                   int state) {
        mListener = listener;
        mState = state;
    }

    /**
     * Method to create step vie holder
     * @param parent of the view holder
     * @param viewType of the view holder
     * @return view holder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(StepFragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    /**
     * Method to bind step data to view holder
     * @param holder to bind data for
     * @param position of of the data
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // get step for this position
        holder.mItem = mValues.get(position);
        // build step text and set
        String tempString = holder.mItem.getNumber() + ") " + holder.mItem.getInstruction();
        holder.mStepView.setText(tempString);
        // test if personal note exists
        if (holder.mItem.getPersonalNote() == null || holder.mItem.getPersonalNote().isEmpty()) {
            // set visibility
            holder.mPersonalNoteView.setVisibility(View.GONE);
        } else {
            // set visibility and set personal note text
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
     * Method to get number of steps
     * @return number of steps
     */
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

    /**
     * Class for step view holder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mStepView;
        public final TextView mPersonalNoteView;
        public final ImageView mEditImageButton;
        public final ImageView mDeleteImageButton;
        public StepEntity mItem;

        /**
         * Constructor for binding
         * @param binding to use
         */
        public ViewHolder(StepFragmentItemBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            mStepView = binding.stepTextView;
            mPersonalNoteView = binding.stepPersonalNoteTextView;
            mEditImageButton = binding.stepEditImageButton;
            mDeleteImageButton = binding.stepDeleteImageButton;
        }
    }
}