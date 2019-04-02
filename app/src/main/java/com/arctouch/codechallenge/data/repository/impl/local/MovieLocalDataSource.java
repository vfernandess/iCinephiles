package com.arctouch.codechallenge.data.repository.impl.local;

import com.arctouch.codechallenge.data.model.Genre;
import com.arctouch.codechallenge.data.model.Movie;
import com.arctouch.codechallenge.data.repository.MovieDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

public class MovieLocalDataSource implements MovieDataSource.LocalDataSource {

    private static final List<Movie> cachedMovies = new ArrayList<>();

    private static final List<Genre> cachedGenres = new ArrayList<>();

    @Override
    public Observable<List<Movie>> getMovies(final long page, final Locale locale) {
//        if(page == 0) {
//            cachedMovies.clear();
//        }

        return Observable.just(cachedMovies);
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
}
