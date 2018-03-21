package com.blues;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blues.dbmanager.CommonUtil;
import com.example.blues.myapplication.R;
import com.lesson.entity.Lesson;

import java.lang.annotation.Target;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

public class CourseActivity extends AppCompatActivity {
    private ImageButton course_back;
    private ImageView add_pic;
    private EditText class_et, course_et, classroom_et, teacher_et, number_et;
    private Button course_create;
    private CommonUtil commonUtil;
    private Target target;
    private static final int REC_REQUESTCODE = 1;
    private LinearLayout layout_course, layout_book, layout_xuexiyaoqiu, layout_jiaoxuejindu, layout_kaoshianpai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_main);
        commonUtil = new CommonUtil(this);
        initView();
    }

    /**
     * 实例化对象
     */
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
                course_intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                course_intent.addFlags(FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
                startActivity(course_intent);
            }
        });

        /**
         * 设置封面
         */
        add_pic = (ImageView) findViewById(R.id.add_pic);
        add_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置一个Dialog
                AlertDialog dialog = new AlertDialog.Builder(CourseActivity.this)
                        .setTitle("从图库里选择照片")
                        .setMessage("确定要更换照片吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent, REC_REQUESTCODE);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create();
                dialog.show();

                /*Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image*//*");//设置为所有格式的图片
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent,"Select A Picture Upload"),REC_REQUESTCODE);
                 Toast.makeText(CourseActivity.this, "功能仍在开发中...", Toast.LENGTH_SHORT).show();*/
            }
        });

        /**
         * 添加课程
         */
        course_create = (Button) findViewById(R.id.course_create);
        course_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String class_name = class_et.getText().toString().trim();
                String course_name = course_et.getText().toString().trim();
                String classroom_name = classroom_et.getText().toString().trim();
                String teacher_name = teacher_et.getText().toString().trim();
                String student_number = number_et.getText().toString().trim();

                if (class_name.isEmpty() || course_name.isEmpty() || classroom_name.isEmpty() || teacher_name.isEmpty() || student_number.isEmpty()) {
                    Toast.makeText(CourseActivity.this, "创建失败!", Toast.LENGTH_SHORT).show();
                } else {
                    Lesson lesson = new Lesson();
                    lesson.setLesson_name(course_name);
                    lesson.setLesson_teacher(teacher_name);
                    lesson.setLesson_classroom(classroom_name);
                    lesson.setClass_name(class_name);
                    lesson.setStudent_number(student_number);
                    commonUtil.insertLesson(lesson);
                    Toast.makeText(CourseActivity.this, "创建成功！！即将跳转至主界面！", Toast.LENGTH_SHORT).show();


                    //带参跳转
                    Intent intent = new Intent();
                    //Bundle bundle = new Bundle();
                    intent.setClass(CourseActivity.this, MainActivity.class);
                    //intent.setClass(CourseActivity.this, MainActivity.class);
                    //bundle.putString("class_name", class_name);
                    //bundle.putString("course_name", course_name);
                    //bundle.putString("classroom_name", classroom_name);
                    //bundle.putString("teacher_name", teacher_name);
                    //bundle.putString("student_number", student_number);
                    //intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }
                //Toast.makeText(CourseActivity.this, "创建失败，功能仍在开发中...", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REC_REQUESTCODE && resultCode == RESULT_OK && data != null) {
            add_pic.setImageURI(data.getData());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //设置延时跳转
   /* private void setTimeoutIntent() {
        Timer time = new Timer();
        TimerTask tk = new TimerTask() {
            Intent intent = new Intent(CourseActivity.this, MainActivity.class);
            @Override
            public void run() {
                startActivity(intent);
            }
        };
        time.schedule(tk, 3000);
    }*/
}

