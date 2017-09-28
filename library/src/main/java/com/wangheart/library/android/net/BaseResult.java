package com.wangheart.library.android.net;


import static com.wangheart.library.android.net.NetManager.getSuccessCode;

/**
 * 描述说明  网络层返回结果的基类<br/>
 * Author : liwang <br/>
 * CreateDate : 2016/5/26 <br/>
 * Modified : liwang <br/>
 * ModifiedDate :  2016/5/26 <br/>
 * Email : ericli_wang@163.com <br/>
 * Version 1.0
 */
abstract public class BaseResult {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return code == getSuccessCode();
    }

    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
