package com.wangheart.library.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * Author : eric
 * CreateDate : 2017/9/29  10:46
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public abstract class BaseDbOpenHelper extends OrmLiteSqliteOpenHelper {

    public BaseDbOpenHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    public BaseDbOpenHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, int configFileId) {
        super(context, databaseName, factory, databaseVersion, configFileId);
    }

    public BaseDbOpenHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, File configFile) {
        super(context, databaseName, factory, databaseVersion, configFile);
    }

    public BaseDbOpenHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, InputStream stream) {
        super(context, databaseName, factory, databaseVersion, stream);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    private static void reCreateTable(ConnectionSource connectionSource, Class clazz) {
        try {
            TableUtils.dropTable(connectionSource, clazz, true);
            TableUtils.createTable(connectionSource, clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void clearTable(Class clazz) {
        try {
            TableUtils.clearTable(getConnectionSource(), clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
