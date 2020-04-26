package com.blues.course.view

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore

import com.example.blues.myapplication.R

import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import com.blues.base.BaseVmActivity
import com.blues.course.model.Course
import com.blues.course.viewmodel.CourseViewModel
import com.blues.main.MainActivity
import com.example.blues.myapplication.databinding.CourseMainBinding
import kotlinx.android.synthetic.main.item_course_header.view.*

class CourseActivity : BaseVmActivity<CourseMainBinding, CourseViewModel>(), TextWatcher {

    private lateinit var className: String
    private lateinit var courseName: String
    private lateinit var classroomName: String
    private lateinit var teacherName: String
    private lateinit var studentNum: String

    companion object {
        private const val REC_REQUEST_CODE = 1
    }

    override fun setLayoutResId(): Int {
        return R.layout.course_main
    }

    override fun setViewModelClass(): Class<CourseViewModel> {
        return CourseViewModel::class.java
    }

    override fun setListener() {
        mDataBinding.etClass.addTextChangedListener(this)
        mDataBinding.etCourse.addTextChangedListener(this)
        mDataBinding.etClassroom.addTextChangedListener(this)
        mDataBinding.etTeacher.addTextChangedListener(this)
        mDataBinding.etStudentNum.addTextChangedListener(this)

        mDataBinding.header.course_back.setOnClickListener {
            ActivityUtil.start(MainActivity::class.java)
        }

        /**
         * 设置封面
         */
        mDataBinding.addPic.setOnClickListener {
            //设置一个Dialog
            val dialog = AlertDialog.Builder(this)
                    .setTitle("从图库里选择照片")
                    .setMessage("确定要更换照片吗？")
                    .setPositiveButton("确定") { _, _ ->
                        startActivityForResult(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), REC_REQUEST_CODE)
                    }
                    .setNegativeButton("取消") { dialog, _ -> dialog.cancel() }
                    .create()
            dialog.show()

            /*Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image*//*");//设置为所有格式的图片
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent,"Select A Picture Upload"),REC_REQUESTCODE);
                 Toast.makeText(CourseActivity.this, "功能仍在开发中...", Toast.LENGTH_SHORT).show();*/
        }

        /**
         * 添加课程
         */
        mDataBinding.courseCreate.setOnClickListener {

            when {
                courseName.isEmpty() -> showToast("课程名称不能为空")
                teacherName.isEmpty() -> showToast("教师名称不能为空")
                classroomName.isEmpty() -> showToast("课室名称不能为空")
                className.isEmpty() -> showToast("班级名称不能为空")
                studentNum.isEmpty() -> showToast("学生人数")
                else -> {
                    mViewModel.insertCourse(Course(courseName, teacherName, classroomName, className, studentNum.toInt()))
                    //课程不为空，插入课程
                    startActivity(Intent().apply {
                        setClass(mContext, MainActivity::class.java)
                        flags = FLAG_ACTIVITY_CLEAR_TOP
                        addFlags(FLAG_ACTIVITY_SINGLE_TOP)
                    })
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode == REC_REQUEST_CODE && resultCode == Activity.RESULT_OK && intent != null) {
            mDataBinding.addPic.setImageURI(intent.data)
        }
        super.onActivityResult(requestCode, resultCode, intent)
    }


    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        className = mDataBinding.etClass.text.toString().trim()
        courseName = mDataBinding.etCourse.text.toString().trim()
        classroomName = mDataBinding.etClassroom.text.toString().trim()
        teacherName = mDataBinding.etTeacher.text.toString().trim()
        studentNum = mDataBinding.etStudentNum.text.toString().trim()
    }
}

