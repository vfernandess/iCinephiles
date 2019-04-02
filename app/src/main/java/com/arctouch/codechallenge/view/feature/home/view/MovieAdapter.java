package com.arctouch.codechallenge.view.feature.home.view;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arctouch.codechallenge.databinding.MovieItemBinding;
import com.arctouch.codechallenge.view.BindableAdapter;
import com.arctouch.codechallenge.view.BindableViewHolder;
import com.arctouch.codechallenge.view.feature.home.viewmodel.MovieItemViewModel;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class MovieAdapter extends BindableAdapter<MovieItemBinding> {

    private MovieItemDataSource mMovieDataSource;

    @Inject
    MovieAdapter() {
    }

    public void setDataSource(final MovieItemDataSource source) {
        mMovieDataSource = source;
    }

    @Override
    public int getItemCount() {
        if (mMovieDataSource == null) {
            return 0;
        }

        return mMovieDataSource.getMovieCount();
    }

    @Override
    protected void onBindViewHolder(final BindableViewHolder holder, final MovieItemBinding binding, final int position) {
        binding.setViewModel(mMovieDataSource.getItemViewModel(position));
    }

    @Override
    protected MovieItemBinding inflate(final Context context, @NonNull final ViewGroup parent) {
        return MovieItemBinding.inflate(LayoutInflater.from(context), parent, false);
    }


    public interface MovieItemDataSource {

        int getMovieCount();

        MovieItemViewModel getItemViewModel(final int position);

    }

}
