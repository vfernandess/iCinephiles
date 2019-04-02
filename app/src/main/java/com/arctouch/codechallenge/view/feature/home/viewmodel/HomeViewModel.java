package com.arctouch.codechallenge.view.feature.home.viewmodel;

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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static java.util.Objects.requireNonNull;

public class HomeViewModel implements MovieAdapter.MovieItemDataSource {

    public static final int NONE = -1;

    public final ObservableField<Integer> newMoviesStartAt;
    public final ObservableField<Integer> newMoviesCount;
    public final ObservableField<Parcelable> onMovieSelected;
    public final ObservableField<State> state;
    public final List<MovieItemViewModel> movies;
    public long mCurrentPage;

    private final List<Disposable> mDisposables;

    @Inject
    MovieDataSource mMovieDataSource;

    @Inject
    HomeViewModel() {
        onMovieSelected = new ObservableField<>();
        state = new ObservableField<>(new State());
        newMoviesStartAt = new ObservableField<>(NONE);
        newMoviesCount = new ObservableField<>(NONE);

        movies = new ArrayList<>();
        mDisposables = new ArrayList<>();
    }

    public void load(final long page) {
        requireNonNull(this.state.get()).setLoadingEnabled(true);
        requireNonNull(this.state.get()).setLoadingMoreEnabled(false);
        mCurrentPage = page == NONE ? 1 : page;
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onMoviesLoaded,
                        this::onMoviesError);
        mDisposables.add(disposable);
    }

    private void onMoviesLoaded(final List<Movie> movies) {
        requireNonNull(this.state.get()).setLoadingEnabled(false);
        requireNonNull(this.state.get()).setLoadingMoreEnabled(false);

        final int startAt = this.movies.size();

        for (final Movie movie : movies) {
            this.movies.add(new MovieItemViewModel(movie, this::onMovieSelected));
        }

        if (movies.size() > 0) {
            this.newMoviesStartAt.set(startAt);
            this.newMoviesCount.set(movies.size());
        }

    }

    private void onMovieSelected(final Movie movie) {
        this.onMovieSelected.set(movie);
    }

    @SuppressWarnings("unused")
    private void onMoviesError(final Throwable throwable) {
        if(mCurrentPage <= 1) {
            this.state.get().toggleError();
        }


        if(mCurrentPage > 0) {
            mCurrentPage--;
        }
    }

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

        void setLoadingEnabled(final boolean enabled) {
            this.loading.set(enabled);
        }

        void setLoadingMoreEnabled(final boolean enabled) {
            this.loadingMore.set(enabled);
        }

        void toggleError() {
            this.showFullError.set(!this.showFullError.get());
        }

    }

}
