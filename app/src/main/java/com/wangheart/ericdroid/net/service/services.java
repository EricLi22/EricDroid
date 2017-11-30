package com.wangheart.ericdroid.net.service;

import com.wangheart.library.android.net.RetrofitManager;

/**
 * Author : eric
 * CreateDate : 2017/9/29  10:19
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public class services {
    public static UserService getUserService() {
       return RetrofitManager.getService(UserService.class);
    }
}
