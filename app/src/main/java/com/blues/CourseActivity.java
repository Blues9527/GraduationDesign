package com.blues;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.blues.myapplication.R;

public class CourseActivity extends AppCompatActivity {
    private ImageButton course_back, add_pic;
    private Button course_create;
    private LinearLayout layout_course, layout_book, layout_xuexiyaoqiu, layout_jiaoxuejindu, layout_kaoshianpai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_main);
        initView();
    }

    private void initView() {
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

        add_pic = (ImageButton)findViewById(R.id.add_pic);
        add_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CourseActivity.this,"功能仍在开发中...",Toast.LENGTH_SHORT).show();
            }
        });

        course_create = (Button)findViewById(R.id.course_create);
        course_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CourseActivity.this,"创建失败，功能仍在开发中...",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
