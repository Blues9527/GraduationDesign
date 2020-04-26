package com.blues.menu

import android.content.Intent

import com.blues.main.MainActivity
import com.blues.base.BaseActivity
import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.MenuIndividualCenterBinding
import kotlinx.android.synthetic.main.menu_individual_center.*

/**
 * Created by Administrator on 2018/2/12.
 */

class CenterActivity : BaseActivity<MenuIndividualCenterBinding>() {
    override fun setLayoutId(): Int {
        return R.layout.menu_individual_center
    }

    override fun initLayout() {

    }

    override fun setListener() {
        btnBack!!.setOnClickListener {
            startActivity(Intent().apply {
                setClass(this@CenterActivity, MainActivity::class.java)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//关掉所要到的界面中间的activit
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)//设置不要刷新将要跳转的界面
            })
        }
    }
}
