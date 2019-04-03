package com.arctouch.codechallenge.view;


import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public class BindableViewHolder<VDB extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private final VDB mViewBinding;

    public BindableViewHolder(final VDB binding) {
        super(binding.getRoot());
        mViewBinding = binding;
        mViewBinding.executePendingBindings();
    }

    public VDB getViewBinding() {
        return mViewBinding;
    }


}
