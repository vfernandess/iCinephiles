package com.arctouch.codechallenge.view.feature.home.view;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.databinding.HomeActivityBinding;
import com.arctouch.codechallenge.view.BaseActivity;
import com.arctouch.codechallenge.view.feature.home.viewmodel.HomeViewModel;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity {

    private static final String EXTRA_CURRENT_PAGE = "EXTRA_CURRENT_PAGE";

    private final Observable.OnPropertyChangedCallback onMovieSelectedCallback =
            new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(final Observable sender, final int propertyId) {
                    // TODO: 01/04/19 navigate to details
                }
            };

    private final Observable.OnPropertyChangedCallback onLoadMoreErrorCallback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(final Observable sender, final int propertyId) {
            // TODO: 02/04/19 show snack bar generic message error
        }
    };

    @Inject
    HomeViewModel mViewModel;

    @Inject
    MovieAdapter mAdapter;

    private HomeActivityBinding mBinding;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);

        mBinding = DataBindingUtil.setContentView(this, R.layout.home_activity);
        mBinding.setViewModel(mViewModel);

        mAdapter.setDataSource(mViewModel);
        mBinding.movies.setAdapter(mAdapter);

        mViewModel.onMovieSelected.addOnPropertyChangedCallback(onMovieSelectedCallback);
        mViewModel.state.get().showLoadMoreError.addOnPropertyChangedCallback(onLoadMoreErrorCallback);

        final long page = savedInstanceState == null ?
                HomeViewModel.NONE : savedInstanceState.getLong(EXTRA_CURRENT_PAGE, HomeViewModel.NONE);
        mViewModel.load(page);
    }

    @Override
    protected void onDestroy() {
        mViewModel.onMovieSelected.removeOnPropertyChangedCallback(onMovieSelectedCallback);
        mViewModel.state.get().showLoadMoreError.removeOnPropertyChangedCallback(onLoadMoreErrorCallback);
        mViewModel.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        outState.putLong(EXTRA_CURRENT_PAGE, mViewModel.mCurrentPage);
        super.onSaveInstanceState(outState);
    }
}
