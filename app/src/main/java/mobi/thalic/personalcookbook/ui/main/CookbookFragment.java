/*
This program saves all recipes on a device with search and editing capabilities.
Copyright (C) 2020  Gary Larson

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
import mobi.thalic.personalcookbook.ui.main.CookbookViewModel;

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