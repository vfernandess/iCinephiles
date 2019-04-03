package com.arctouch.codechallenge.view.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

public class ListBinding {

    @BindingAdapter(value = {"positionStart", "itemCount"}, requireAll = true)
    public static void putItems(final RecyclerView view, final int positionStart, final int itemCount) {
        if (view == null || view.getAdapter() == null || positionStart == -1 || itemCount <= 0) {
            return;
        }

        view.getAdapter().notifyItemRangeInserted(positionStart, itemCount);
    }


}
