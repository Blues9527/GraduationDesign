package com.blues.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var mActivity: BaseActivity

    //设置layout id
    abstract fun setLayoutId(): Int

    //初始化布局
    abstract fun initLayout()

    //设置监听
    abstract fun setListener()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(setLayoutId())

        mActivity = this

        initLayout()

        setListener()
    }

}