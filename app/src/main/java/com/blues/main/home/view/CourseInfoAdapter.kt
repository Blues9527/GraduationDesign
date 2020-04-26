package com.blues.main.home.view

import com.blues.course.model.Course
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.blues.myapplication.R
import kotlinx.android.synthetic.main.item_lv_homepage.view.*

class CourseInfoAdapter : BaseQuickAdapter<Course, BaseViewHolder>(R.layout.item_lv_homepage) {
    override fun convert(helper: BaseViewHolder, item: Course) {
        helper.itemView.run {
            tvCourseName.text = item.courseName
            tvClassName.text = item.className
            tvClassroomName.text = item.classroomName
            tvTeacherName.text = item.teacherName
            tvStuNum.text = item.studentNum.toString()
        }
    }
}