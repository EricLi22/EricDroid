package com.wangheart.library.android.net.builder;


import com.wangheart.library.android.net.OkHttpManager;
import com.wangheart.library.android.net.request.OtherRequest;
import com.wangheart.library.android.net.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpManager.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
