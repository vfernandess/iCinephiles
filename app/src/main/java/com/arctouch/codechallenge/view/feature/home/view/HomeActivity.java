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

        mAdapter.setDataSource(mViewModel);
        mBinding.movies.setAdapter(mAdapter);

        mViewModel.onMovieSelected.addOnPropertyChangedCallback(onMovieSelectedCallback);
        mViewModel.state.get().showLoadMoreError.addOnPropertyChangedCallback(onLoadMoreErrorCallback);

        getLifecycle().addObserver(mViewModel);

//        this.recyclerView = findViewById(R.id.recyclerView);
//        this.progressBar = findViewById(R.id.progressBar);
//
//        api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, 1L, TmdbApi.DEFAULT_REGION)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(response -> {
//                    for (Movie movie : response.results) {
//                        movie.genres = new ArrayList<>();
//                        for (Genre genre : Cache.getGenres()) {
//                            if (movie.genreIds.contains(genre.id)) {
//                                movie.genres.add(genre);
//                            }
//                        }
//                    }
//
//                    recyclerView.setAdapter(new HomeAdapter(response.results));
//                    progressBar.setVisibility(View.GONE);
//                });
    }

    @Override
    protected void onDestroy() {
        mViewModel.onMovieSelected.removeOnPropertyChangedCallback(onMovieSelectedCallback);
        mViewModel.state.get().showLoadMoreError.removeOnPropertyChangedCallback(onLoadMoreErrorCallback);
        super.onDestroy();
    }
}
