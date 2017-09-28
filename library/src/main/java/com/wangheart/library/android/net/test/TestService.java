package com.wangheart.library.android.net.test;

import com.wangheart.library.android.net.EmptyResult;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Author : eric
 * CreateDate : 2017/9/28  18:31
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public interface TestService {
    @GET("html/test.json")
    Observable<EmptyResult> getTestJson();
}
