package com.arctouch.codechallenge.view.feature.home.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.arctouch.codechallenge.data.model.Movie;

public class MovieItemViewModel {

    public final ObservableField<String> title;
    public final ObservableField<String> genres;
    public final ObservableField<String> releaseDate;
    public final ObservableField<String> poster;

    public MovieItemViewModel(final Movie movie) {
        title = new ObservableField<>(movie.title);
        genres = new ObservableField<>(TextUtils.join(" ,", movie.genres));
        releaseDate = new ObservableField<>(movie.releaseDate);
        poster = new ObservableField<>(movie.getPosterURL());
    }

    public void select() {

    }

}
