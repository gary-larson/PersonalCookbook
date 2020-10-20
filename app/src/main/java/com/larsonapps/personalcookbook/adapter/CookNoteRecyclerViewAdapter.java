package com.larsonapps.personalcookbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.larsonapps.personalcookbook.data.CookNoteEntity;
import com.larsonapps.personalcookbook.databinding.CookNoteFragmentItemBinding;
import com.larsonapps.personalcookbook.ui.CookNoteFragment;
import com.larsonapps.personalcookbook.ui.CookbookActivity;

import java.util.ArrayList;
import java.util.List;

public class CookNoteRecyclerViewAdapter extends RecyclerView.Adapter<CookNoteRecyclerViewAdapter.ViewHolder> {
    // Declare variables
    private List<CookNoteEntity> mValues = new ArrayList<>();
    private CookNoteFragment.OnListFragmentInteractionListener mListener;
    private final int mState;

    public CookNoteRecyclerViewAdapter(CookNoteFragment.OnListFragmentInteractionListener listener,
                                       int state) {
        mListener = listener;
        mState = state;
    }

    @NonNull
    @Override
    public CookNoteRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CookNoteRecyclerViewAdapter.ViewHolder(CookNoteFragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final CookNoteRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        String tempString = holder.mItem.getNumber() + ") " + holder.mItem.getNote();
        holder.mCookNoteView.setText(tempString);
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

    @Override
    public int getItemCount() {
        if (mValues == null) {
            return 0;
        }
        return mValues.size();
    }

    /**
     * Method to set data and notify adapter
     * @param notes to set
     */
    public void setData(List<CookNoteEntity> notes) {
        mValues = notes;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mCookNoteView;
        public final ImageView mEditImageButton;
        public final ImageView mDeleteImageButton;
        public CookNoteEntity mItem;

        public ViewHolder(CookNoteFragmentItemBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            mCookNoteView = binding.cookNoteTextView;
            mEditImageButton = binding.cookNoteEditImageButton;
            mDeleteImageButton = binding.cookNoteDeleteImageButton;
        }
    }
}
