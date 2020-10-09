package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.RecipeEntity;
import com.larsonapps.personalcookbook.model.IngredientViewModel;

import com.larsonapps.personalcookbook.databinding.CookbookDetailsFragmentBinding;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class CookbookDetailsFragment extends Fragment {
    // Declare constants
    private static final String ARG_STATE = "state";
    private static final String ARG_RECIPE = "recipe";

    // Declare variables
    private CookbookDetailsFragmentBinding mBinding;
    private RecipeEntity mRecipe;
    private int mState;

    /**
     * Default constructor
     */
    public CookbookDetailsFragment() {}

    /**
     * Method to create a new instance of this fragment
     * @param state of the app
     * @param recipe of data
     * @return the new fragment
     */
    public static CookbookDetailsFragment newInstance(int state, RecipeEntity recipe) {
        CookbookDetailsFragment fragment = new CookbookDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STATE, state);
        args.putParcelable(ARG_RECIPE, recipe);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Method to save the state of the instance
     * @param savedInstanceState to save
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mState = getArguments().getInt(ARG_STATE);
            mRecipe = getArguments().getParcelable(ARG_RECIPE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = CookbookDetailsFragmentBinding.inflate(inflater, container, false);
        String assetUrl = "file:///android_asset/ApplePie.jpg";
        Picasso.get().load(assetUrl)
                //.error(R.mipmap.error)
                .noPlaceholder()
                //.resize((int) mContext.getResources().getDimension(R.dimen.details_photo_height),
                //        (int)mContext.getResources().getDimension(R.dimen.details_photo_height))
                .into(mBinding.photo);
        mBinding.toolbar.setTitle(getString(R.string.app_name));
        getChildFragmentManager().beginTransaction()
                .replace(mBinding.contentContainer.getId(), ContentFragment
                        .newInstance(mState, mRecipe))
                .replace(mBinding.ingredientListContainer.getId(), IngredientFragment
                        .newInstance(mState, mRecipe.getId()))
                .replace(mBinding.stepListContainer.getId(), StepFragment
                        .newInstance(mState, mRecipe.getId()))
                .commit();
        CookbookActivity activity = (CookbookActivity) getActivity();
        int height = 0;
        if (activity != null) {
            height = activity.getHeight();
            float h = getResources().getDimension(R.dimen.details_photo_height);
            height -= h - getResources().getInteger(R.integer.list_size_adjuster);
            height /= getResources().getInteger(R.integer.list_size_divisor);
        }
        ViewGroup.LayoutParams params = mBinding.ingredientListContainer.getLayoutParams();
        params.height = height;
        mBinding.ingredientListContainer.setLayoutParams(params);
        params = mBinding.stepListContainer.getLayoutParams();
        params.height = height;
        mBinding.stepListContainer.setLayoutParams(params);
        mBinding.editFab.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Edit FAB clicked", Toast.LENGTH_LONG).show();
        });
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.toolbar);
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar())
                    .setDisplayHomeAsUpEnabled(true);
        }
    }

}