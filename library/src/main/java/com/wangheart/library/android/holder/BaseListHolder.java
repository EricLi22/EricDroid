package com.wangheart.library.android.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
/**
 * Author : eric
 * CreateDate : 2017/9/29  14:02
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public abstract class BaseListHolder <T> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    protected T mData;
    protected int mPosition;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public BaseListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }
    public void setData(T t, int position) {
        mData = t;
        mPosition = position;
        refresh();
    }

    public void setData(T t) {
        mData = t;
        mPosition = -1;
        refresh();
    }

    public View getRootView() {
        return itemView;
    }

    public abstract void refresh();


    @Override
    public void onClick(View v) {
        if (onItemClickListener != null)
            onItemClickListener.onItemClick(v, getLayoutPosition());
    }

    @Override
    public boolean onLongClick(View v) {
        if (onItemLongClickListener != null)
            onItemLongClickListener.onItemLongClick(v, getLayoutPosition());
        return true;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int postion);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int postion);
    }
}

