package com.wangheart.library.android.widget.listener;

/**
 * Author : eric
 * CreateDate : 2017/9/29  11:40
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public interface RefreshHandle {
    void setRefreshing(boolean refreshing);

    boolean isRefreshing();

    void setEnabled(boolean enable);
}
