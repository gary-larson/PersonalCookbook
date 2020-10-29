package com.larsonapps.personalcookbook.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.ImageEntity;

import com.larsonapps.personalcookbook.databinding.ImageFragmentItemBinding;
import com.larsonapps.personalcookbook.model.CookbookRepository;
import com.larsonapps.personalcookbook.ui.CookbookActivity;
import com.larsonapps.personalcookbook.ui.ImageFragment;
import com.squareup.picasso.Picasso;


import java.io.File;
import java.util.List;

/**
 * Class to deal with the display of images
 */
public class ImageRecyclerViewAdapter extends
        RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder> {
    // Declare variables
    private List<ImageEntity> mValues = null;
    private final ImageFragment.OnListFragmentInteractionListener mListener;
    private final int mState;

    /**
     * Constructor for image recycler view adapter
     * @param listener to handle clicks
     * @param state of the app
     */
    public ImageRecyclerViewAdapter(ImageFragment.OnListFragmentInteractionListener listener,
                                    int state) {
        mListener = listener;
        mState = state;
    }

    /**
     * Method to create a view holder
     * @param parent of the view holder
     * @param viewType of the view holder
     * @return created view holder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ImageFragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    /**
     * Method to bind date to the view holder
     * @param holder to bind
     * @param position of the date
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // Get image at position
        holder.mImage = mValues.get(position);
        // test if image has url
        if (holder.mImage.getImageUrl() != null) {
            // check if image is to large and if file
            if (holder.mImage.getType().equals(CookbookRepository.FILE_TYPE)) {
                if (holder.mImage.getWidth() > holder.mView.getResources()
                        .getDimension(R.dimen.image_max_width) || holder.mImage.getHeight() >
                        holder.mView.getResources().getDimension(R.dimen.image_max_height)) {
                    // set file image and resize
                    Picasso.get().load(new File(holder.mImage.getImageUrl()))
                            .noPlaceholder()
                            .resize((int) holder.mView.getResources()
                                            .getDimension(R.dimen.image_max_width),
                                    (int) holder.mView.getResources()
                                            .getDimension(R.dimen.image_max_height))
                            .into(holder.mImageView);
                } else {
                    // set file image
                    Picasso.get().load(new File(holder.mImage.getImageUrl()))
                            .noPlaceholder()
                            .into(holder.mImageView);
                }
            } else {
                // test image size
                if (holder.mImage.getWidth() > holder.mView.getResources()
                        .getDimension(R.dimen.image_max_width) || holder.mImage.getHeight() >
                        holder.mView.getResources().getDimension(R.dimen.image_max_height)) {
                    // set image and resize
                    Picasso.get().load(holder.mImage.getImageUrl())
                            .noPlaceholder()
                            .resize((int) holder.mView.getResources()
                                            .getDimension(R.dimen.image_max_width),
                                    (int) holder.mView.getResources()
                                            .getDimension(R.dimen.image_max_height))
                            .into(holder.mImageView);
                } else {
                    //set image
                    Picasso.get().load(holder.mImage.getImageUrl())
                            .noPlaceholder()
                            .into(holder.mImageView);
                }
            }
        }
        // test state and set view visibility accordingly
        if (mState == CookbookActivity.STATE_EDIT) {
            // set listener for delete icon
            holder.mImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mImage, mState, v);
                }
            });
            // set visibility
            holder.mImageButton.setVisibility(View.VISIBLE);
        } else {
            // set visibility
            holder.mImageButton.setVisibility(View.GONE);
        }
    }

    /**
     * Method to get the amount of the data
     * @return the amount of the data
     */
    @Override
    public int getItemCount() {
        if (mValues == null) {
            return 0;
        }
        return mValues.size();
    }

    /**
     * Method to set the adapter data and notify data set changed
     * @param lists of the data to set
     */
    public void setData(List<ImageEntity> lists) {
        mValues = lists;
        notifyDataSetChanged();
    }

    /**
     * Method to get the item id
     * @param position position of the data
     * @return item id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Method to get the view type
     * @param position position of the view
     * @return view type
     */
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * Class for the view holder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Declare variables
        public final View mView;
        public ImageView mImageView;
        public ImageView mImageButton;
        public ImageEntity mImage;

        /**
         * Constructor for the view holder
         * @param binding binding of the layout
         */
        public ViewHolder(ImageFragmentItemBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            mImageView = binding.image;
            mImageButton = binding.imageButton;
        }
    }
}