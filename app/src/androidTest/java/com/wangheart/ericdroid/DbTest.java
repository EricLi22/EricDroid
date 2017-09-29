package com.wangheart.ericdroid;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.wangheart.ericdroid.dao.UserDao;
import com.wangheart.ericdroid.entity.User;
import com.wangheart.library.android.utils.AppUtils;
import com.wangheart.library.android.utils.LogUtils;
import com.wangheart.library.java.utils.CollectionUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DbTest {
    @Before
    public void before() {
        LogUtils.w("===========before==========");
        Context appContext = InstrumentationRegistry.getTargetContext();
        AppUtils.init(appContext);
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        User user = new User();
        user.setId(2);
        user.setName("eric2");
        user.setSex(1);
        user.setBirth("1990-11-15");
        UserDao.add(user);
        List<User> userList = UserDao.queryList();
        CollectionUtils.print(userList);
    }
}
