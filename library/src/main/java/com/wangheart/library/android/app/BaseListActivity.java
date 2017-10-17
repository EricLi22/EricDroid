package com.wangheart.library.android.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangheart.library.R;
import com.wangheart.library.R2;
import com.wangheart.library.android.holder.BaseListHolder;
import com.wangheart.library.android.holder.LoadMoreListHolder;
import com.wangheart.library.android.utils.UIUtils;
import com.wangheart.library.android.widget.divider.HorizontalDividerItemDecoration;
import com.wangheart.library.android.widget.listener.RefreshHandle;
import com.wangheart.library.java.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author : eric
 * CreateDate : 2017/9/29  13:57
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public abstract class BaseListActivity<T extends RefreshHandle, DATA> extends BaseLoadingActivity<T> {
    @BindView(R2.id.rv_list)
    protected RecyclerView rvList;
    private BaseListAdapter mAdapter;
    //list数据
    private List<DATA> mData;
    private LoadMoreListHolder loadMoreHolder;
    private ListDataSetObserver mListDataSetObserver;
    //参数配置
    private ParamBuilder mParamBuilder;
    //默认分页大小
    private final int PAGE_SIZE = 15;
    //分页的大小
    private int mPageSize = PAGE_SIZE;
    //list背景
    private int mRvBg;
    //分割线
    private RecyclerView.ItemDecoration mItemDecoration;
    //是否加载更多
    private boolean mIsLoadMoreEnable = false;


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mParamBuilder = paramBuilder();
        //加载配置
        buildParam(mParamBuilder);
        mListDataSetObserver = new ListDataSetObserver();
        if (mRvBg < 0)
            rvList.setBackgroundColor(mRvBg);
        mData = new ArrayList<>();
        mAdapter = new BaseListAdapter();
        rvList.setLayoutManager(new LinearLayoutManager(getThis()));
        //分隔线
        rvList.addItemDecoration(mItemDecoration);
        //加载更多布局
        loadMoreHolder = new LoadMoreListHolder(LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.layout_load_more, rvList, false));
        loadMoreHolder.setData(mIsLoadMoreEnable ? LoadMoreListHolder.STATE_NORMAL : LoadMoreListHolder.STATE_NO_MORE);
        //观察数据
        mAdapter.registerAdapterDataObserver(mListDataSetObserver);
        mAdapter.setOnItemClickListener(new BaseListHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                BaseListActivity.this.onItemClick(view, position);
            }
        });
        mAdapter.setOnItemLongClickListener(new BaseListHolder.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                BaseListActivity.this.onItemLongClick(view, position);
            }
        });
        rvList.setAdapter(mAdapter);
        /**
         * 加载更多配置
         */
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (CollectionUtils.isEmpty(mData))
                    return;
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!mIsLoadMoreEnable)
                        return;
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int lastVisiblePosition = manager.findLastVisibleItemPosition();
                    if (lastVisiblePosition >= manager.getItemCount() - 1) {
                        if (loadMoreHolder.canLoad())
                            loadMoreData();
                    }
                }
            }
        });
    }

    /**
     * 加载更多事件回调
     */
    abstract protected void loadMoreData();


    public class BaseListAdapter extends RecyclerView.Adapter<BaseListHolder<DATA>> {

        private BaseListHolder.OnItemClickListener mOnItemClickListener;
        private BaseListHolder.OnItemLongClickListener mOnItemLongClickListener;

        public void setOnItemClickListener(BaseListHolder.OnItemClickListener mOnItemClickListener) {
            this.mOnItemClickListener = mOnItemClickListener;
        }

        public void setOnItemLongClickListener(BaseListHolder.OnItemLongClickListener mOnItemLongClickListener) {
            this.mOnItemLongClickListener = mOnItemLongClickListener;
        }

        public BaseListAdapter() {

        }

        /**
         * 获取总的条目数量
         */
        @Override
        public int getItemCount() {
            // TODO Auto-generated method stub
            return CollectionUtils.isEmpty(mData) ? 0 : (mIsLoadMoreEnable ? mData.size() + 1 : mData.size());
        }

        @Override
        public int getItemViewType(int position) {
            /**
             * 是否有加载更多
             */
            if (mIsLoadMoreEnable) {
                if (position >= mData.size())
                    return super.getItemViewType(position) + 1;
            }
            return super.getItemViewType(position);
        }

        /**
         * 创建ViewHolder
         */
        @Override
        public BaseListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 0) {
                BaseListHolder<DATA> holder = newViewHolder(parent, viewType);
                holder.setOnItemClickListener(mOnItemClickListener);
                holder.setOnItemLongClickListener(mOnItemLongClickListener);
                return holder;
            } else {
                if (loadMoreHolder.getRootView().getParent() != null) {
                    ((ViewGroup) loadMoreHolder.getRootView().getParent()).removeView(loadMoreHolder.getRootView());
                }
                return loadMoreHolder;
            }
        }

        /**
         * 将数据绑定到ViewHolder上
         */
        @Override
        public void onBindViewHolder(BaseListHolder holder, int position) {
            // TODO Auto-generated method stub
            if (!(holder instanceof LoadMoreListHolder)) {
                holder.setData(mData.get(position), position);
            }
        }
    }

    protected abstract BaseListHolder<DATA> newViewHolder(ViewGroup parent, int viewType);


    protected void onItemClick(View view, int position) {

    }

    protected void onItemLongClick(View view, int position) {

    }


    protected ParamBuilder paramBuilder() {
        return generateParamBuilder();
    }

    final protected ParamBuilder generateParamBuilder() {
        return new ParamBuilder(getThis());
    }

    /**
     * 刷新数据
     *
     * @param data
     */
    protected void setData(List<DATA> data) {
        mData.clear();
        if (!CollectionUtils.isEmpty(data))
            mData.addAll(data);
        mAdapter.notifyDataSetChanged();
        loadMoreHolder.setData(data.size() < mPageSize ? LoadMoreListHolder.STATE_NO_MORE : LoadMoreListHolder.STATE_NORMAL);
    }

    public List<DATA> getData() {
        return mData;
    }

    /**
     * 添加更多数据
     *
     * @param data
     */
    protected void showMore(List<DATA> data) {
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
        loadMoreHolder.setData(data.size() < mPageSize ? LoadMoreListHolder.STATE_NO_MORE : LoadMoreListHolder.STATE_NORMAL);
    }


    /**
     * 刷新数据
     */
    protected void notifyDataSetChanged() {
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }


    /**
     * RecyclerView属性构造器
     */
    public final static class ParamBuilder {
        int listBg;
        RecyclerView.ItemDecoration itemDecoration;
        boolean isLoadMoreEnable = false;

        public ParamBuilder(Context context) {
            itemDecoration = new HorizontalDividerItemDecoration.Builder(context)
                    .color(UIUtils.getColor(R.color.divider))
                    .sizeResId(R.dimen.list_dividers)
                    .build();
        }

        public ParamBuilder bgList(@ColorRes int bg) {
            this.listBg = bg;
            return this;
        }

        public ParamBuilder loadMoreEnable(boolean isLoadMoreEnable) {
            this.isLoadMoreEnable = isLoadMoreEnable;
            return this;
        }

        public ParamBuilder itemDecoration(RecyclerView.ItemDecoration itemDecoration) {
            this.itemDecoration = itemDecoration;
            return this;
        }
    }

    /**
     * 将ParamBuild的参数配置到ListActivity。
     *
     * @param paramBuilder
     */
    private void buildParam(ParamBuilder paramBuilder) {
        if (paramBuilder == null) {
            throw new RuntimeException("ParamBuild is null");
        }
        this.mRvBg = paramBuilder.listBg;
        mItemDecoration = paramBuilder.itemDecoration;
        mIsLoadMoreEnable = paramBuilder.isLoadMoreEnable;
    }

    /**
     * 观察list数据
     */
    class ListDataSetObserver extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            if (CollectionUtils.isEmpty(mData)) {
                showEmpty();
            } else {
                showContent();
            }
        }
    }
}
