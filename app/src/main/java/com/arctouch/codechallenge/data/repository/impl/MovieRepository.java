package com.arctouch.codechallenge.data.repository.impl;

import com.arctouch.codechallenge.data.model.Movie;
import com.arctouch.codechallenge.data.repository.MovieDataSource;
import com.arctouch.codechallenge.data.repository.impl.local.MovieLocalDataSource;
import com.arctouch.codechallenge.data.repository.impl.remote.MovieRemoteDataSource;

import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

public class MovieRepository implements MovieDataSource {

    private final MovieLocalDataSource mLocalDataSource;
    private final MovieRemoteDataSource mRemoteDataSource;

    public MovieRepository(final MovieLocalDataSource local, final MovieRemoteDataSource remote) {
        mLocalDataSource = local;
        mRemoteDataSource = remote;
    }

    @Override
    public Observable<List<Movie>> getMovies(final long page, final Locale locale) {

        mLocalDataSource.getMovies(page, locale).flatMap(movies -> {

            return null;
        });

        return null;
    }
}
