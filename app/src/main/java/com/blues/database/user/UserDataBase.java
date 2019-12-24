package com.blues.database.user;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = User.class, version = 1)
public abstract class UserDataBase extends RoomDatabase {

    public abstract UserDAO userDAO();
}
