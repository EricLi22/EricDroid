package com.wangheart.ericdroid.net.service;

import com.wangheart.ericdroid.entity.User;
import com.wangheart.library.android.net.result.Result;

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

public interface UserService {
    @GET("user.json")
    Observable<Result<User>> getTestJson();
}
