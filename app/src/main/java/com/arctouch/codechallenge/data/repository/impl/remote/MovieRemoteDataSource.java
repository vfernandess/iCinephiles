package com.arctouch.codechallenge.data.repository.impl.remote;

import com.arctouch.codechallenge.BuildConfig;
import com.arctouch.codechallenge.data.model.Genre;
import com.arctouch.codechallenge.data.model.Movie;
import com.arctouch.codechallenge.data.model.UpcomingMoviesResponse;
import com.arctouch.codechallenge.data.net.TmdbApi;
import com.arctouch.codechallenge.data.repository.MovieDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MovieRemoteDataSource implements MovieDataSource.RemoteDataSource {

    private final TmdbApi mApi;

    public MovieRemoteDataSource(final TmdbApi api) {
        mApi = api;
    }

    @Override
    public Observable<List<Movie>> getMovies(final long page, final List<Genre> genres, final Locale locale) {
        return mApi
                .upcomingMovies(BuildConfig.API_KEY, locale.getLanguage(), page, locale.getCountry())
                .subscribeOn(Schedulers.io())
                .flatMap(upcomingMoviesResponse ->
                        Observable.just(buildMovies(genres, upcomingMoviesResponse))
                );
    }

    @Override
    public Observable<List<Genre>> getGenres(final Locale locale) {
        return mApi
                .genres(BuildConfig.API_KEY, locale.toLanguageTag())
                .flatMap(
                        genreResponse -> Observable.just(genreResponse.genres)
                );
    }

    private List<Movie> buildMovies(final List<Genre> genres, final UpcomingMoviesResponse upcomingMoviesResponse) {
        final List<Movie> movies = new ArrayList<>();

        for (Movie movie : upcomingMoviesResponse.results) {
            movie.genres = new ArrayList<>();
            for (Genre genre : genres) {
                if (movie.genreIds.contains(genre.id)) {
                    movie.genres.add(genre);
                }
            }
            movies.add(movie);
        }

        return movies;
    }

}
