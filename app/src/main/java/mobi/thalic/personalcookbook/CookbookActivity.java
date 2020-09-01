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
package mobi.thalic.personalcookbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import mobi.thalic.personalcookbook.databinding.CookbookActivityBinding;
import mobi.thalic.personalcookbook.ui.main.CookbookFragment;
import mobi.thalic.personalcookbook.ui.main.CookbookViewModel;

public class CookbookActivity extends AppCompatActivity {
    // Declare variables
    CookbookViewModel mCookbookViewModel;
    CookbookActivityBinding mBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = CookbookActivityBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CookbookFragment.newInstance())
                    .commitNow();
        }
        mCookbookViewModel = new ViewModelProvider(this).get(CookbookViewModel.class);
    }
}