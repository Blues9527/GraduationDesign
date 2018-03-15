package com.blues.dbmanager;

import android.content.Context;

import com.lesson.dao.DaoMaster;
import com.lesson.dao.DaoSession;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2018/3/9.
 */

public class LessonDaoManager {

    //标志，测试用
    private static final String TAG = LessonDaoManager.class.getSimpleName();

    //数据库名称
    private static final String DB_NAME = "mydb.sqlite";

    //多线程访问
    private volatile static LessonDaoManager manager;
    private static DaoMaster.DevOpenHelper helper;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private Context context;


    /**
     * 使用单例模式获得操作数据库的对象
     *
     * @return
     */
    public static LessonDaoManager getInstance() {
        LessonDaoManager instance = null;
        if (manager == null) {
            synchronized (LessonDaoManager.class) {
                if (instance == null) {
                    instance = new LessonDaoManager();
                    manager = instance;
                }
            }
        }
        return manager;
    }

    public void init(Context context) {
        this.context = context;
    }

    /**
     * 判断数据库是否存在，若没有则创建数据库
     *
     * @return
     */
    public DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 完成对数据库的添加、删除、修改、查询的操作，仅仅是一个接口，供调用
     *
     * @return
     */
    public DaoSession getLessonDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * 使用debug调试,打开输出日志的操作，默认是关闭的
     */
    public void setDebug() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    /**
     * 关闭数据库
     * 使用了数据库后一定要关闭，否则造成资源浪费
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {
        if (helper != null) {
            helper.close();
            helper = null;
        }
    }

    public void closeDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }
}
