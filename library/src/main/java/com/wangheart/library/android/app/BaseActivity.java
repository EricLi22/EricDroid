package com.wangheart.library.android.app;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wangheart.library.R2;
import com.wangheart.library.android.utils.PhoneUtils;
import com.wangheart.library.android.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author : eric
 * CreateDate : 2017/9/28  14:57
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

abstract public class BaseActivity extends RxAppCompatActivity implements LifecycleProvider{
    private View mContentView;
    @Nullable
    @BindView(R2.id.iv_common_back)
    ImageView ivBack;
    @Nullable
    @BindView(R2.id.tv_common_title)
    TextView tvTitle;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(initContentView(layoutResID));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        highApiEffects();
    }


    protected BaseActivity getThis(){
        return this;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void highApiEffects() {
        //透明状态栏 @顶部
//        getWindow().getDecorView().setFitsSystemWindows(true);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏 @底部    这一句不要加，目的是防止沉浸式状态栏和部分底部自带虚拟按键的手机（比如华为）发生冲突，注释掉就好了
        if (!PhoneUtils.isHaveNavigationBar(this)) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setTitle(getTitle());
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (tvTitle != null) {
            ViewUtils.setText(tvTitle, title.toString());
        }
    }


    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(initContentView(view), params);
    }

    protected View initContentView(int layoutResID) {
        return initContentView(getLayoutInflater().inflate(layoutResID, null));
    }

    protected View initContentView(View contentView) {
        mContentView = contentView;
        ButterKnife.bind(this, contentView);
        return contentView;
    }
}
