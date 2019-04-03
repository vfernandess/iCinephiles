package com.arctouch.codechallenge.di.module;


import com.arctouch.codechallenge.BuildConfig;
import com.arctouch.codechallenge.data.net.TmdbApi;
import com.arctouch.codechallenge.data.repository.MovieDataSource;
import com.arctouch.codechallenge.data.repository.impl.MovieRepository;
import com.arctouch.codechallenge.data.repository.impl.local.MovieLocalDataSource;
import com.arctouch.codechallenge.data.repository.impl.remote.MovieRemoteDataSource;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class DataModule {

    private TmdbApi mApi;

    public DataModule() {
        initialize();
    }

    private void initialize() {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            // TODO: 01/04/19 add others interceptor like Authorization for Bearer
            final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);
        }

        final Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BuildConfig.BASE_URL);
        builder.addConverterFactory(MoshiConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.client(httpClient.build());

        final Retrofit retrofit = builder.build();
        mApi = retrofit.create(TmdbApi.class);
    }

    @Provides
    public MovieDataSource providesMovieDataSource() {
        final MovieDataSource.LocalDataSource local = new MovieLocalDataSource();
        final MovieDataSource.RemoteDataSource remote = new MovieRemoteDataSource(mApi);
        return new MovieRepository(local, remote);
    }

}
