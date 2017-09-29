package com.wangheart.ericdroid.dao;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.wangheart.ericdroid.entity.User;
import com.wangheart.library.android.db.BaseDbOpenHelper;
import com.wangheart.library.android.utils.AppUtils;

import java.sql.SQLException;

/**
 * Author : eric
 * CreateDate : 2017/9/29  10:51
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public class DbHelper extends BaseDbOpenHelper {
    public static final String DB_NAME = "app";
    public static final int DB_VERSION = 1;

    private static DbHelper mInstance;

    private DbHelper() {
        super(AppUtils.getContext(), DB_NAME, null, DB_VERSION);
    }

    public static DbHelper getInstance() {
        if (mInstance == null) {
            synchronized (DbHelper.class) {
                if (mInstance == null) {
                    mInstance = new DbHelper();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        super.onCreate(database, connectionSource);
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RuntimeExceptionDao<User, Integer> getUserDao() {
        return getRuntimeExceptionDao(User.class);
    }
}
