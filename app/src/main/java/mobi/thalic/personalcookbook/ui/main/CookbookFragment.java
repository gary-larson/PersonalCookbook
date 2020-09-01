package mobi.thalic.personalcookbook.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import mobi.thalic.personalcookbook.databinding.CookbookFragmentBinding;

public class CookbookFragment extends Fragment {
    // Declare variables
    private CookbookViewModel mCookbookViewModel;
    private CookbookFragmentBinding mBinding;
    public static CookbookFragment newInstance() {
        return new CookbookFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = CookbookFragmentBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCookbookViewModel = new ViewModelProvider(requireActivity())
                .get(CookbookViewModel.class);
        // TODO: Use the ViewModel
    }

}