package com.blues.course.database

import androidx.room.*
import com.blues.course.model.Course

@Dao
interface CourseDAO {
    @Insert
    fun insert(course: Course?)

    @Update
    fun update(course: Course?)

    @Delete
    fun delete(course: Course?)

    @get:Query("SELECT * FROM course")
    val fetchAll: List<Course>?

    @Query("SELECT * FROM course WHERE name LIKE :courseName")
    fun getCourseByName(courseName: String?): List<Course?>?
}