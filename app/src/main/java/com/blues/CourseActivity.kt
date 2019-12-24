package com.blues

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import com.example.blues.myapplication.R

import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.blues.base.BaseActivity
import com.blues.database.course.Course
import com.blues.database.course.CourseManager
import kotlinx.android.synthetic.main.course_main.*
import kotlinx.android.synthetic.main.item_course_header.*

class CourseActivity : BaseActivity(), TextWatcher {

    private var className: String? = null
    private var courseName: String? = null
    private var classroomName: String? = null
    private var teacherName: String? = null
    private var studentNum: String? = null

    override fun setLayoutId(): Int {
        return R.layout.course_main
    }

    override fun initLayout() {
    }

    override fun setListener() {
        etClass.addTextChangedListener(this@CourseActivity)
        etCourse.addTextChangedListener(this@CourseActivity)
        etClassroom.addTextChangedListener(this@CourseActivity)
        etTeacher.addTextChangedListener(this@CourseActivity)
        etStudentNum.addTextChangedListener(this@CourseActivity)


        course_back.setOnClickListener {
            startActivity(Intent().apply {
                setClass(this@CourseActivity, MainActivity::class.java)
                flags = FLAG_ACTIVITY_CLEAR_TOP//关掉所要到的界面中间的activity
                addFlags(FLAG_ACTIVITY_SINGLE_TOP)//设置不要刷新将要跳转的界面
            })
        }

        /**
         * 设置封面
         */
        add_pic.setOnClickListener {
            //设置一个Dialog
            val dialog = AlertDialog.Builder(this@CourseActivity)
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
        course_create!!.setOnClickListener {

            if (TextUtils.isEmpty(courseName) ||
                    TextUtils.isEmpty(teacherName) ||
                    TextUtils.isEmpty(classroomName) ||
                    TextUtils.isEmpty(className) ||
                    TextUtils.isEmpty(studentNum)) {
                Toast.makeText(this@CourseActivity, "创建失败!", Toast.LENGTH_SHORT).show()
            } else {
                //课程不为空，插入课程
                if (!CourseManager.getInstance().courseExits(courseName)) {
                    CourseManager.getInstance().insertCourse(Course().also {
                        it.name = courseName
                        it.teacher = teacherName
                        it.classroom = classroomName
                        it.class_name = className
                        it.st_num = studentNum
                    })
                }

                Toast.makeText(this@CourseActivity, "创建成功！！即将跳转至主界面！", Toast.LENGTH_SHORT).show()

                startActivity(Intent().apply {
                    //带参跳转
                    //Bundle bundle = new Bundle();
                    //intent.setClass(CourseActivity.this, MainActivity.class);
                    //bundle.putString("class_name", class_name);
                    //bundle.putString("course_name", course_name);
                    //bundle.putString("classroom_name", classroom_name);
                    //bundle.putString("teacher_name", teacher_name);
                    //bundle.putString("student_number", student_number);
                    //intent.putExtras(bundle);

                    setClass(this@CourseActivity, MainActivity::class.java)
                    flags = FLAG_ACTIVITY_CLEAR_TOP
                    addFlags(FLAG_ACTIVITY_SINGLE_TOP)
                })
            }
        }
    }


    companion object {
        private const val REC_REQUEST_CODE = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode == REC_REQUEST_CODE && resultCode == Activity.RESULT_OK && intent != null) {
            add_pic.setImageURI(intent.data)
        }
        super.onActivityResult(requestCode, resultCode, intent)
    }


    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        className = etClass!!.text.toString().trim { it <= ' ' }
        courseName = etCourse!!.text.toString().trim { it <= ' ' }
        classroomName = etClassroom!!.text.toString().trim { it <= ' ' }
        teacherName = etTeacher!!.text.toString().trim { it <= ' ' }
        studentNum = etStudentNum!!.text.toString().trim { it <= ' ' }
    }
}

