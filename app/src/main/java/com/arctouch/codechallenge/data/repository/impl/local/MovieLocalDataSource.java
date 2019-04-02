package com.arctouch.codechallenge.data.repository.impl.local;

import android.util.SparseArray;

import com.arctouch.codechallenge.data.model.Genre;
import com.arctouch.codechallenge.data.model.Movie;
import com.arctouch.codechallenge.data.repository.MovieDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class MovieLocalDataSource implements MovieDataSource.LocalDataSource {

    private static final List<Movie> cachedMovies = new ArrayList<>();

    private static final List<Genre> cachedGenres = new ArrayList<>();

    private static final SparseArray<Movie> movies = new SparseArray<>();

    @Override
    public Observable<List<Movie>> getMovies() {
        return Observable.just(cachedMovies);
    }

    @Override
    public Observable<Movie> getMovie(final long id) {
        final Movie movie = movies.get((int) id);
        if(movie == null) {
            return Completable.complete().toObservable();
        } else {
            return Observable.just(movie);
        }
    }

    @Override
    public Observable<List<Genre>> getGenres() {
        return Observable.just(cachedGenres);
    }

    @Override
    public void saveGenres(final List<Genre> genres) {
        cachedGenres.addAll(genres);
    }

    @Override
    public void saveMovies(final List<Movie> movies) {
        cachedMovies.addAll(movies);
    }

    @Override
    public void saveMovie(final Movie movie) {

    }
}
