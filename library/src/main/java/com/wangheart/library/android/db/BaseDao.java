package com.wangheart.library.android.db;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Author : eric
 * CreateDate : 2016/10/14  16:09
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public class BaseDao {
    protected static<T, ID> void createOrUpdateList(final RuntimeExceptionDao<T, ID> dao, final List<T> items) {
        if (items == null || items.isEmpty()) return;
        //使用事务批量增加到数据库
        dao.callBatchTasks(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                for (T obj : items) {
                    dao.createOrUpdate(obj);
                }
                return null;
            }
        });
    }
}
