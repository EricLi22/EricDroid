package com.wangheart.library.android.utils;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.wangheart.library.android.app.BaseApplication;

import java.util.List;

/**
 * Author : eric
 * CreateDate : 16/9/3  下午1:32
 * Email : ericli_wang@163.com
 * Version : 1.0
 * Desc :
 * Modified :
 */
public class UIUtils {
    public static BaseApplication getContext() {
        return BaseApplication.getInstance();
    }

    public static void addOrShowFragment(FragmentManager fragmentManager, List<Fragment> fragmentList, Fragment fragment, @IdRes int id, String TAG) {
        for (Fragment fm : fragmentList) {
            fragmentManager.beginTransaction().hide(fm).commit();
        }
        if (fragmentManager.findFragmentByTag(TAG) == null) {
            fragmentManager.beginTransaction().add(id, fragment, TAG).commit();
        } else {
            fragmentManager.beginTransaction().show(fragment).commit();
        }
    }

    private static Activity mHomeActivity;

    public static void setHomeActivity(Activity activity) {
        mHomeActivity = activity;
    }

    public static Activity getHomeActivity() {
        return mHomeActivity;
    }
}
