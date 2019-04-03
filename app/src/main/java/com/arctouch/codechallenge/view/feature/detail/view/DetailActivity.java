package com.arctouch.codechallenge.view.feature.detail.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.databinding.ActivityDetailBinding;
import com.arctouch.codechallenge.view.BaseActivity;
import com.arctouch.codechallenge.view.feature.detail.viewmodel.DetailViewModel;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity {

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    public static void start(Context context, final long movieID) {
        Intent starter = new Intent(context, DetailActivity.class);
        starter.putExtra(EXTRA_MOVIE, movieID);
        context.startActivity(starter);
    }

    @Inject
    DetailViewModel mViewModel;

    private ActivityDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        mBinding.setViewModel(mViewModel);

        setSupportActionBar(mBinding.toolbar);

        mViewModel.load(getIntent().getLongExtra(EXTRA_MOVIE, -1));
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
