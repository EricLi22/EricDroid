package com.wangheart.library.android.app;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wangheart.library.android.widget.listener.RefreshHandle;

/**
 * Author : eric
 * CreateDate : 2017/9/29  11:22
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public abstract class BaseLoadingActivity<T extends RefreshHandle> extends BaseActivity implements ILoadingController {
    protected T refreshHandle;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    protected void setRefreshHandle(T t) {
        this.refreshHandle = t;

    }


    protected void setRefreshEnable(boolean enable) {
        if (refreshHandle != null)
            refreshHandle.setEnabled(enable);
    }

    protected abstract void loadData();


    @Override
    public void showLoading() {
        if (refreshHandle != null)
            refreshHandle.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        if (refreshHandle != null)
            refreshHandle.setRefreshing(true);
    }

    @Override
    public void showContent() {

    }

    @Override
    public void showEmpty() {
    }

    @Override
    public void showError() {

    }

    @Override
    public void setEmpty(String text) {

    }

    @Override
    public void setError(String text) {

    }
}
