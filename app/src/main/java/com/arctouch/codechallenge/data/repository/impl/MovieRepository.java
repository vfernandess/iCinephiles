package com.arctouch.codechallenge.data.repository.impl;

import com.arctouch.codechallenge.data.model.Movie;
import com.arctouch.codechallenge.data.repository.MovieDataSource;

import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

public class MovieRepository implements MovieDataSource {

    private final LocalDataSource mLocalDataSource;
    private final RemoteDataSource mRemoteDataSource;

    public MovieRepository(final LocalDataSource local, final RemoteDataSource remote) {
        mLocalDataSource = local;
        mRemoteDataSource = remote;
    }

    @Override
    public Observable<List<Movie>> getMovies(final long page, final Locale locale) {
        return mLocalDataSource
                .getMovies(page, locale)
                .flatMap(movies -> {
                    if (movies.isEmpty()) {
                        return getRemoteMovies(page, locale);
                    } else {
                        return Observable.just(movies);
                    }
                });
    }

    private Observable<List<Movie>> getRemoteMovies(final long page, final Locale locale) {
        return mLocalDataSource
                .getGenres()
                .flatMap(genres -> {
                    if (genres.isEmpty()) {
                        return mRemoteDataSource
                                .getGenres(locale)
                                .flatMap(remoteGenres -> {
                                    mLocalDataSource.saveGenres(remoteGenres);
                                    return mRemoteDataSource.getMovies(page, remoteGenres, locale);
                                });
                    } else {
                        return mRemoteDataSource.getMovies(page, genres, locale);
                    }
                }).map(movies -> {
                    mLocalDataSource.saveMovies(movies);
                    return movies;
                });
    }

}
