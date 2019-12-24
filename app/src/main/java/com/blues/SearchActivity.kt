package com.blues


import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.widget.SimpleAdapter
import android.widget.Toast
import com.blues.base.BaseActivity
import com.blues.database.course.CourseManager

import com.example.blues.myapplication.R
import kotlinx.android.synthetic.main.search_main.*

import java.util.ArrayList
import java.util.HashMap


/**
 * Created by Administrator on 2018/2/10.
 */

class SearchActivity : BaseActivity(), TextWatcher {
    private lateinit var searchText: String

    override fun setLayoutId(): Int {
        return R.layout.search_main
    }

    override fun initLayout() {
    }

    override fun setListener() {
        etSearch.addTextChangedListener(this@SearchActivity)

        //实例化后退按钮，并绑定监听器
        btnBack.setOnClickListener {
            startActivity(Intent().apply {
                setClass(this@SearchActivity, MainActivity::class.java)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//关掉所要到的界面中间的activity
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)//设置不要刷新将要跳转的界面
            })
        }

        //实例化搜索按钮，并绑定监听器
        btnSearch.setOnClickListener {
            //点击搜索按钮后，对数据库执行查询功能
            if (TextUtils.isEmpty(searchText)) {
                Toast.makeText(this@SearchActivity, "输入内容不能为空！", Toast.LENGTH_SHORT).show()
            } else {
                val courses = CourseManager.getInstance().getAllCourseByName(searchText)
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
                //添加一个simple adapter
                lvSearch.adapter = SimpleAdapter(this@SearchActivity,
                        lessonMaps,
                        R.layout.item_search_content,
                        arrayOf("name", "teacher", "class_name", "classroom", "st_num"),
                        intArrayOf(R.id.item_course_name, R.id.item_teacher_name, R.id.item_class_name, R.id.item_classroom_name, R.id.item_student_number))

                //在控制台打印出搜索结果。
                for (i in courses.indices) {
                    Log.i("Blues", "班级名称：" + courses[i].class_name)
                    Log.i("Blues", "课室名称：" + courses[i].classroom)
                    Log.i("Blues", "课程名称：" + courses[i].name)
                    Log.i("Blues", "班级人数：" + courses[i].st_num)
                }
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        searchText = etSearch.text.toString().trim { it <= ' ' }
    }
}
