package com.blues.main.home.view

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration

import com.blues.course.view.CourseActivity
import com.blues.search.SearchActivity
import com.blues.base.BaseVmFragment
import com.blues.main.home.viewmodel.HomeViewModel
import com.blues.util.Bus
import com.blues.util.COURSE_UPDATE
import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.FragmentHomepageBinding
import kotlinx.android.synthetic.main.fragment_homepage.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException

/**
 * Created by Administrator on 2018/1/27.
 */

class HomeFragment : BaseVmFragment<FragmentHomepageBinding, HomeViewModel>(), View.OnClickListener {

    private lateinit var picPath: String
    private var mAdapter = CourseInfoAdapter()

    companion object {
        private const val REQUEST_CODE = 5
    }

    override fun setLayoutResId(): Int {
        return R.layout.fragment_homepage
    }

    override fun setViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }


    override fun initView() {
        mViewModel.queryCourse()
        rvCourse.apply {
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        mAdapter.apply {
            bindToRecyclerView(rvCourse)
        }

        tb_title.text = "Blues"

        picPath = StringBuilder()
                .append(Environment.getExternalStorageDirectory().path)
                .append("/")
                .append(System.currentTimeMillis())
                .append(".png")
                .toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                var fis: FileInputStream? = null
                try {
                    fis = FileInputStream(picPath)
                    BitmapFactory.decodeStream(fis)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } finally {
                    try {
                        fis!!.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    override fun observe() {
        mViewModel.courses.observe(this, Observer {
            mAdapter.setNewData(it)
        })
        Bus.observe<Boolean>(COURSE_UPDATE, this) {
            mViewModel.queryCourse()
        }
    }

    override fun setListener() {
        ibAdd.setOnClickListener(this)
        imbtn_camera.setOnClickListener(this)
        imbtn_search.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            ibAdd -> ActivityUtil.start(CourseActivity::class.java)//点击添加课程
            imbtn_camera -> {
                //设置相机监听跳转
                startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        .apply {
                            putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(File(picPath)))
                        }, REQUEST_CODE)
            }
            imbtn_search -> ActivityUtil.start(SearchActivity::class.java) //search按钮跳转到搜索界面
        }
    }
}


