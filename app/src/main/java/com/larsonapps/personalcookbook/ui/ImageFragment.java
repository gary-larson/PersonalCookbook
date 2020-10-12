package com.larsonapps.personalcookbook.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larsonapps.personalcookbook.adapter.ImageRecyclerViewAdapter;
import com.larsonapps.personalcookbook.data.ImageEntity;
import com.larsonapps.personalcookbook.data.IngredientEntity;
import com.larsonapps.personalcookbook.databinding.ImageFragmentItemListBinding;
import com.larsonapps.personalcookbook.model.ImageViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ImageFragment extends Fragment {
    // Declare constants
    private static final String ARG_STATE = "state";
    private static final String ARG_RECIPE_ID = "recipeId";
    private static final String ARG_IMAGES = "images";
    private static final String STATE = "mState";
    private static final String RECIPE_ID = "mRecipeId";
    private static final String IMAGES = "mImages";
    // Declare variables
    private int mState;
    private int mRecipeId;
    private List<ImageEntity> mImageList;
    private ImageFragmentItemListBinding mBinding;
    private ImageFragment.OnListFragmentInteractionListener mListener;
    private ImageViewModel mImageViewModel;

    /**
     * Default constructor
     */
    public ImageFragment() {
    }

    /**
     * Method to create a new image fragment
     *
     * @param state    to save
     * @param recipeId to set
     * @return new image fragment
     */
    public static ImageFragment newInstance(int state, int recipeId) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putInt(ARG_RECIPE_ID, recipeId);
        fragment.setArguments(args);
        return fragment;
    }

    public static ImageFragment newInstance(int state, List<ImageEntity> list) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putParcelableArrayList(ARG_IMAGES, (ArrayList<ImageEntity>) list);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Method to initialize when activity is created
     *
     * @param savedInstanceState for state changes
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mState = getArguments().getInt(ARG_STATE);
            if (getArguments().containsKey(ARG_RECIPE_ID)) {
                mRecipeId = getArguments().getInt(ARG_RECIPE_ID);
            }
            if (getArguments().containsKey(ARG_IMAGES)) {
                mImageList = getArguments().getParcelableArrayList(ARG_IMAGES);
            }
        }
    }

    /**
     * Method to create the view(s) of the image fragment
     *
     * @param inflater           to inflate the views
     * @param container          to hold the views
     * @param savedInstanceState of the fragment
     * @return the created view(s)
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = ImageFragmentItemListBinding.inflate(inflater, container, false);
        mImageViewModel = new ViewModelProvider(requireActivity()).get(ImageViewModel.class);

        // Set the adapter
        Context context = mBinding.getRoot().getContext();
        RecyclerView recyclerView = mBinding.imageList;
        if (savedInstanceState != null) {
            mState = savedInstanceState.getInt(STATE);
            mRecipeId = savedInstanceState.getInt(RECIPE_ID);
            mImageList = savedInstanceState.getParcelableArrayList(IMAGES);
        }
        ImageRecyclerViewAdapter adapter = new ImageRecyclerViewAdapter(mListener, mState);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        if (mState == CookbookActivity.STATE_MANUAL || mState == CookbookActivity.STATE_IMPORT) {
            adapter.setData(mImageList);
        } else {
            mImageViewModel.getImages(mRecipeId).observe(getViewLifecycleOwner(), newImages -> {
                if (newImages != null && newImages.size() > 0) {
                    adapter.setData(newImages);
                }
            });
        }
        return mBinding.getRoot();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE, mState);
        outState.putInt(RECIPE_ID, mRecipeId);
        outState.putParcelableArrayList(IMAGES, (ArrayList<ImageEntity>) mImageList);
    }

    /**
     * Method that initializes the listener
     * @param context to use
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ImageFragment.OnListFragmentInteractionListener) {
            mListener = (ImageFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() );
        }
    }

    /**
     * Method to remove listener
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Interface for the click listener in the activity containing the fragment
     */
    public interface OnListFragmentInteractionListener {
        // set arguments type and name
        void onListFragmentInteraction(ImageEntity image, int state, View view);
    }
}