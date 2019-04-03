package com.arctouch.codechallenge.view.feature.home.view;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.databinding.HomeActivityBinding;
import com.arctouch.codechallenge.view.BaseActivity;
import com.arctouch.codechallenge.view.feature.detail.view.DetailActivity;
import com.arctouch.codechallenge.view.feature.home.viewmodel.HomeViewModel;
import com.arctouch.codechallenge.view.util.EndlessRecyclerViewScrollListener;
import com.arctouch.codechallenge.view.util.MarginItemDecoration;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity {

    private static final String EXTRA_CURRENT_PAGE = "EXTRA_CURRENT_PAGE";
    private static final int ITEMS_BEFORE_LOADING = 3;

    private final Observable.OnPropertyChangedCallback onMovieSelectedCallback =
            new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(final Observable sender, final int propertyId) {
                    DetailActivity.start(HomeActivity.this, mViewModel.onMovieSelected.get());
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

        setUpMoviesList();

        mViewModel.onMovieSelected.addOnPropertyChangedCallback(onMovieSelectedCallback);

        final long page = savedInstanceState == null ?
                HomeViewModel.NONE : savedInstanceState.getLong(EXTRA_CURRENT_PAGE, HomeViewModel.NONE);

        mViewModel.load(page);
    }

    private void setUpMoviesList() {
        final LinearLayoutManager layoutManager =
                (LinearLayoutManager) mBinding.movies.getLayoutManager();
        final int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        } else {
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        }

        final int horizontal = getResources().getDimensionPixelSize(R.dimen.size_02x);
        final int vertical = getResources().getDimensionPixelSize(R.dimen.size_00_5x);
        mBinding.movies.addItemDecoration(new MarginItemDecoration(horizontal, vertical));

        final EndlessRecyclerViewScrollListener endless = new EndlessRecyclerViewScrollListener(ITEMS_BEFORE_LOADING,
                EndlessRecyclerViewScrollListener.DIRECTION_BOTTOM);
        endless.setLoadMoreListener(mViewModel::loadMore);
        endless.setLayoutManager(layoutManager);
        mBinding.movies.addOnScrollListener(endless);
    }

    @Override
    protected void onDestroy() {
        mViewModel.onMovieSelected.removeOnPropertyChangedCallback(onMovieSelectedCallback);
        mViewModel.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        outState.putLong(EXTRA_CURRENT_PAGE, mViewModel.mCurrentPage);
        super.onSaveInstanceState(outState);
    }
}
