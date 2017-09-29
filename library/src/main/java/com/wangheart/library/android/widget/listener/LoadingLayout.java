package com.wangheart.library.android.widget.listener;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * Author : eric
 * CreateDate : 2017/9/29  11:48
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public class LoadingLayout extends SwipeRefreshLayout implements RefreshHandle {
    public LoadingLayout(Context context) {
        super(context);
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
