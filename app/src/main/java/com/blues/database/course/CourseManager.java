package com.blues.database.course;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;


public class CourseManager {

    private CourseDataBase courseDataBase;

    private CourseManager() {
    }

    private static class Holder {
        public static final CourseManager INSTANCE = new CourseManager();
    }

    public static CourseManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 初始化创建数据库
     *
     * @param ctx
     */
    public void initDataBase(Context ctx) {
        courseDataBase = Room.databaseBuilder(ctx, CourseDataBase.class, "blues.db")
                .allowMainThreadQueries()
                .build();
    }

    /**
     * 获取dao
     *
     * @return
     */
    public CourseDAO getDao() {
        return courseDataBase.courseDAO();
    }

    //---------------------数据库基本操作
    public void insertCourse(Course course) {
        getDao().insert(course);
    }

    public void deleteCourse(Course course) {
        getDao().delete(course);
    }

    public void updateCourse(Course course) {
        getDao().update(course);
    }

    public List<Course> getAllCourse() {
        return getDao().getAll();
    }

    public List<Course> getAllCourseByName(String name) {
        return getDao().getCourseByName(name);
    }

    //数据库扩展操作
    public boolean courseExits(String name) {
        return getAllCourseByName(name).size() > 0;
    }
}
