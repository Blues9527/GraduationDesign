package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoMaker {

    public static void main(String[] args) {

        //生成数据库的实体类xx.entity 对应的数据库的表
        Schema schema = new Schema(1, "com.student.entity");
        Schema schema1 = new Schema(2, "com.lesson.entity");
        addStudent(schema);
        addLesson(schema1);
        schema.setDefaultJavaPackageDao("com.student.dao");
        try {
            new DaoGenerator().generateAll(schema, "E:\\AndroidStudio\\MyApplication\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
        schema1.setDefaultJavaPackageDao("com.lesson.dao");
        try {
            new DaoGenerator().generateAll(schema1, "E:\\AndroidStudio\\MyApplication\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //创建数据库的表
    private static void addStudent(Schema schema) {

        Entity entity = schema.addEntity("Student");
        //表对象的列
        entity.addIdProperty();//主键是int类型
        entity.addStringProperty("username");
        entity.addStringProperty("password");
        entity.addStringProperty("email");
        entity.addStringProperty("phone");
    }

    private static void addLesson(Schema schema1) {

        Entity entity1 = schema1.addEntity("Lesson");
        entity1.addIdProperty();
        entity1.addStringProperty("lesson_name");
        entity1.addStringProperty("lesson_teacher");
        entity1.addStringProperty("lesson_classroom");
        entity1.addIntProperty("student_number");
        entity1.addStringProperty("class_name");
    }
}
