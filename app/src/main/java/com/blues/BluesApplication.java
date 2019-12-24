package com.blues;

import android.app.Application;
import android.content.Context;

import com.blues.database.course.CourseManager;
import com.blues.database.user.UserManager;
import com.blues.util.ThreadManager;

public class BluesApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //创建数据库
        createDataBase();
    }

    private void createDataBase() {

        ThreadManager.INSTANCE.executeRunnable(new Runnable() {
            @Override
            public void run() {
                //创建数据库
                UserManager.getInstance().initDataBase(getApplicationContext());

                CourseManager.getInstance().initDataBase(getApplicationContext());
            }
        });
    }
}
