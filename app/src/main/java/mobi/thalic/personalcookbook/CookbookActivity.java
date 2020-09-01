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