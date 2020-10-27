package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.larsonapps.personalcookbook.R;
import com.larsonapps.personalcookbook.data.ImageEntity;
import com.larsonapps.personalcookbook.databinding.AddImageFragmentBinding;
import com.squareup.picasso.Picasso;

public class AddImageDialogFragment extends DialogFragment {
    // Declare constants
    private static final String ARG_TITLE = "title";
    private static final String ARG_STATE = "state";
    private static final String ARG_IMAGE = "image";
    private static final String TITLE = "mTitle";
    private static final String STATE = "mState";
    private static final String IMAGE = "mImage";
    private static final String INTERNET_TYPE = "Internet";
    // Declare variables
    private AddImageFragmentBinding mBinding;
    private int mState;
    private ImageEntity mImage;
    private String mTitle;

    /**
     * Default constructor
     */
    public AddImageDialogFragment() {}

    /**
     * Method to create a new instance of edit image dialog fragment
     * @param title to display
     * @param state to save
     * @param image to use
     * @return created dialog fragment
     */
    public static AddImageDialogFragment newInstance(String title, int state,
                                                     ImageEntity image) {
        AddImageDialogFragment dialogFragment = new AddImageDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putInt(ARG_STATE, state);
        args.putParcelable(ARG_IMAGE, image);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    /**
     * Method to load arguments
     * @param savedInstanceState to maintain state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            mState = getArguments().getInt(ARG_STATE);
            mImage = getArguments().getParcelable(ARG_IMAGE);
        }
    }

    /**
     * Method to create the view for edit image dialog fragment
     * @param inflater to inflate the view
     * @param container the view is in
     * @param savedInstanceState of the fragment
     * @return that created view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = AddImageFragmentBinding.inflate(inflater, container, false);
        if (savedInstanceState != null) {
            mTitle = savedInstanceState.getString(TITLE);
            mState = savedInstanceState.getInt(STATE);
            mImage = savedInstanceState.getParcelable(IMAGE);
        }
        // set image url
        if (mImage != null) {
            String temp = mImage.getImageUrl();
            mBinding.addImageUrlEditText.setText(temp);
            loadImage();
        } else {
            mImage = new ImageEntity();
        }
        // set listener for url edit text
        mBinding.addImageUrlEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mImage.setImageUrl(s.toString());
                mImage.setType(INTERNET_TYPE);
                loadImage();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        // set listener for submit button
        mBinding.addImageSubmitButton.setOnClickListener(v -> sendImageToParent());
        return mBinding.getRoot();
    }

    /**
     * Method to load image
     */
    private void loadImage() {
        // test for url
        if (mImage.getImageUrl() != null) {
            // test for large image
            if (mImage.getHeight() > getResources().getDimension(R.dimen.image_max_height) ||
                    mImage.getWidth() > getResources().getDimension(R.dimen.image_max_width)) {
                // set image and resize
                Picasso.get().load(mImage.getImageUrl())
                    .noPlaceholder()
                    .resize((int) getResources().getDimension(R.dimen.image_max_width),
                            (int) getResources().getDimension(R.dimen.image_max_height))
                    .into(mBinding.addImageView);
            } else {
                // set image
                Picasso.get().load(mImage.getImageUrl())
                    .noPlaceholder()
                    .into(mBinding.addImageView);
            }
        }
    }

    /**
     * Method to display the dialog fragment
     * @param view to display
     * @param savedInstanceState of the fragment
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // set title
        if (getDialog() != null) {
            getDialog().setTitle(mTitle);
        }
        // set focus
        mBinding.addImageUrlEditText.requestFocus();
        // display the virtual keyboard
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    /**
     * Method to save state
     * @param outState state
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TITLE, mTitle);
        outState.putInt(STATE, mState);
        outState.putParcelable(IMAGE, mImage);
    }

    /**
     * Method to send the step to the calling fragment
     */
    public void sendImageToParent() {
        // set listener
        AddImageDialogFragment.AddImageDialogListener listener =
                (AddImageDialogFragment.AddImageDialogListener) getTargetFragment();
        // send image
        if (listener != null) {
            listener.onFinishAddImageDialog(mImage);
        }
        dismiss();
    }

    /**
     * Interface for the listener
     */
    public interface AddImageDialogListener {
        void onFinishAddImageDialog(ImageEntity image);
    }
}
