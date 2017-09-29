package com.wangheart.library.android.holder;

import android.view.View;
import android.widget.LinearLayout;

import com.wangheart.library.R2;
import com.wangheart.library.android.utils.ViewUtils;

import butterknife.BindView;

/**
 * 描述说明  <br/>
 * CreateDate : 2016/5/31 <br/>
 * ModifiedDate :  2016/5/31 <br/>
 * Email : ericli_wang@163.com <br/>
 * Version 1.0
 */
public class LoadMoreListHolder extends BaseListHolder<Integer> {
    public static final int STATE_NORMAL = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_LOADING_FAILED = 2;
    public static final int STATE_NO_MORE = 3;
    @BindView(R2.id.ll_normal)
    LinearLayout llNormal;
    @BindView(R2.id.ll_loading)
    LinearLayout llLoading;
    @BindView(R2.id.ll_load_failed)
    LinearLayout llLoadFailed;
    @BindView(R2.id.ll_load_no_more)
    LinearLayout llLoadNoMore;
    public LoadMoreListHolder(View itemView) {
        super(itemView);
        setData(STATE_NORMAL);
    }

    @Override
    public void setData(Integer integer) {
        super.setData(integer);
    }

    public boolean canLoad() {
        return mData == STATE_NORMAL || mData == STATE_LOADING_FAILED;
    }

    @Override
    public void refresh() {
        switch (mData) {
            case STATE_NORMAL:
                break;
            case STATE_LOADING:
                break;
            case STATE_LOADING_FAILED:
                break;
            case STATE_NO_MORE:
                break;
        }
        ViewUtils.setGone(llNormal, mData != STATE_NORMAL);
        ViewUtils.setGone(llLoading, mData != STATE_LOADING);
        ViewUtils.setGone(llLoadFailed, mData != STATE_LOADING_FAILED);
        ViewUtils.setGone(llLoadNoMore, mData != STATE_NO_MORE);
    }
}
