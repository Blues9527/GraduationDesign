package com.blues.course.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class Course(
        @PrimaryKey
        @ColumnInfo(name = "name")
        val courseName: String,
        @ColumnInfo(name = "teacher")
        val teacherName: String,
        @ColumnInfo(name = "classroom")
        val classroomName: String,
        @ColumnInfo(name = "st_num")
        val className: String,
        @ColumnInfo(name = "class_name")
        val studentNum: Int)