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

/**
 * Class to display cook notes
 */
public class CookNoteRecyclerViewAdapter extends RecyclerView.Adapter<CookNoteRecyclerViewAdapter.ViewHolder> {
    // Declare variables
    private List<CookNoteEntity> mValues = new ArrayList<>();
    private CookNoteFragment.OnListFragmentInteractionListener mListener;
    private final int mState;

    /**
     * Constructor for listener and state
     * @param listener to set
     * @param state to set
     */
    public CookNoteRecyclerViewAdapter(CookNoteFragment.OnListFragmentInteractionListener listener,
                                       int state) {
        mListener = listener;
        mState = state;
    }

    /**
     * Method to create cook note adapter view holder
     * @param parent of the view holder
     * @param viewType of the view holder
     * @return view holder
     */
    @NonNull
    @Override
    public CookNoteRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CookNoteRecyclerViewAdapter.ViewHolder(CookNoteFragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    /**
     * Method to bind data to the view holder
     * @param holder to bind data for
     * @param position of the data
     */
    @Override
    public void onBindViewHolder(final CookNoteRecyclerViewAdapter.ViewHolder holder, int position) {
        // get cook note at position
        holder.mItem = mValues.get(position);
        // create a string for the cook note
        String tempString = holder.mItem.getNumber() + ") " + holder.mItem.getNote();
        // set text for cook note
        holder.mCookNoteView.setText(tempString);
        // test state and set visibility of views accordingly
        if (mState == CookbookActivity.STATE_EDIT) {
            // set listener for edit icon
            holder.mEditImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem, mState, v);
                }
            });
            // set listener for delete icon
            holder.mDeleteImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem, mState, v);
                }
            });
            // set visibility for views
            holder.mEditImageButton.setVisibility(View.VISIBLE);
            holder.mDeleteImageButton.setVisibility(View.VISIBLE);
        } else {
            // set visibility for views
            holder.mEditImageButton.setVisibility(View.GONE);
            holder.mDeleteImageButton.setVisibility(View.GONE);
        }
    }

    /**
     * Method to get number of cook notes
     * @return number of cook notes
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
     * @param notes to set
     */
    public void setData(List<CookNoteEntity> notes) {
        mValues = notes;
        notifyDataSetChanged();
    }

    /**
     * Class fo the cook note view holders
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mCookNoteView;
        public final ImageView mEditImageButton;
        public final ImageView mDeleteImageButton;
        public CookNoteEntity mItem;

        /**
         * Constructor for the view holder
         * @param binding to use
         */
        public ViewHolder(CookNoteFragmentItemBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            mCookNoteView = binding.cookNoteTextView;
            mEditImageButton = binding.cookNoteEditImageButton;
            mDeleteImageButton = binding.cookNoteDeleteImageButton;
        }
    }
}
