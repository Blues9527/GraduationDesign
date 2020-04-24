package com.blues.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import androidx.recyclerview.widget.RecyclerView

import com.blues.CourseActivity
import com.blues.base.BaseFragment
import com.blues.database.course.Course
import com.blues.database.course.CourseManager
import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.FragmentHomepageBinding
import kotlinx.android.synthetic.main.fragment_homepage.*

import java.util.ArrayList
import java.util.HashMap

/**
 * Created by Administrator on 2018/1/27.
 */

class HomeFragment : BaseFragment<FragmentHomepageBinding>() {
    override fun setLayoutResourceId(): Int {
        return R.layout.fragment_homepage
    }

    override fun initLayout(savedInstanceState: Bundle?) {
        rvCourse.adapter
    }

    override fun lazyFetchData() {
    }

    override fun setListener() {
        //点击添加课程
        ibAdd.setOnClickListener {
            startActivity(Intent(activity, CourseActivity::class.java))
        }

        //点击刷新课程
        ibRefresh.setOnClickListener {
            val courses = CourseManager.getInstance().allCourse
            val lessonMaps = ArrayList<Map<String, Any>>()
            for (i in courses.indices) {
                val lessonMap = HashMap<String, Any>()
                lessonMap["name"] = courses[i].name
                lessonMap["teacher"] = courses[i].teacher
                lessonMap["class_name"] = courses[i].class_name
                lessonMap["classroom"] = courses[i].classroom
                lessonMap["st_num"] = courses[i].st_num
                lessonMaps.add(lessonMap)
            }

            //添加一个simpleadapter
            val adapter = SimpleAdapter(activity, lessonMaps, R.layout.item_lv_homepage, arrayOf("name", "class_name", "classroom", "teacher", "st_num"), intArrayOf(R.id.course_1, R.id.course_2, R.id.course_3, R.id.course_4, R.id.course_5))

//            course_lv!!.adapter = adapter
        }
    }

//    class CourseAdapter(course: List<Course>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//
//        }
//
//        override fun getItemCount(): Int {
//
//        }
//
//        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//
//        }
//
//    }

}


