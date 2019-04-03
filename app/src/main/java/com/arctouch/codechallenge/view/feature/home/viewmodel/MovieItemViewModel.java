package com.arctouch.codechallenge.view.feature.home.viewmodel;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.arctouch.codechallenge.data.model.Movie;


public class MovieItemViewModel {

    public final ObservableField<String> title;
    public final ObservableField<String> genres;
    public final ObservableField<String> releaseDate;
    public final ObservableField<String> poster;

    private final OnMovieSelectedListener mListener;
    private final Movie mMovie;

    public MovieItemViewModel(@NonNull final Movie movie,
                              @Nullable final OnMovieSelectedListener listener) {
        title = new ObservableField<>(movie.title);
        genres = new ObservableField<>(TextUtils.join(" ,", movie.genres));
        releaseDate = new ObservableField<>(movie.releaseDate);
        poster = new ObservableField<>(movie.getPosterURL());
        mListener = listener;
        mMovie = movie;
    }

    public void select() {
        if (mListener == null) {
            return;
        }

        mListener.onMovieSelected(mMovie);
    }

    interface OnMovieSelectedListener {

        void onMovieSelected(final Movie movie);

    }

}
