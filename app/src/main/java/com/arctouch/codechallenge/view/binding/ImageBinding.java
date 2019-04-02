package com.arctouch.codechallenge.view.binding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class ImageBinding {

    @BindingAdapter(value = {"srcURL", "placeholder"}, requireAll = false)
    public static void setImageURI(final ImageView view, final String url, final Drawable placeholder) {
        if(view == null) {
            return;
        }

        if (TextUtils.isEmpty(url)) {
            view.setImageDrawable(placeholder);
        } else {
            Glide.with(view)
                    .load(url)
                    .apply(new RequestOptions().placeholder(placeholder))
                    .into(view);
        }
    }

}
