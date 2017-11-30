package com.wangheart.library.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.wangheart.library.android.holder.BaseListHolder;
import com.wangheart.library.android.utils.CollectionUtils;

import java.util.List;

/**
 * Author : eric
 * CreateDate : 2017/9/29  14:04
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public abstract class BaseListAdapter<T> extends RecyclerView.Adapter<BaseListHolder<T>> implements BaseListHolder.OnItemClickListener, BaseListHolder.OnItemLongClickListener {
    private List<T> mData;

    public List<T> getData() {
        return mData;
    }

    public BaseListAdapter(List<T> list) {
        mData = list;
    }

    /**
     * 获取总的条目数量
     */
    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return CollectionUtils.isEmpty(mData) ? 0 : mData.size();
    }

    @Override
    public void onItemClick(View view, int position) {
    }

    @Override
    public void onItemLongClick(View view, int position) {
    }

    public boolean isEmpty() {
        return mData == null || mData.size() <= 0;
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public BaseListHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseListHolder<T> holder = newViewHolder(parent, viewType);
        holder.setOnItemClickListener(this);
        holder.setOnItemLongClickListener(this);
        return holder;
    }

    protected abstract BaseListHolder<T> newViewHolder(ViewGroup parent, int viewType);

    /**
     * 将数据绑定到ViewHolder上
     */
    @Override
    public void onBindViewHolder(BaseListHolder<T> holder, int position) {
        // TODO Auto-generated method stub
        holder.setData(mData.get(position), position);
    }
}