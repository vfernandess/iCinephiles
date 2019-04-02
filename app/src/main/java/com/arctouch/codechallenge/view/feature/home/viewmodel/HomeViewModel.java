package com.arctouch.codechallenge.view.feature.home.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Parcelable;

import com.arctouch.codechallenge.data.model.Movie;
import com.arctouch.codechallenge.data.repository.MovieDataSource;
import com.arctouch.codechallenge.view.feature.home.view.MovieAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

import static java.util.Objects.requireNonNull;

public class HomeViewModel implements LifecycleObserver, MovieAdapter.MovieItemDataSource {

    private static final int NONE = -1;

    public final ObservableField<Integer> newMoviesStartAt;
    public final ObservableField<Integer> newMoviesCount;

    public final ObservableField<State> state;
    public final ObservableField<Parcelable> onMovieSelected;


    public final List<MovieItemViewModel> movies;

    private long mCurrentPage;

    @Inject
    MovieDataSource mMovieDataSource;

    private final List<Disposable> mDisposables;

    @Inject
    HomeViewModel() {
        onMovieSelected = new ObservableField<>();
        state = new ObservableField<>(new State());
        newMoviesStartAt = new ObservableField<>(NONE);
        newMoviesCount = new ObservableField<>(NONE);

        movies = new ArrayList<>();
        mDisposables = new ArrayList<>();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(final LifecycleOwner owner) {
//        owner.getLifecycle().getCurrentState() == Lifecycle.State.INITIALIZED
        load();
    }

    public void load() {
        requireNonNull(this.state.get()).setLoadingEnabled(true);
        requireNonNull(this.state.get()).setLoadingMoreEnabled(false);
        mCurrentPage = 1;
        getPage(mCurrentPage);
    }

    public void loadMore() {
        requireNonNull(this.state.get()).setLoadingEnabled(false);
        requireNonNull(this.state.get()).setLoadingMoreEnabled(true);
        mCurrentPage++;
        getPage(mCurrentPage);
    }

    private void getPage(final long page) {
        final Disposable disposable = mMovieDataSource
                .getMovies(page, Locale.getDefault())
                .subscribe(movies -> {

                });
        mDisposables.add(disposable);
    }

    private void onMoviesLoaded(final List<Movie> movies) {
        requireNonNull(this.state.get()).setLoadingEnabled(false);
        requireNonNull(this.state.get()).setLoadingMoreEnabled(false);

    }

    private void onMoviesError() {
//        requireNonNull(this.state.get()).toggleError();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        for (final Disposable disposable : mDisposables) {
            disposable.dispose();
        }
    }

    @Override
    public int getMovieCount() {
        return movies.size();
    }

    @Override
    public MovieItemViewModel getItemViewModel(final int position) {
        return movies.get(position);
    }

    public static class State {

        public final ObservableBoolean loading = new ObservableBoolean(true);

        public final ObservableBoolean loadingMore = new ObservableBoolean(false);

        public final ObservableBoolean showFullError = new ObservableBoolean(false);

        public final ObservableBoolean showLoadMoreError = new ObservableBoolean(false);

        void setLoadingEnabled(final boolean enabled) {
            this.loading.set(enabled);
        }

        void setLoadingMoreEnabled(final boolean enabled) {
            this.loadingMore.set(enabled);
        }

//        void toggleError() {
//            this.showError.set(!this.showError.get());
//        }

    }

}
