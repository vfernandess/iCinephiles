package com.arctouch.codechallenge.view.feature.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.view.BaseActivity;
import com.arctouch.codechallenge.view.feature.home.view.HomeActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }, 2500L);
    }
}
