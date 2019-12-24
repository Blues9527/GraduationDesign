package com.blues

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.blues.base.BaseActivity
import com.blues.database.user.UserManager

import com.blues.util.SpUtils
import com.blues.util.SpUtils.Companion.saveBoolean
import com.blues.util.SpUtils.Companion.saveStr
import com.blues.util.ThreadManager
import com.example.blues.myapplication.R
import kotlinx.android.synthetic.main.login_main.*

class LoginActivity : BaseActivity(), TextWatcher {
    private var username: String? = null
    private var password: String? = null


    override fun setLayoutId(): Int {
        return R.layout.login_main
    }

    override fun initLayout() {
        //判断之前是否记录过用户信息，保存过的则直接读取用户信息
        if (SpUtils.getBoolean(mActivity, "is_remembered")) {

            username = SpUtils.getStr(mActivity, "username")
            password = SpUtils.getStr(mActivity, "psd")

            etUsername!!.setText(username)
            etPassword!!.setText(password)
        }
    }

    override fun setListener() {
        etUsername.addTextChangedListener(this@LoginActivity)
        etPassword.addTextChangedListener(this@LoginActivity)


        tvRegisterNow.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {

            //判断用户是否已存在，如果已存在，则跳转到MainActivity，如果不存在，则提示用户名密码错误
            if (UserManager.getInstance().userExits(username)) {
                //如果用户存在，则用shared preferences.editor保存用户数据
                if (cbRemember.isChecked) {
                    SpUtils.let {
                        saveStr(mActivity, "username", username)
                        saveStr(mActivity, "psd", password)
                        saveBoolean(mActivity, "is_remembered", true)
                    }
                }

                startActivity(Intent(this@LoginActivity, MainActivity::class.java).apply {
                    putExtra("login_username", username)
                    putExtra("login_password", password)
                })

                finish()//完成页面跳转后结束当前activity，即将当前activity从盏移除。

            } else {
                Toast.makeText(this@LoginActivity, "用户不存在或用户名密码错误！", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        username = etUsername.text.toString().trim { it <= ' ' }
        password = etPassword.text.toString().trim { it <= ' ' }
    }

}
