package com.larsonapps.personalcookbook.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.RecipeImage;
import com.larsonapps.personalcookbook.data.Step;

import com.larsonapps.personalcookbook.databinding.ImageFragmentItemBinding;
import com.larsonapps.personalcookbook.ui.ImageFragment;
import com.larsonapps.personalcookbook.ui.dummy.DummyContent.DummyItem;
import com.larsonapps.personalcookbook.utilities.GlideApp;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder> {
    //Declare Constants
    private static final int STATE_EDIT= 1;
    private static final int STATE_MANUAL = 2;
    private static final int STATE_IMPORT = 3;

    private final List<RecipeImage> mValues;
    private final ImageFragment.OnListFragmentInteractionListener mListener;
    private final int mState;
    private ImageFragmentItemBinding mBinding;
    private Context mContext;

    public ImageRecyclerViewAdapter(ImageFragment.OnListFragmentInteractionListener listener, List<RecipeImage> images, int state) {
        mValues = images;
        mListener = listener;
        mState = state;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding = ImageFragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        mContext = parent.getContext();
        return new ViewHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mImage = mValues.get(position);
        if (holder.mImage.getImageUrl() != null) {
            Picasso.get().load(holder.mImage.getImageUrl())
                    //.error(R.mipmap.error)
                    .noPlaceholder()
                    //.resize((int) mContext.getResources().getDimension(R.dimen.details_photo_height),
                    //        (int)mContext.getResources().getDimension(R.dimen.details_photo_height))
                    .into(holder.mImageView);
//            GlideApp.with(mContext)
//                   .load(holder.mImage.getImageUrl())
//                   .into(holder.mImageView);
        }
        holder.mImageButton.setOnClickListener(v -> {
            if (null != mListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListener.onListFragmentInteraction(holder.mImage, mState);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ImageView mImageView;
        public ImageView mImageButton;
        public RecipeImage mImage;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = mBinding.image;
            mImageButton = mBinding.imageButton;
        }
    }
}