package com.wangheart.library.java.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 描述说明  <br/>
 * Author : eric <br/>
 * CreateDate : 2016/6/29 <br/>
 * Modified : eric <br/>
 * ModifiedDate :  2016/6/29 <br/>
 * Email : ericli_wang@163.com <br/>
 * Version 1.0
 */
public class JsonUtil {
    public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
}
