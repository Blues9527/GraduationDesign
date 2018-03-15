package com.blues;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blues.dbmanager.CommonUtil;
import com.example.blues.myapplication.R;
import com.lesson.entity.Lesson;

import java.util.Timer;
import java.util.TimerTask;

public class CourseActivity extends AppCompatActivity {
    private ImageButton course_back, add_pic;
    private EditText class_et, course_et, classroom_et, teacher_et, number_et;
    private Button course_create;
    private CommonUtil commonUtil;
    private LinearLayout layout_course, layout_book, layout_xuexiyaoqiu, layout_jiaoxuejindu, layout_kaoshianpai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_main);
        commonUtil = new CommonUtil(this);
        initView();
    }

    private void initView() {
        class_et = (EditText) findViewById(R.id.class_name);
        course_et = (EditText) findViewById(R.id.lesson_name);
        classroom_et = (EditText) findViewById(R.id.lesson_classroom);
        teacher_et = (EditText) findViewById(R.id.lesson_teacher);
        number_et = (EditText) findViewById(R.id.student_number);


        course_back = (ImageButton) findViewById(R.id.course_back);
        course_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent course_intent = new Intent();
                course_intent.setClass(CourseActivity.this, MainActivity.class);
                course_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                course_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
                startActivity(course_intent);
            }
        });

        add_pic = (ImageButton) findViewById(R.id.add_pic);
        add_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CourseActivity.this, "功能仍在开发中...", Toast.LENGTH_SHORT).show();
            }
        });

        course_create = (Button) findViewById(R.id.course_create);
        course_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String class_name = class_et.getText().toString().trim();
                String course_name = course_et.getText().toString().trim();
                String classroom_name = classroom_et.getText().toString().trim();
                String teacher_name = teacher_et.getText().toString().trim();
                String srudent_number = number_et.getText().toString().trim();

                if (class_name.isEmpty() || course_name.isEmpty() || classroom_name.isEmpty() || teacher_name.isEmpty() || srudent_number.isEmpty()) {
                    Toast.makeText(CourseActivity.this, "创建失败!", Toast.LENGTH_SHORT).show();
                } else {
                    Lesson lesson = new Lesson();
                    lesson.setLesson_name(course_name);
                    lesson.setLesson_teacher(teacher_name);
                    lesson.setLesson_classroom(classroom_name);
                    lesson.setClass_name(class_name);
                    lesson.setStudent_number(srudent_number);
                    commonUtil.insertLesson(lesson);
                    Toast.makeText(CourseActivity.this, "创建成功！！3秒后跳转至主界面！", Toast.LENGTH_SHORT).show();
                    setTimeoutIntent();
                }
                //Toast.makeText(CourseActivity.this, "创建失败，功能仍在开发中...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //设置延时跳转
    private void setTimeoutIntent() {
        Timer time = new Timer();
        TimerTask tk = new TimerTask() {
            Intent intent = new Intent(CourseActivity.this, MainActivity.class);
            @Override
            public void run() {
                startActivity(intent);
            }
        };
        time.schedule(tk, 3000);
    }
}

