package com.arctouch.codechallenge.view.binding;

import android.databinding.BindingAdapter;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;

public class ShimmerBinding {

    @BindingAdapter("shimmer_enabled")
    public static void setShimmerEnabled(final ShimmerFrameLayout shimmer, final boolean enabled) {
        if (enabled) {
            shimmer.setVisibility(View.VISIBLE);
            shimmer.startShimmer();
        } else {
            shimmer.stopShimmer();
            shimmer.setVisibility(View.GONE);
        }
    }

}
