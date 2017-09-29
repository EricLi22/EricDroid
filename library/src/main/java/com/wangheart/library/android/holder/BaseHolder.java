package com.wangheart.library.android.holder;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Author : eric
 * CreateDate : 2017/9/29  14:01
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public abstract class BaseHolder<T> {
    protected View mRootView;
    protected T mData;
    protected int mPosition = -1;

    public BaseHolder() {
        mRootView = initView();
        // 打标记
        ButterKnife.bind(this, mRootView);
        mRootView.setTag(this);
        postAfterView();
    }


    public void postAfterView() {

    }

    /**
     * 实现初始化View
     *
     * @return
     */
    protected abstract View initView();

    /**
     * 刷新UI，当设置数据后就会被调用
     *
     * @param data
     */
    protected abstract void refreshUI(T data);

    /**
     * 设置数据，并刷新UI
     *
     * @param data
     */
    public void setData(T data) {
        this.mData = data;
        // UI刷新
        refreshUI(mData);
    }

    public void setData(T data, int position) {
        this.mData = data;
        mPosition = position;
        // UI刷新
        refreshUI(mData);
    }

    public View getRootView() {
        return mRootView;
    }
}
