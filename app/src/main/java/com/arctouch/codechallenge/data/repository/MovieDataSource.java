package com.arctouch.codechallenge.data.repository;

import com.arctouch.codechallenge.data.model.Genre;
import com.arctouch.codechallenge.data.model.Movie;

import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

public interface MovieDataSource {

    Observable<List<Movie>> getMovies(final Locale locale);

    Observable<List<Movie>> getMoreMovies(final long page, final Locale locale);

    interface RemoteDataSource {

        Observable<List<Movie>> getMovies(final long page, final List<Genre> genres, final Locale locale);

        Observable<List<Genre>> getGenres(final Locale locale);

    }

    interface LocalDataSource {

        Observable<List<Genre>> getGenres();

        Observable<List<Movie>> getMovies();

        void saveGenres(final List<Genre> genres);

        void saveMovies(final List<Movie> movies);

    }

}
