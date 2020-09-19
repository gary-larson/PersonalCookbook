package com.larsonapps.personalcookbook.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.Step;
import com.larsonapps.personalcookbook.databinding.StepFragmentItemBinding;
import com.larsonapps.personalcookbook.ui.StepFragment;
import com.larsonapps.personalcookbook.ui.dummy.DummyContent.DummyItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class StepRecyclerViewAdapter extends RecyclerView.Adapter<StepRecyclerViewAdapter.ViewHolder> {
    // Declare constants
    private static final int STATE_EDIT= 1;
    private static final int STATE_MANUAL = 2;
    private static final int STATE_IMPORT = 3;

    private final List<DummyItem> mValues;
    private static StepFragmentItemBinding mBinding;
    private StepFragment.OnListFragmentInteractionListener mListener;
    private final int mState;

    public StepRecyclerViewAdapter(StepFragment.OnListFragmentInteractionListener listener, List<DummyItem> items, int state) {
        mListener = listener;
        mValues = items;
        mState = state;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        mBinding = StepFragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
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
                    mListener.onListFragmentInteraction(new Step(), mState);
                }
            });
            mBinding.stepImageButton.setVisibility(View.GONE);
        } else if (mState == 1) {
            holder.mImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(new Step(), mState);
                }
            });
            mBinding.stepImageButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
            mImageButton = mBinding.stepImageButton;
        }

        @NotNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}