package com.arctouch.codechallenge.view.util;

import android.support.annotation.IntDef;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    public static final int DIRECTION_TOP = 0;
    public static final int DIRECTION_BOTTOM = 1;

    private LinearLayoutManager mLayoutManager;
    private int visibleThreshold;
    private int mLoadMoreDirection;
    private boolean loading;

    private OnLoadMoreListener mOnLoadMoreListener;

    public EndlessRecyclerViewScrollListener(final int threshold, @ScrollDirection final int direction) {
        visibleThreshold = threshold;
        mLoadMoreDirection = direction;
    }

    public void setLoadMoreListener(final OnLoadMoreListener listener) {
        mOnLoadMoreListener = listener;
    }

    public void setLayoutManager(final LinearLayoutManager layoutManager) {
        mLayoutManager = layoutManager;
    }

    @Override
    public void onScrollStateChanged(final RecyclerView recyclerView, final int newState) {
        if (mLayoutManager == null) {
            if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                setLayoutManager((LinearLayoutManager) recyclerView.getLayoutManager());
            }
        }
    }

    @Override
    public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (mLayoutManager == null || mOnLoadMoreListener == null) {
            return;
        }

        final int totalItemCount = mLayoutManager.getItemCount();
        final int visibleItem = getVisibleItem();
        if (!loading
                && totalItemCount > 0
                && visibleItem > -1
                && hasToLoadMore(visibleItem, totalItemCount)) {
            mOnLoadMoreListener.onLoadMore();
            loading = true;
        }
    }

    private int getVisibleItem() {
        if (mLoadMoreDirection == DIRECTION_TOP) {
            return mLayoutManager.findFirstVisibleItemPosition();
        } else if (mLoadMoreDirection == DIRECTION_BOTTOM) {
            return mLayoutManager.findLastVisibleItemPosition();
        } else {
            return -1;
        }
    }

    private boolean hasToLoadMore(final int visibleItem, final int totalItemCount) {
        if (mLoadMoreDirection == DIRECTION_TOP) {
            return (visibleItem - visibleThreshold) <= 0;
        } else if (mLoadMoreDirection == DIRECTION_BOTTOM) {
            return totalItemCount <= (visibleItem + visibleThreshold);
        } else {
            return false;
        }
    }

    public void setLoading(final boolean loading) {
        this.loading = loading;
    }

    public boolean isLoading() {
        return loading;
    }

    @IntDef({
            DIRECTION_TOP,
            DIRECTION_BOTTOM
    })
    public @interface ScrollDirection {

    }

    public interface OnLoadMoreListener {

        void onLoadMore();

    }

}

