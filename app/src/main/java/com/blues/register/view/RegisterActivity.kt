package com.blues.register.view

import android.graphics.Rect
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.blues.base.BaseVmActivity
import com.blues.login.mvvm.view.LoginActivity
import com.blues.register.viewmodel.RegisterViewModel

import com.example.blues.myapplication.R
import com.example.blues.myapplication.databinding.RegisterMainBinding

class RegisterActivity : BaseVmActivity<RegisterMainBinding, RegisterViewModel>(), TextWatcher {

    private lateinit var username: String
    private lateinit var password: String
    private lateinit var repassword: String


    override fun setLayoutResId(): Int {
        return R.layout.register_main
    }

    override fun setViewModelClass(): Class<RegisterViewModel> {
        return RegisterViewModel::class.java
    }

    override fun initView() {
        mDataBinding.etUsername.setCompoundDrawables(
                resources.getDrawable(R.mipmap.blue_personalcenter).apply { bounds = Rect(0, 0, 50, 50) },
                null,
                null,
                null)
        mDataBinding.etPassword.setCompoundDrawables(
                resources.getDrawable(R.mipmap.blue_password).apply { bounds = Rect(0, 0, 50, 50) },
                null,
                null,
                null)
        mDataBinding.etRepassword.setCompoundDrawables(
                resources.getDrawable(R.mipmap.blue_password).apply { bounds = Rect(0, 0, 50, 50) },
                null,
                null,
                null)
    }

    override fun setListener() {
        mDataBinding.etUsername.addTextChangedListener(this)
        mDataBinding.etPassword.addTextChangedListener(this)
        mDataBinding.etRepassword.addTextChangedListener(this)

        mDataBinding.btnRegister.setOnClickListener {

            when {
                username.isEmpty() -> showToast("账号不能为空")
                password.isEmpty() -> showToast("密码不能为空")
                repassword.isEmpty() -> showToast("密码不能为空")
                !TextUtils.equals(password, repassword) -> showToast("两次密码输入不一致，请重新输入密码")
                else -> {
                    mViewModel.register(username, password, repassword)
                }
            }
        }

        //返回按钮，返回登陆界面
        mDataBinding.btnBack.setOnClickListener {
            ActivityUtil.finish(RegisterActivity::class.java)
        }
    }

    override fun observe() {
        mViewModel.isRegister.observe(this, Observer {
            if (it) {
                //注册完成，页面跳转及销毁当前页面
                ActivityUtil.start(LoginActivity::class.java)
                ActivityUtil.finish(RegisterActivity::class.java)
            }
        })
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        username = mDataBinding.etUsername.text.toString().trim()
        password = mDataBinding.etPassword.text.toString().trim()
        repassword = mDataBinding.etRepassword.text.toString().trim()
    }

}
