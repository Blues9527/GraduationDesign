package com.blues.database.course;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CourseDAO {

    @Insert
    void insert(Course course);

    @Update
    void update(Course course);


    @Delete
    void delete(Course course);

    @Query("SELECT * FROM course")
    List<Course> getAll();

    @Query("SELECT * FROM course WHERE name LIKE :courseName")
    List<Course> getCourseByName(String courseName);
}
