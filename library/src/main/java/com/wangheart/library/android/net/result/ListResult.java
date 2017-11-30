package com.wangheart.library.android.net.result;

import java.util.List;

/**
 * Author : eric
 * CreateDate : 2017/11/30  10:42
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public class ListResult<T> extends BaseResult {
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
