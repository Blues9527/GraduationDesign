package com.blues.database.course;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Course.class, version = 1)
public abstract class CourseDataBase extends RoomDatabase {

    public abstract CourseDAO courseDAO();
}
