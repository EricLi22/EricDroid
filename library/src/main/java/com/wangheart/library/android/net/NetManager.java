package com.wangheart.library.android.net;

/**
 * Author : eric
 * CreateDate : 2017/11/30  10:30
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public class NetManager {
    private static int successCode = 0;
    private static boolean debug=false;

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        NetManager.debug = debug;
    }

    public static void setSuccessCode(int successCode) {
        NetManager.successCode = successCode;
    }

    public static int getSuccessCode() {
        return successCode;
    }
}
