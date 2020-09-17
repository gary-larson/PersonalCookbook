package com.larsonapps.personalcookbook.ui;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.adapter.ImageRecyclerViewAdapter;
import com.larsonapps.personalcookbook.data.RecipeImage;
import com.larsonapps.personalcookbook.data.Step;
import com.larsonapps.personalcookbook.databinding.ImageFragmentItemListBinding;
import com.larsonapps.personalcookbook.ui.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ImageFragment extends Fragment {

    // Declare constants
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_STATE= "state";
    private static final int STATE_EDIT= 1;
    private static final int STATE_MANUAL = 2;
    private static final int STATE_IMPORT = 3;
    // Declare variables
    private int mColumnCount = 1;
    private int mState;
    private ImageFragmentItemListBinding mBinding;
    private ImageFragment.OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ImageFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ImageFragment newInstance(int columnCount, int state) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(ARG_STATE, state);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mState = getArguments().getInt(ARG_STATE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            List<RecipeImage> recipeImages = new ArrayList<>();
            RecipeImage recipeImage = new RecipeImage();
            recipeImage.setImageUrl("file:///android_asset/ApplePie.jpg");
            recipeImages.add(recipeImage);
            recyclerView.setAdapter(new ImageRecyclerViewAdapter(mListener, recipeImages, 1));
        }
        return view;
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
        void onListFragmentInteraction(RecipeImage recipeImage, int state);
    }
}