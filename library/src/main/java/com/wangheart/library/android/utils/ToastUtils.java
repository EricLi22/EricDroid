package com.wangheart.library.android.utils;

import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;


/**
 * 描述说明  <br/>
 * Author : liwang <br/>
 * CreateDate : 2016/6/23 <br/>
 * Modified : liwang <br/>
 * ModifiedDate :  2016/6/23 <br/>
 * Email : ericli_wang@163.com <br/>
 * Version 1.0
 */
public class ToastUtils {
    /**
     * 显示duration为 Toast.LENGTH_LONG 的Toast
     *
     * @param text 显示的内容
     */
    public static void showLongTimeMsg(CharSequence text) {
        if (TextUtils.isEmpty(text)) return;
        Toast.makeText(UIUtils.getContext(), text, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示duration为 Toast.LENGTH_LONG 的Toast
     *
     * @param resId 显示内容的资源ID
     */
    public static void showLongTimeMsg(@StringRes int resId) {
        Toast.makeText(UIUtils.getContext(), resId, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示duration为 Toast.LENGTH_SHORT 的Toast
     *
     * @param text 显示的内容
     */
    public static void showShortTimeMsg(CharSequence text) {
        if (TextUtils.isEmpty(text)) return;
        Toast.makeText(UIUtils.getContext(), text + "", Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示duration为 Toast.LENGTH_SHORT 的Toast
     *
     * @param resId 显示内容的资源ID
     */
    public static void showShortTimeMsg(@StringRes int resId) {
        Toast.makeText(UIUtils.getContext(), resId, Toast.LENGTH_SHORT).show();
    }
}
