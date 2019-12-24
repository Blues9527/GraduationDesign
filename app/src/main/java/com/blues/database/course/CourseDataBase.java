package com.blues.database.course;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = Course.class, version = 1)
public abstract class CourseDataBase extends RoomDatabase {

    public abstract CourseDAO courseDAO();
}
