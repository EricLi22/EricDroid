package com.wangheart.library.android.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.StringRes;

/**
 * Author : eric
 * CreateDate : 16/9/3  下午1:32
 * Email : ericli_wang@163.com
 * Version : 1.0
 * Desc :
 * Modified :
 */
public class UIUtils {
    public static Context getContext() {
        return AppUtils.getContext();
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    public static int getColor(@ColorRes int colorId) {
        return getResources().getColor(colorId);
    }

    public static String getString(@StringRes int stringId) {
        return getResources().getString(stringId);
    }

    public static String getString(@StringRes int stringId, Object... formatArgs) {
        return getResources().getString(stringId, formatArgs);
    }

    public float getDimen(@DimenRes int dimenRes) {
        return getResources().getDimension(dimenRes);
    }
}
