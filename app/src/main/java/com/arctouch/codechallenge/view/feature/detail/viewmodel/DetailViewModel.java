package com.arctouch.codechallenge.view.feature.detail.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.arctouch.codechallenge.data.model.Movie;
import com.arctouch.codechallenge.data.repository.MovieDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailViewModel {

    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> poster = new ObservableField<>();
    public final ObservableField<String> backdrop = new ObservableField<>();
    public final ObservableField<String> genres = new ObservableField<>();
    public final ObservableField<String> overview = new ObservableField<>();
    public final ObservableField<String> releaseDate = new ObservableField<>();

    private final List<Disposable> disposables;

    @Inject
    MovieDataSource mMovieRepository;

    @Inject
    DetailViewModel() {
        disposables = new ArrayList<>();
    }

    public void load(final long id) {
        final Disposable disposable = mMovieRepository
                .getMovie(id, Locale.getDefault())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onMovieLoaded, this::onMovieError);
        disposables.add(disposable);
    }

    private void onMovieError(final Throwable throwable) {

    }

    private void onMovieLoaded(final Movie movie) {
        this.name.set(movie.title);
        this.poster.set(movie.getPosterURL());
        this.backdrop.set(movie.getBackdropURL());
        this.genres.set(TextUtils.join(", ", movie.genres));
        this.overview.set(movie.overview);
        this.releaseDate.set(movie.releaseDate);
    }

    public void onDestroy() {
        for (final Disposable disposable : disposables) {
            disposable.dispose();
        }
    }

}
