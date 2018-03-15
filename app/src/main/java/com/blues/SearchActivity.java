package com.blues;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.blues.dbmanager.CommonUtil;
import com.example.blues.myapplication.R;
import com.lesson.entity.Lesson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018/2/10.
 */

public class SearchActivity extends AppCompatActivity {

    private ImageButton back_button;
    private ImageButton search_button;
    private EditText editText;
    private CommonUtil commonUtil;
    private ListView listView;
    //private List<Map<String, Object>> listItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);
        commonUtil = new CommonUtil(this);
        initView();
        listView = (ListView) findViewById(R.id.search_lv);
    }

    //加载视图
    private void initView() {
        //实例化后退按钮，并绑定监听器
        back_button = (ImageButton) findViewById(R.id.search_backbt);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchback_intent = new Intent();
                searchback_intent.setClass(SearchActivity.this, MainActivity.class);
                searchback_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
                searchback_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//设置不要刷新将要跳转的界面
                startActivity(searchback_intent);
            }
        });
        //实例化EditText
        editText = (EditText) findViewById(R.id.search_editText);

        //实例化搜索按钮，并绑定监听器
        search_button = (ImageButton) findViewById(R.id.search_searchbt);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击搜索按钮后，对数据库执行查询功能
                String search_text = editText.getText().toString().trim();
                if (search_text.isEmpty()) {
                    Toast.makeText(SearchActivity.this, "输入内容不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    List<Lesson> lessons = commonUtil.queryCourse(search_text);
                    List<Map<String, Object>> lessonMaps = new ArrayList<>();
                    for (int i = 0; i < lessons.size(); i++) {
                        Map<String, Object> lessonMap = new HashMap<>();
                        lessonMap.put("Lesson_name", lessons.get(i).getLesson_name());
                        lessonMap.put("teacher_name", lessons.get(i).getLesson_teacher());
                        lessonMap.put("class_name", lessons.get(i).getClass_name());
                        lessonMap.put("classroom_name", lessons.get(i).getLesson_classroom());
                        lessonMap.put("student_number", lessons.get(i).getStudent_number());
                        lessonMaps.add(lessonMap);
                    }
                    //添加一个simpleadapter
                    SimpleAdapter adapter = new SimpleAdapter(SearchActivity.this, lessonMaps, R.layout.item_search_content, new String[]{"Lesson_name", "teacher_name", "class_name",
                            "classroom_name", "student_number"}, new int[]{R.id.item_course_name, R.id.item_teacher_name, R.id.item_class_name, R.id.item_classroom_name, R.id.item_student_number});

                    listView.setAdapter(adapter);


                    //在控制台打印出搜索结果。
                    for (int i = 0; i < lessons.size(); i++) {
                        System.out.println("班级名称：" + lessons.get(i).getClass_name());
                        System.out.println("课室名称：" + lessons.get(i).getLesson_classroom());
                        System.out.println("课程名称：" + lessons.get(i).getLesson_name());
                        System.out.println("班级人数：" + lessons.get(i).getStudent_number());
                    }
                }

            }
        });
    }
}
