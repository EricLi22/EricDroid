package com.wangheart.ericdroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangheart.ericdroid.entity.User;
import com.wangheart.library.android.app.DefaultListActivity;
import com.wangheart.library.android.holder.BaseListHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : eric
 * CreateDate : 2017/9/29  14:26
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public class UserListActivity extends DefaultListActivity<User> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        loadData();
    }

    @Override
    protected void loadData() {
        User user = new User();
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user);
        list.add(user);
        list.add(user);
        list.add(user);
        list.add(user);
        list.add(user);
        list.add(user);
        list.add(user);
        list.add(user);
        list.add(user);
        list.add(user);
        setData(list);
    }

    @Override
    protected void loadMoreData() {

    }

    @Override
    protected BaseListHolder<User> newViewHolder(ViewGroup parent, int viewType) {
        return new UserHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false));
    }

    protected class UserHolder extends BaseListHolder<User>{

        public UserHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void refresh() {

        }
    }
}
