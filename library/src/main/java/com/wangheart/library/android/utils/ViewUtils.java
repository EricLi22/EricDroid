package com.wangheart.library.android.utils;

import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Author : eric
 * CreateDate : 2016/9/5  15:24
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
public class ViewUtils {
    public static void setEnable(View view, boolean isEnable) {
        if (view == null)
            return;
        view.setEnabled(isEnable);
    }

//    public static void setTextColor(TextView view, @ColorRes int colorId) {
//        if (view == null)
//            return;
//        view.setTextColor(ResUtils.getColor(colorId));
//    }

    public static void setGone(View view, boolean isGone) {
        if (view == null)
            return;
        view.setVisibility(isGone ? View.GONE : View.VISIBLE);
    }

    public static void setInVisible(View view, boolean isInVisible) {
        if (view == null)
            return;
        view.setVisibility(isInVisible ? View.INVISIBLE : View.VISIBLE);
    }

    public static void setText(TextView view, String str) {
        if (view == null)
            return;
        view.setText(str == null ? "" : str);
    }

    public static void setImageView(ImageView imageView, @DrawableRes int id){
        if (imageView == null)
            return;
        imageView.setImageResource(id);
    }

//    public static View inflate(@LayoutRes int layoutId) {
//        return View.inflate(UIUtils.getContext(), layoutId, null);
//    }

    public static boolean isTextNull(EditText editText){
        boolean flag;
        String text = editText.getText().toString().trim();
        if (TextUtils.isEmpty(text)){
            flag = true;
        }else {
            flag = false;
        }
        return flag;
    }
}
