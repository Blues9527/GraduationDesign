package com.blues.database.user;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM user")
    List<User> getAllUser();

    @Query("SELECT * FROM user WHERE username LIKE :userName")
    List<User> getUserByName(String userName);
}
