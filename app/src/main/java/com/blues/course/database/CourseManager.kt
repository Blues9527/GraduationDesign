package com.blues.course.database

import android.content.Context
import androidx.room.Room
import com.blues.course.model.Course

class CourseManager private constructor() {
    private lateinit var courseDataBase: CourseDataBase

    companion object {
        val INSTANCE = CourseManager()
    }

    /**
     * 初始化创建数据库
     *
     * @param ctx
     */
    fun initDataBase(ctx: Context) {
        courseDataBase = Room.databaseBuilder(ctx, CourseDataBase::class.java, "blues.db")
                .allowMainThreadQueries()
                .build()
    }

    /**
     * 获取dao
     *
     * @return
     */
    private val dao: CourseDAO?
        get() = courseDataBase.courseDAO()

    //---------------------数据库基本操作
    suspend fun insertCourse(course: Course?) = dao!!.insert(course)


    fun deleteCourse(course: Course?) = dao!!.delete(course)


    fun updateCourse(course: Course?) = dao!!.update(course)

    val allCourse: List<Course>?
        get() = dao!!.fetchAll

    fun getAllCourseByName(name: String?): List<Course?>? = dao!!.getCourseByName(name)

    //数据库扩展操作
    fun courseExits(name: String?): Boolean = getAllCourseByName(name)!!.isNotEmpty()
}