package com.arctouch.codechallenge.view;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


public abstract class BindableAdapter<VDB extends ViewDataBinding> extends RecyclerView.Adapter<BindableViewHolder<VDB>> {

    @Override
    public BindableViewHolder<VDB> onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final VDB binding = inflate(parent.getContext());
        return new BindableViewHolder<>(binding);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(final BindableViewHolder holder, final int position) {
        final VDB binding = (VDB) holder.getViewBinding();
        onBindViewHolder(holder, binding, position);
    }

    protected abstract void onBindViewHolder(final BindableViewHolder holder, final VDB binding, final int position);

    protected abstract VDB inflate(final Context context);

}
