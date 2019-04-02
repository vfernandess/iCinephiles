package com.arctouch.codechallenge.data.repository.impl.local;

import com.arctouch.codechallenge.data.model.Genre;
import com.arctouch.codechallenge.data.model.Movie;
import com.arctouch.codechallenge.data.repository.MovieDataSource;

import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

public class MovieLocalDataSource implements MovieDataSource, MovieDataSource.LocalDataSource {
    @Override
    public Observable<List<Movie>> getMovies(final long page, final Locale locale) {
        return null;
    }

    @Override
    public Observable<List<Genre>> getGenres() {
        return null;
    }
}
