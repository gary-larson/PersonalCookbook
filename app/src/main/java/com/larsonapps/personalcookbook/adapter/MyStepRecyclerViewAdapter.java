package com.larsonapps.personalcookbook.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.Step;
import com.larsonapps.personalcookbook.databinding.StepFragmentItemBinding;
import com.larsonapps.personalcookbook.databinding.StepFragmentItemListBinding;
import com.larsonapps.personalcookbook.ui.StepFragment;
import com.larsonapps.personalcookbook.ui.dummy.DummyContent.DummyItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyStepRecyclerViewAdapter extends RecyclerView.Adapter<MyStepRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private StepFragmentItemBinding mBinding;
    private StepFragment.OnListFragmentInteractionListener mListener;

    public MyStepRecyclerViewAdapter(StepFragment.OnListFragmentInteractionListener listener, List<DummyItem> items) {
        mListener = listener;
        mValues = items;
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
        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(new Step());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @NotNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}