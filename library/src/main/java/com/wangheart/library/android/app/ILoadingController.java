package com.wangheart.library.android.app;

/**
 * Author : eric
 * CreateDate : 2016/9/28  17:13
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public interface ILoadingController {
    void showLoading();

    void hideLoading();

    void showContent();

    void showEmpty();

    void showError();

    void setEmpty(String text);

    void setError(String text);
}
