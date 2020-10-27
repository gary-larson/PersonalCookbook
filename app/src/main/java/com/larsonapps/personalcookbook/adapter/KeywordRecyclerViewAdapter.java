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

    /**
     * Constructor for listener and state
     * @param listener to set
     * @param state to set
     */
    public KeywordRecyclerViewAdapter(KeywordFragment.OnListFragmentInteractionListener listener,
                                      int state) {
        mListener = listener;
        mState = state;
    }

    /**
     * Method to create a keyword view holder
     * @param parent of the view holder
     * @param viewType of the view holder
     * @return view holder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(KeywordFragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    /**
     * Method to bind keyword data to view holder
     * @param holder to bind data for
     * @param position of the data
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // get keyword for this position
        holder.mItem = mValues.get(position);
        // set keyword text
        holder.mKeywordView.setText(mValues.get(position).getKeyword());
        // test state
        if (mState == CookbookActivity.STATE_EDIT) {
            // set delete icon listener
            holder.mDeleteImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem, mState, v);
                }
            });
            // set visibility
            holder.mDeleteImageButton.setVisibility(View.VISIBLE);
        } else {
            // set visibility
            holder.mDeleteImageButton.setVisibility(View.GONE);
        }
    }

    /**
     * Method to set keyword data and notify data changed
     * @param keywords to set
     */
    public void setData(List<KeywordEntity> keywords) {
        mValues = keywords;
        notifyDataSetChanged();
    }

    /**
     * Methos to get the number of keywords
     * @return number of keywords
     */
    @Override
    public int getItemCount() {
        if (mValues == null) {
            return 0;
        }
        return mValues.size();
    }

    /**
     * Class for the keyword view holder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mKeywordView;
        public final ImageView mDeleteImageButton;
        public KeywordEntity mItem;

        /**
         * Constructor for keyword binding
         * @param binding to use
         */
        public ViewHolder(KeywordFragmentItemBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            mKeywordView = binding.keywordTextView;
            mDeleteImageButton = binding.keywordDeleteImageButton;
        }
    }
}