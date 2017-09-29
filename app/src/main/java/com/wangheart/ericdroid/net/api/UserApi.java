package com.wangheart.ericdroid.net.api;

import com.wangheart.ericdroid.entity.User;
import com.wangheart.library.android.app.LifecycleProvider;
import com.wangheart.library.android.net.Result;
import com.wangheart.library.android.net.api.BaseApi;
import com.wangheart.library.android.net.listener.BaseJsonRequestListener;

import static com.wangheart.ericdroid.net.service.services.getUserService;

/**
 * Author : eric
 * CreateDate : 2017/9/29  10:04
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public class UserApi extends BaseApi {
    public static void getUser(LifecycleProvider lifecycleProvider, BaseJsonRequestListener<Result<User>> listener) {
        subscribeOnUiThread(getUserService().getTestJson(), lifecycleProvider, listener);
    }
}
