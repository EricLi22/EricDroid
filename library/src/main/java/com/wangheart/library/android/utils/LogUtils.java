package com.wangheart.library.android.utils;

import android.util.Log;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

/**
 * 描述说明  <br/>
 * Author : eric <br/>
 * CreateDate : 2016/5/27 <br/>
 * Modified : eric <br/>
 * ModifiedDate :  2016/5/27 <br/>
 * Email : ericli_wang@163.com <br/>
 * Version 1.0
 */
public class LogUtils {
    public static final String TAG_DEFAULT = "eric";

    /**
     * 日志输出级别NONE
     */
    public static final int LEVEL_NONE = 0;
    /**
     * 日志输出级别V
     */
    public static final int LEVEL_VERBOSE = 1;
    /**
     * 日志输出级别D
     */
    public static final int LEVEL_DEBUG = 2;
    /**
     * 日志输出级别I
     */
    public static final int LEVEL_INFO = 3;
    /**
     * 日志输出级别W
     */
    public static final int LEVEL_WARN = 4;
    /**
     * 日志输出级别E
     */
    public static final int LEVEL_ERROR = 5;
    /**
     * 设置日志级别
     */
    private static int mDebuggable = LEVEL_ERROR;
    /**
     * 日志TAG
     */
    private static String mTag = TAG_DEFAULT;

    public static Settings init(int level, String tag) {
        mTag = tag;
        return init(level);
    }

    public static Settings init(int level) {
        mDebuggable = level;
        return init();
    }

    public static Settings init() {
        return Logger.init(mTag).logLevel(mDebuggable > LEVEL_NONE ? LogLevel.FULL : LogLevel.NONE);
    }

    public static void d(String msg) {
        if (mDebuggable >= LEVEL_DEBUG) {
            Log.d(mTag, msg);
        }
    }

    public static void e(String msg) {
        if (mDebuggable >= LEVEL_ERROR) {
            Log.e(mTag, msg);
        }
    }

    public static void w(String msg) {
        if (mDebuggable >= LEVEL_WARN) {
            Log.w(mTag, msg);
        }
    }

    public static void v(String msg) {
        if (mDebuggable >= LEVEL_VERBOSE) {
            Log.v(mTag, msg);
        }
    }

    public static void i(String msg) {
        if (mDebuggable >= LEVEL_INFO) {
            Log.i(mTag, msg);
        }
    }


}
