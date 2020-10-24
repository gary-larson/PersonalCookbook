package com.larsonapps.personalcookbook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.larsonapps.personalcookbook.data.KeywordEntity;
import com.larsonapps.personalcookbook.databinding.AddKeywordFragmentBinding;

public class AddKeywordDialogFragment extends DialogFragment {
    // Declare constants
    private static final String ARG_TITLE = "title";
    private static final String ARG_STATE = "state";
    private static final String ARG_KEYWORD = "keyword";
    private static final String TITLE = "mTitle";
    private static final String STATE = "mState";
    private static final String KEYWORD = "mKeyword";
    // Declare variables
    private AddKeywordFragmentBinding mBinding;
    private int mState;
    private KeywordEntity mKeyword;
    private String mTitle;


    /**
     * Default constructor
     */
    public AddKeywordDialogFragment() {}

    /**
     * Method to create a new instance of edit ingredient dialog fragment
     * @param title to display
     * @param state to save
     * @param keyword to use
     * @return created dialog fragment
     */
    public static AddKeywordDialogFragment newInstance(String title, int state,
                                                       KeywordEntity keyword) {
        AddKeywordDialogFragment dialogFragment = new AddKeywordDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putInt(ARG_STATE, state);
        args.putParcelable(ARG_KEYWORD, keyword);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            mState = getArguments().getInt(ARG_STATE);
            mKeyword = getArguments().getParcelable(ARG_KEYWORD);
        }
    }

    /**
     * Method to create the view for edit keyword dialog fragment
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
        mBinding = AddKeywordFragmentBinding.inflate(inflater, container, false);
        if (savedInstanceState != null) {
            mTitle = savedInstanceState.getString(TITLE);
            mState = savedInstanceState.getInt(STATE);
            mKeyword = savedInstanceState.getParcelable(KEYWORD);
        }
        if (mKeyword != null) {
            String temp = String.valueOf(mKeyword.getKeyword());
            mBinding.addKeywordEditText.setText(temp);
        } else {
            mKeyword = new KeywordEntity();
        }
        mBinding.addKeywordSubmitButton.setOnClickListener(v -> sendKeywordToParent());
        return mBinding.getRoot();
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
        mBinding.addKeywordEditText.requestFocus();
        // display the virtual keyboard
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TITLE, mTitle);
        outState.putInt(STATE, mState);
        outState.putParcelable(KEYWORD, mKeyword);
    }

    /**
     * Method to send the keyword to the calling fragment
     */
    public void sendKeywordToParent() {
        // set listener
        AddKeywordDialogFragment.EditKeywordDialogListener listener =
                (AddKeywordDialogFragment.EditKeywordDialogListener) getTargetFragment();
        // build keyword
        String tempString = mBinding.addKeywordEditText.getText().toString();
        if (!(tempString.isEmpty() || tempString.equals(mKeyword.getKeyword()))) {
            mKeyword.setKeyword(tempString);
        }
        // send keyword
        if (listener != null) {
            listener.onFinishEditKeywordDialog(mKeyword);
        }
        dismiss();
    }

    /**
     * Interface for the listener
     */
    public interface EditKeywordDialogListener {
        void onFinishEditKeywordDialog(KeywordEntity keyword);
    }
}
