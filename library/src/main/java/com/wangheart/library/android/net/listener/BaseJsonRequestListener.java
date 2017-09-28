package com.wangheart.library.android.net.listener;


import com.wangheart.library.android.net.BaseResult;
import com.wangheart.library.android.net.NetManager;
import com.wangheart.library.android.utils.LogUtils;
import com.wangheart.library.android.utils.ToastUtils;

import java.net.SocketTimeoutException;

/**
 * Author : eric
 * CreateDate : 2016/9/5  11:39
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc : 网络请求回调的积累
 * Modified :
 */
public abstract class BaseJsonRequestListener<RESULT extends BaseResult> {

    public void onRequestStart() {
    }

    public void onRequestComplete() {
    }

    public void onRequestFailure(Exception exception) {
        LogUtils.e("onRequestFailure ->" + exception);
        exception.printStackTrace();
        if (NetManager.isDebug()) {
            ToastUtils.showShortTimeMsg(exception.getMessage());
        } else {
            if (exception instanceof SocketTimeoutException) {
                ToastUtils.showShortTimeMsg("网络连接失败，请检查网络!");
            } else {
                ToastUtils.showShortTimeMsg("发生异常!");
            }
        }
    }

    public void onRequestSuccess(RESULT result) {
        if (!result.isSuccess()) {
            ToastUtils.showLongTimeMsg(result.getMsg());
            switch (result.getCode()) {
                case 401:
                case 402:
                    break;
            }
        }
    }
}