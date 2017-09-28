package com.wangheart.library.android.app;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleTransformer;


/**
 * Author : eric
 * CreateDate : 2016/9/1  14:57
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
public interface LifecycleProvider {
    @NonNull
    @CheckResult
    <T> LifecycleTransformer<T> bindToLifecycle();
}
