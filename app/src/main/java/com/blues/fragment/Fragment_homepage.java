package com.blues.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.blues.CourseActivity;
import com.example.blues.myapplication.R;

/**
 * Created by Administrator on 2018/1/27.
 */

public class Fragment_homepage extends Fragment {
    private ImageButton add_course;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        add_course = (ImageButton) view.findViewById(R.id.add_course);
        add_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent course_intent = new Intent(getActivity(),CourseActivity.class);//不能直接Fragment_homepage.this，需要getActivity（）方法获取当前activity。
                startActivity(course_intent);
            }
        });
        return view;
    }


}
