package com.wangheart.library.android.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

/**
 * Author : eric
 * CreateDate : 2017/9/29  13:49
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public class PhoneUtils {

    public static String getTelNumber() {
        TelephonyManager tm = (TelephonyManager) UIUtils.getContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null) {
            return "";
        }
        return TextUtils.isEmpty(tm.getLine1Number()) ? "" : tm.getLine1Number();
    }


    public static boolean GTE_HC;
    public static boolean GTE_ICS;
    public static boolean PRE_HC;
    private static Boolean _hasCamera = null;
    private static Boolean _isTablet = null;
    public static float displayDensity = 0.0F;

    static {
        GTE_ICS = Build.VERSION.SDK_INT >= 14;
        GTE_HC = Build.VERSION.SDK_INT >= 11;
        PRE_HC = Build.VERSION.SDK_INT < 11;
    }

    public static float getDensity() {
        if (displayDensity == 0.0)
            displayDensity = getDisplayMetrics().density;
        return displayDensity;
    }

    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) UIUtils.getContext().getSystemService(
                Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
                displaymetrics);
        return displaymetrics;
    }

    public static float getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    public static float getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    public static int[] getRealScreenSize(Activity activity) {
        int[] size = new int[2];
        int screenWidth = 0, screenHeight = 0;
        WindowManager w = activity.getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        // since SDK_INT = 1;
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
        // includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
            try {
                screenWidth = (Integer) Display.class.getMethod("getRawWidth")
                        .invoke(d);
                screenHeight = (Integer) Display.class
                        .getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored) {
            }
        // includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 17)
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d,
                        realSize);
                screenWidth = realSize.x;
                screenHeight = realSize.y;
            } catch (Exception ignored) {
            }
        size[0] = screenWidth;
        size[1] = screenHeight;
        return size;
    }

    public static int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            return UIUtils.getContext().getResources()
                    .getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean hasCamera() {
        if (_hasCamera == null) {
            PackageManager pckMgr = UIUtils.getContext()
                    .getPackageManager();
            boolean flag = pckMgr
                    .hasSystemFeature("android.hardware.camera.front");
            boolean flag1 = pckMgr.hasSystemFeature("android.hardware.camera");
            boolean flag2;
            flag2 = flag || flag1;
            _hasCamera = flag2;
        }
        return _hasCamera;
    }

    public static boolean hasInternet() {
        boolean flag;
        flag = ((ConnectivityManager) UIUtils.getContext().getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
        return flag;
    }


    public static boolean isPackageExist(String pckName) {
        try {
            PackageInfo pckInfo = UIUtils.getContext().getPackageManager()
                    .getPackageInfo(pckName, 0);
            if (pckInfo != null)
                return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isLandscape() {
        return UIUtils.getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public static boolean isPortrait() {
        return UIUtils.getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static boolean isTablet() {
        if (_isTablet == null) {
            boolean flag;
            flag = (0xf & UIUtils.getContext().getResources()
                    .getConfiguration().screenLayout) >= 3;
            _isTablet = flag;
        }
        return _isTablet;
    }

    public static void showSoftKeyboard(View view) {
        ((InputMethodManager) UIUtils.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE)).showSoftInput(view,
                InputMethodManager.SHOW_FORCED);
    }

    public static void hideSoftKeyboard(View view) {
        if (view == null)
            return;
        ((InputMethodManager) UIUtils.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                view.getWindowToken(), 0);
    }

    public static void toogleSoftKeyboard(View view) {
        ((InputMethodManager) UIUtils.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE)).toggleSoftInput(0,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static boolean isSdcardReady() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }

    public static void setFullScreen(Activity activity) {
        WindowManager.LayoutParams params = activity.getWindow()
                .getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        activity.getWindow().setAttributes(params);
        activity.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public static void cancelFullScreen(Activity activity) {
        WindowManager.LayoutParams params = activity.getWindow()
                .getAttributes();
        params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().setAttributes(params);
        activity.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public static PackageInfo getPackageInfo(String pckName) {
        try {
            return UIUtils.getContext().getPackageManager()
                    .getPackageInfo(pckName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getVersionCode() {
        int versionCode = 0;
        try {
            versionCode = UIUtils.getContext()
                    .getPackageManager()
                    .getPackageInfo(UIUtils.getContext().getPackageName(),
                            0).versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            versionCode = 0;
        }
        return versionCode;
    }

    public static int getVersionCode(String packageName) {
        int versionCode;
        try {
            versionCode = UIUtils.getContext().getPackageManager()
                    .getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            versionCode = 0;
        }
        return versionCode;
    }

    public static String getVersionName() {
        String name;
        try {
            name = UIUtils.getContext()
                    .getPackageManager()
                    .getPackageInfo(UIUtils.getContext().getPackageName(),
                            0).versionName;
        } catch (PackageManager.NameNotFoundException ex) {
            name = "";
        }
        return name;
    }

    public static boolean isScreenOn() {
        PowerManager pm = (PowerManager) UIUtils.getContext()
                .getSystemService(Context.POWER_SERVICE);
        return pm.isScreenOn();
    }

    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void uninstallApk(Context context, String packageName) {
        if (isPackageExist(packageName)) {
            Uri packageURI = Uri.parse("package:" + packageName);
            Intent uninstallIntent = new Intent(Intent.ACTION_DELETE,
                    packageURI);
            context.startActivity(uninstallIntent);
        }
    }

    public static Intent getInstallApkIntent(File file) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        return intent;
    }

    public static boolean isWifiConnect() {
        boolean isWifiConnect = false;
        ConnectivityManager cm = (ConnectivityManager) UIUtils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        // check the networkInfos numbers
        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
        for (NetworkInfo networkInfo : networkInfos) {
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    isWifiConnect = false;
                }
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    isWifiConnect = true;
                }
            }
        }
        return isWifiConnect;
    }

    public static int getStatuBarHeight() {
        Class<?> c;
        Object obj;
        Field field;
        int x, sbar = 38;// 默认为38，貌似大部分是这样的
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = UIUtils.getContext().getResources()
                    .getDimensionPixelSize(x);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }


    public static boolean hasStatusBar(Activity activity) {
        WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
        return (attrs.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    /**
     * 判断指定包名的进程是否运行
     *
     * @param context
     * @param packageName 指定包名
     * @return 是否运行
     */
    public static boolean isRunning(Context context, String packageName) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo rapi : infos) {
            if (rapi.processName.equals(packageName))
                return true;
        }
        return false;
    }

    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public static boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device

        Context context = UIUtils.getContext();

//        ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
//        String packageName = context.getApplicationContext().getPackageName();

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = context.getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        int i = 0;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            i = i + 1;
            Logger.e(" 得到所有后台的程序 %s , %s , 所有后台 %s %s", i, packageName, i, appProcess.processName);

            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {

                Logger.e(" 得到 importance %s ", appProcess.importance);

                return true;
            }
        }

        return false;
    }

    /**
     * 判断当前应用程序处于前台还是后台
     */
    public static boolean isApplicationBroughtToBackground() {
        ActivityManager am = (ActivityManager) UIUtils.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(UIUtils.getContext().getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 用来判断服务是否运行.
     *
     * @param context
     * @param className 判断的服务名字
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceRunning(Context context, String className) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(300);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className)) {
                return true;
            }
        }
        return false;
    }

    public static String getIpAddress() {
        String ipAddress = null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface networkInterface = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = networkInterface.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ipAddress = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ipAddress = null;
        }
        return ipAddress;
    }


    private static boolean hasNavigationBar = false;
    private static boolean isTestHasNavigationBar = false;

    public static synchronized boolean isHaveNavigationBar(Context context) {
        if (!isTestHasNavigationBar) {
            boolean isHave = false;
            Resources rs = context.getResources();
            int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
            if (id > 0) {
                isHave = rs.getBoolean(id);
            }
            try {
                Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
                Method m = systemPropertiesClass.getMethod("get", String.class);
                String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
                if ("1".equals(navBarOverride)) {
                    isHave = false;
                } else if ("0".equals(navBarOverride)) {
                    isHave = true;
                }
            } catch (Exception e) {
                Log.w("TAG", e);
            }
            isTestHasNavigationBar = true;
            hasNavigationBar = isHave;
            return hasNavigationBar;
        } else {
            return hasNavigationBar;
        }
    }

}
