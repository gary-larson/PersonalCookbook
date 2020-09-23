package com.larsonapps.personalcookbook.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.RecipeImage;
import com.larsonapps.personalcookbook.data.Step;

import com.larsonapps.personalcookbook.databinding.ImageFragmentItemBinding;
import com.larsonapps.personalcookbook.ui.CookbookActivity;
import com.larsonapps.personalcookbook.ui.ImageFragment;
import com.larsonapps.personalcookbook.ui.dummy.DummyContent.DummyItem;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder> {
    // Declare variables
    private final List<RecipeImage> mValues;
    private final ImageFragment.OnListFragmentInteractionListener mListener;
    private final int mState;

    public ImageRecyclerViewAdapter(ImageFragment.OnListFragmentInteractionListener listener, List<RecipeImage> images, int state) {
        mValues = images;
        mListener = listener;
        mState = state;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(ImageFragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
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
        }
        if (mState == CookbookActivity.STATE_EDIT) {
            holder.mImageButton.setOnClickListener(v -> {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mImage, mState, v);
                }
            });
            holder.mImageButton.setVisibility(View.VISIBLE);
            holder.mCheckbox.setVisibility(View.GONE);
        } else if (mState == CookbookActivity.STATE_IMPORT) {
            holder.mCheckbox.setVisibility(View.VISIBLE);
            holder.mImageButton.setVisibility(View.GONE);
        } else {
                holder.mImageButton.setVisibility(View.GONE);
                holder.mCheckbox.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (mValues == null) {
            return 0;
        }
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
        public CheckBox mCheckbox;


        public ViewHolder(ImageFragmentItemBinding binding) {
            super(binding.getRoot());
            mView = binding.getRoot();
            mImageView = binding.image;
            mImageButton = binding.imageButton;
            mCheckbox = binding.imageCheckbox;
        }
    }
}