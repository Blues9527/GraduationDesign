package com.blues.application

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.blues.course.database.CourseManager

import com.blues.util.ThreadManager

class App : Application() {

    companion object {
        lateinit var instance: App
    }


    override fun onCreate() {
        super.onCreate()

        instance = this

        //创建数据库
        createDataBase()

        //注册activity声明周期
        registerActivityLifecycle()
    }

    private fun createDataBase() {

        ThreadManager.executeRunnable(Runnable {
            //创建数据库
            CourseManager.INSTANCE.initDataBase(applicationContext)
        })
    }

    private fun registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(p0: Activity) {
            }

            override fun onActivityStarted(p0: Activity) {
            }

            override fun onActivityDestroyed(p0: Activity) {
                ActivityUtil.activities.remove(p0)
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            }

            override fun onActivityStopped(p0: Activity) {

            }

            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                ActivityUtil.activities.add(p0)
            }

            override fun onActivityResumed(p0: Activity) {
            }

        })
    }
}
