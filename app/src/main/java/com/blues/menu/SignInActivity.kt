package com.blues.menu

import android.content.Intent

import com.blues.main.MainActivity
import com.blues.base.BaseActivity
import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.MenuCheckinSystemBinding
import kotlinx.android.synthetic.main.menu_checkin_system.*

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone


/**
 * Created by Administrator on 2018/2/12.
 */

class SignInActivity : BaseActivity<MenuCheckinSystemBinding>() {
    private var click = true

    override fun setLayoutId(): Int {
        return R.layout.menu_checkin_system
    }

    override fun initLayout() {

    }

    override fun setListener() {
        btnSignIn.setOnClickListener {
            val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss").apply {
                timeZone = TimeZone.getTimeZone("GMT+08")
            }
            val time = sdf.format(Date())

            qiandao_tv.text = "签到成功！当前签到时间为：$time"
            if (click) {
                click = false
                btnSignIn.isEnabled = false
            }
        }

        btnBack.setOnClickListener {
            startActivity(Intent().apply {
                setClass(this@SignInActivity, MainActivity::class.java)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//关掉所要到的界面中间的activity
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)//设置不要刷新将要跳转的界面
            })
        }
    }
}
