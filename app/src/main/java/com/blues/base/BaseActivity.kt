package com.blues.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var mActivity: BaseActivity<V>

    protected lateinit var mDataBinding: V

    //设置layout id
    abstract fun setLayoutId(): Int

    //初始化布局
    abstract fun initLayout()

    //设置监听
    abstract fun setListener()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivity = this

        mDataBinding = DataBindingUtil.setContentView(mActivity, setLayoutId())

        initLayout()

        setListener()
    }

}