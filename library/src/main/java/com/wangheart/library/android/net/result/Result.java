package com.wangheart.library.android.net.result;

/**
 * 描述说明  <br/>
 * Author : eric <br/>
 * CreateDate : 2016/6/29 <br/>
 * Modified : eric <br/>
 * ModifiedDate :  2016/6/29 <br/>
 * Email : ericli_wang@163.com <br/>
 * Version 1.0
 */
public class Result<T> extends BaseResult {
    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "datas=" + data +
                '}';
    }
}
