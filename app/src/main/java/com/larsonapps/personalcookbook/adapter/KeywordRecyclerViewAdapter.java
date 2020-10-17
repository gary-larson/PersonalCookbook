package com.larsonapps.personalcookbook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.larsonapps.personalcookbook.data.KeywordEntity;
import com.larsonapps.personalcookbook.databinding.KeywordFragmentItemBinding;
import com.larsonapps.personalcookbook.ui.CookbookActivity;
import com.larsonapps.personalcookbook.ui.KeywordFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to display keyword list
 */
public class KeywordRecyclerViewAdapter extends
        RecyclerView.Adapter<KeywordRecyclerViewAdapter.ViewHolder> {

    private List<KeywordEntity> mValues = new ArrayList<>();
    private final int mState;
    private final KeywordFragment.OnListFragmentInteractionListener mListener;

    public KeywordRecyclerViewAdapter(KeywordFragment.OnListFragmentInteractionListener listener,
                                      int state) {
        mListener = listener;
        mState = state;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(KeywordFragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mKeywordView.setText(mValues.get(position).getKeyword());
        if (mState == CookbookActivity.STATE_EDIT) {
            holder.mDeleteImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(new KeywordEntity(), mState, v);
                }
            });
            holder.mDeleteImageButton.setVisibility(View.VISIBLE);
        } else {
            holder.mDeleteImageButton.setVisibility(View.GONE);
        }
    }

    public void setData(List<KeywordEntity> keywords) {
        mValues = keywords;
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
        public final TextView mKeywordView;
        public final ImageView mDeleteImageButton;
        public KeywordEntity mItem;

        public ViewHolder(KeywordFragmentItemBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            mKeywordView = binding.keywordTextView;
            mDeleteImageButton = binding.keywordDeleteImageButton;
        }
    }
}