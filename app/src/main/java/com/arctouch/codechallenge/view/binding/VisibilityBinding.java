package com.arctouch.codechallenge.view.binding;

import android.databinding.BindingAdapter;
import android.view.View;

public class VisibilityBinding {


    @BindingAdapter("visible")
    public static void setVisible(final View view, final Boolean visible) {
        if (visible == null) {
            view.setVisibility(View.GONE);
        } else if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    @BindingAdapter("gone")
    public static void setInvertVisible(final View view, final Boolean gone) {
        if (gone == null) {
            view.setVisibility(View.VISIBLE);
        } else if (gone) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }


}
