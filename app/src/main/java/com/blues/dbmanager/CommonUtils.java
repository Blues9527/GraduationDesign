package com.blues.dbmanager;

import android.content.Context;
import android.util.Log;

import com.student.dao.StudentDao;
import com.student.entity.Student;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * 完成对某一张表的具体操作，ORM操作是对象，Student
 * 二次封装数据库操作方法
 * Created by Administrator on 2018/2/6.
 */

public class CommonUtils {
    private StudentDaoManager manager;

    public CommonUtils(Context context) {
        manager = StudentDaoManager.getInstance();
        manager.init(context);//问题语句 原因--->>空指针异常
    }

    /**
     * 完成对数据库中student表的插入操作
     * 单条数据的插入
     *
     * @param student
     * @return
     */
    public boolean insertStudent(Student student) {
        boolean flag = false;
        flag = manager.getStudentDaoSession().insert(student) != -1 ? true : false;
        Log.i("CommUtils", "result-->" + flag);
        return flag;
    }



    /**
     * 多条数据的插入
     * 开辟新线程，用于同时对多条数据的插入
     */
    public boolean insertMultStudent(final List<Student> students) {
        boolean flag = false;

        try {
            manager.getStudentDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Student student : students) {
                        manager.getStudentDaoSession().insertOrReplace(student);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 完成对数据库表中的某一条记录的修改
     */

    public boolean updateStudent(Student student) {
        boolean flag = false;

        try {
            manager.getStudentDaoSession().update(student);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库表中的某一条记录的删除
     */

    public boolean deleteStudent(Student student) {
        boolean flag = false;

        try {
            manager.getStudentDaoSession().delete(student);//删除一条数据
            //        manager.getDaoSession().deleteAll();//删除所有数据
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库表中多条记录的查询
     */
    public List<Student> listAll() {
        return manager.getStudentDaoSession().loadAll(Student.class);
    }

    /**
     * 按照主键返回单条记录
     *
     * @param key
     * @return
     */
    public Student listSingleStudent(long key) {
        return manager.getStudentDaoSession().load(Student.class, key);
    }

    /**
     * boolean 查询结果
     */
    public boolean userisExist(String username, String password) {
        QueryBuilder<Student> students = manager.getStudentDaoSession().queryBuilder(Student.class);
        students.where(StudentDao.Properties.Username.eq(username));
        students.where(StudentDao.Properties.Password.eq(password));
        students.buildCount().count();
        return students.buildCount().count() > 0;
    }

    /**
     * 复杂的查询操作QueryBuilder();
     * 符合条件查询，即同时成立多个条件
     */
   /* public void query1() {
        List<Student> lits = manager.getDaoSession().queryRaw(Student.class, "where name like ? and _id > ?", new String[]{"",""});
        Log.i("",""+lits);
    }*/
}
