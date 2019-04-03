package com.arctouch.codechallenge;

import com.arctouch.codechallenge.data.model.Genre;
import com.arctouch.codechallenge.data.model.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;


public class MovieTestDataSource {

    public static List<Movie> withEmptyMovies() {
        return new ArrayList<>();
    }

    public static Observable<List<Movie>> withEmptyMoviesObservable() {
        return Observable.just(withEmptyMovies());
    }

    public static List<Genre> withEmptyGenres() {
        return new ArrayList<>();
    }

    public static Observable<List<Genre>> withEmptyGenresObservable() {
        return Observable.just(withEmptyGenres());
    }

    public static List<Movie> withMovies() {
        final List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            final Movie movie = new Movie();
            movie.title = String.format("Movie %1$d", i);
            movie.releaseDate = "9999-99-99";
            movie.overview = String.format("Overview Movie %1$d", i);
            movie.genreIds = Arrays.asList(0, 1, 2, 3, 4);

        }

        return movies;
    }

    public static Observable<List<Movie>> withMoviesObservable() {
        return Observable.just(withMovies());
    }

    public static List<Genre> withGenres() {
        final List<Genre> genres = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            final Genre genre = new Genre();
            genre.id = i;
            genre.name = String.format("Genre %1$d", i);
            genres.add(genre);

        }

        return genres;
    }

    public static Observable<List<Genre>> withGenresObservable() {
        return Observable.just(withGenres());
    }

}
