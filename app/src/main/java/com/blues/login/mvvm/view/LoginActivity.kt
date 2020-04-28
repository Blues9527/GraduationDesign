package com.blues.login.mvvm.view

import android.graphics.Rect
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.blues.application.App
import com.blues.main.MainActivity
import com.blues.register.view.RegisterActivity
import com.blues.base.BaseVmActivity
import com.blues.framework.widget.DrawableEditText
import com.blues.login.mvvm.vm.LoginViewModel
import com.blues.util.SpUtils

import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.LoginMainBinding

class LoginActivity : BaseVmActivity<LoginMainBinding, LoginViewModel>(), TextWatcher {
    private lateinit var username: String
    private lateinit var password: String

    companion object {
        const val tag: String = "BluesTest"
        const val usernameKey: String = "username"
        const val passwordKey: String = "password"
    }

    override fun setLayoutResId(): Int {
        return R.layout.login_main
    }

    override fun initView() {
        //判断之前是否记录过用户信息，保存过的则直接读取用户信息
        val savedUsername = SpUtils.getStr(mContext, usernameKey)
        val savedPassword = SpUtils.getStr(mContext, passwordKey)

        Log.i(tag, "saved msg, username-->$savedUsername, password-->$savedPassword")

        if (!savedUsername.isNullOrEmpty()) {
            mDataBinding.etUsername.setText(savedUsername)
            username = savedUsername
        }
        if (!savedPassword.isNullOrEmpty()) {
            mDataBinding.etPassword.setText(savedPassword)
            password = savedPassword
        }
    }

    override fun setListener() {
        mDataBinding.etUsername.also {
            it.addTextChangedListener(this)
            it.setDrawableListener(object : DrawableEditText.OnDrawableListener {
                override fun onRightListener(v: View) {
                    //清空edit text
                    it.setText("")
                }
            })
        }

        mDataBinding.etPassword.also {
            it.addTextChangedListener(this)
            it.setDrawableListener(object : DrawableEditText.OnDrawableListener {
                override fun onRightListener(v: View) {
                    //清空edit text
                    it.setText("")
                }
            })
        }

        mDataBinding.tvRegisterNow.setOnClickListener {
            ActivityUtil.start(RegisterActivity::class.java)
        }

        mDataBinding.btnLogin.setOnClickListener {
            when {
                username.isEmpty() -> Toast.makeText(App.instance, "账号不能为空", Toast.LENGTH_SHORT)
                        .show()
                password.isEmpty() -> Toast.makeText(App.instance, "密码不能为空", Toast.LENGTH_SHORT)
                        .show()
                else -> {
                    if (mDataBinding.cbRemember.isChecked) {
                        //保存账号密码
                        SpUtils.saveStr(mContext, usernameKey, username)
                        SpUtils.saveStr(mContext, passwordKey, password)
                    }
                    mViewModel.login(username, password)
                }
            }
        }
    }

    override fun observe() {
        mViewModel.isLogin.observe(this, Observer {
            if (it) {
                //页面跳转
                ActivityUtil.start(MainActivity::class.java)
            } else {
                Toast.makeText(App.instance, "登陆错误", Toast.LENGTH_SHORT).show()
            }
        })

        mViewModel.logining.observe(this, Observer {
            if (it) showProgressDialog(R.string.logging_in) else hideProgressDialog()
        })
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        username = mDataBinding.etUsername.text.toString().trim()
        password = mDataBinding.etPassword.text.toString().trim()
    }


    override fun setViewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

}
