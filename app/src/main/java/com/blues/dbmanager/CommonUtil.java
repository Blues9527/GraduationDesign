package com.blues.dbmanager;

import android.content.Context;
import android.util.Log;

import com.lesson.dao.LessonDao;
import com.lesson.entity.Lesson;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */

public class CommonUtil {
    private LessonDaoManager mManager;

    public CommonUtil(Context context) {
        mManager = LessonDaoManager.getInstance();
        mManager.init(context);//问题语句 原因--->>空指针异常
    }

    //插入单条数据
    public boolean insertLesson(Lesson lesson) {
        boolean flag = false;
        flag = mManager.getLessonDaoSession().insert(lesson) != -1;
        Log.i("CommUtils", "result-->" + flag);
        return flag;
    }

    //插入多条数据
    public boolean insertLesson(final List<Lesson> lessons) {
        boolean flag = false;
        try {
            mManager.getLessonDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Lesson lesson : lessons) {
                        mManager.getLessonDaoSession().insertOrReplace(lesson);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //查询单条数据
    public List<Lesson> queryCourse(String string) {
        List<Lesson> lessons = mManager.getLessonDaoSession().queryBuilder(Lesson.class).whereOr(LessonDao.Properties.Class_name.like("%" + string + "%"),
                LessonDao.Properties.Lesson_classroom.like("%" + string + "%"),
                LessonDao.Properties.Lesson_name.like("%" + string + "%"),
                LessonDao.Properties.Lesson_teacher.like("%" + string + "%"),
                LessonDao.Properties.Student_number.like("%" + string + "%"))
                .list();
        return lessons;
    }
}
