package com.blues.course.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blues.course.model.Course

@Database(entities = [Course::class], version = 1)
abstract class CourseDataBase : RoomDatabase() {
    abstract fun courseDAO(): CourseDAO?
}