package com.blues.search


import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.SimpleAdapter
import android.widget.Toast
import com.blues.base.BaseActivity
import com.blues.course.database.CourseManager
import com.blues.main.MainActivity

import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.SearchMainBinding
import kotlinx.android.synthetic.main.search_main.*

import java.util.ArrayList
import java.util.HashMap


/**
 * Created by Administrator on 2018/2/10.
 */

class SearchActivity : BaseActivity<SearchMainBinding>(), TextWatcher {
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
                val courses = CourseManager.INSTANCE.getAllCourseByName(searchText)
                val lessonMaps = ArrayList<Map<String, Any>>()
                for (i in courses!!.indices) {
                    val lessonMap = HashMap<String, Any>()
                    lessonMap["name"] = courses[i]!!.courseName
                    lessonMap["teacher"] = courses[i]!!.teacherName
                    lessonMap["class_name"] = courses[i]!!.className
                    lessonMap["classroom"] = courses[i]!!.classroomName
                    lessonMap["st_num"] = courses[i]!!.studentNum
                    lessonMaps.add(lessonMap)
                }
                //添加一个simple adapter
                lvSearch.adapter = SimpleAdapter(this@SearchActivity,
                        lessonMaps,
                        R.layout.item_search_content,
                        arrayOf("name", "teacher", "class_name", "classroom", "st_num"),
                        intArrayOf(R.id.item_course_name, R.id.item_teacher_name, R.id.item_class_name, R.id.item_classroom_name, R.id.item_student_number))
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
