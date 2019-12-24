package com.blues.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.blues.CourseActivity;
import com.blues.database.course.Course;
import com.blues.database.course.CourseManager;
import com.example.blues.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/27.
 */

public class Fragment_homepage extends Fragment {
    private ListView course_lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        ImageButton add_course = (ImageButton) view.findViewById(R.id.add_course);
        course_lv = (ListView) view.findViewById(R.id.course_lv);

        //点击添加课程
        add_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent course_intent = new Intent(getActivity(), CourseActivity.class);//不能直接Fragment_homepage.this，需要getActivity（）方法获取当前activity。
                startActivity(course_intent);
            }
        });

        //点击刷新课程
        ImageButton refresh_course = (ImageButton) view.findViewById(R.id.reflesh_course);
        refresh_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Course> courses = CourseManager.getInstance().getAllCourse();
                List<Map<String, Object>> lessonMaps = new ArrayList<>();
                for (int i = 0; i < courses.size(); i++) {
                    Map<String, Object> lessonMap = new HashMap<>();
                    lessonMap.put("name", courses.get(i).getName());
                    lessonMap.put("teacher", courses.get(i).getTeacher());
                    lessonMap.put("class_name", courses.get(i).getClass_name());
                    lessonMap.put("classroom", courses.get(i).getClassroom());
                    lessonMap.put("st_num", courses.get(i).getSt_num());
                    lessonMaps.add(lessonMap);
                }

                //添加一个simpleadapter
                SimpleAdapter adapter = new SimpleAdapter(getActivity(), lessonMaps, R.layout.item_lv_homepage, new String[]{"name", "class_name",
                        "classroom", "teacher", "st_num"}, new int[]{R.id.course_1, R.id.course_2, R.id.course_3, R.id.course_4, R.id.course_5});

                course_lv.setAdapter(adapter);
            }
        });
        return view;

    }

}


