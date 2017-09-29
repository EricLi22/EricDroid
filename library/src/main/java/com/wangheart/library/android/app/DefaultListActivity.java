package com.wangheart.library.android.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;

import com.wangheart.library.R;
import com.wangheart.library.R2;
import com.wangheart.library.android.widget.listener.LoadingLayout;

import butterknife.BindView;

/**
 * Author : eric
 * CreateDate : 2017/9/29  14:31
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public abstract class DefaultListActivity<DATA> extends  BaseListActivity<LoadingLayout , DATA> {
    @BindView(R2.id.loading_layout)
    LoadingLayout loadingLayout;

    @Override
    protected View initContentView(View contentView) {
        View mainLayout = View.inflate(this, R.layout.eric_layout_loading, null);
        ViewGroup contentLayout = (ViewGroup) mainLayout.findViewById(R.id.fl_container);
        contentLayout.addView(contentView, 0);
        return super.initContentView(mainLayout);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setRefreshHandle(loadingLayout);
        loadingLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }
}
