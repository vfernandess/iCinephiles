package com.arctouch.codechallenge;

import com.arctouch.codechallenge.data.repository.MovieDataSource;
import com.arctouch.codechallenge.data.repository.impl.MovieRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Locale;

import static com.arctouch.codechallenge.MovieTestDataSource.withEmptyGenresObservable;
import static com.arctouch.codechallenge.MovieTestDataSource.withEmptyMoviesObservable;
import static com.arctouch.codechallenge.MovieTestDataSource.withGenres;
import static com.arctouch.codechallenge.MovieTestDataSource.withGenresObservable;
import static com.arctouch.codechallenge.MovieTestDataSource.withMovies;
import static com.arctouch.codechallenge.MovieTestDataSource.withMoviesObservable;
import static org.mockito.Mockito.doReturn;

public class MovieRepositoryTest {

    @Rule
    public MockitoRule mRule = MockitoJUnit.rule();

    @Mock
    MovieDataSource.LocalDataSource mLocalDataSource;

    @Mock
    MovieDataSource.RemoteDataSource mRemoteDataSource;

    MovieRepository mMovieRepository;

    @Before
    public void setup() {
        mMovieRepository = new MovieRepository(mLocalDataSource, mRemoteDataSource);
    }

    @Test
    public void checkSuccessfullyUnCachedMovieListing() {

        final Locale locale = Locale.getDefault();

        doReturn(withEmptyGenresObservable()).when(mLocalDataSource).getGenres();
        doReturn(withEmptyMoviesObservable()).when(mLocalDataSource).getMovies();

        doReturn(withGenresObservable()).when(mRemoteDataSource).getGenres(locale);
        doReturn(withMoviesObservable()).when(mRemoteDataSource).getMovies(1L, withGenres(), locale);

        mMovieRepository
                .getMovies(locale)
                .test()
                .assertValue(withMovies());

    }

    @Test
    public void checkSuccessfullyCachedMovieListing() {

        final Locale locale = Locale.getDefault();

        doReturn(withGenresObservable()).when(mLocalDataSource).getGenres();
        doReturn(withMoviesObservable()).when(mLocalDataSource).getMovies();

        doReturn(withEmptyMoviesObservable()).when(mRemoteDataSource)
                .getMovies(1L, withGenres(), locale);

        mMovieRepository
                .getMovies(locale)
                .test()
                .assertValue(withMovies());

    }

}
