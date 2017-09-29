package com.wangheart.ericdroid.dao;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.wangheart.ericdroid.entity.User;
import com.wangheart.library.android.db.BaseDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : eric
 * CreateDate : 2017/9/29  10:56
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public class UserDao extends BaseDao {
    private static RuntimeExceptionDao<User, Integer> getGoodDao() {
        return DbHelper.getInstance().getUserDao();
    }

    public static void add(User user) {
        if (user != null) {
            getGoodDao().createOrUpdate(user);
        }
    }

    public static List<User> queryList() {
        List<User> res = getGoodDao().queryForAll();
        if (res == null) {
            return new ArrayList<>(0);
        } else {
            return res;
        }
    }
}
