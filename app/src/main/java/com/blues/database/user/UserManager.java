package com.blues.database.user;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.blues.util.ThreadManager;

import java.util.List;

public class UserManager {

    private UserDataBase userDataBase;

    private UserManager() {
    }

    private static class Holder {
        public static final UserManager INSTANCE = new UserManager();
    }

    public static UserManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 初始化创建数据库
     *
     * @param ctx
     */
    public void initDataBase(final Context ctx) {
        userDataBase = Room.databaseBuilder(ctx, UserDataBase.class, "blues.db")
                .allowMainThreadQueries()
                .build();
    }

    /**
     * 获取dao
     *
     * @return
     */
    public UserDAO getDao() {
        return userDataBase.userDAO();
    }

    //---------------数据库基本操作
    public void insertUser(final User user) {
        getDao().insertUser(user);
    }

    public void deleteUser(final User user) {
        getDao().deleteUser(user);
    }

    public void updateUser(final User user) {
        getDao().updateUser(user);
    }

    public List<User> getAllUser() {
        return getDao().getAllUser();
    }

    public List<User> getAllUserByName(final String name) {
        return getDao().getUserByName(name);
    }


    //---------------数据库扩展操作
    public boolean userExits(final String name) {
        return getDao().getUserByName(name).size() > 0;
    }
}
