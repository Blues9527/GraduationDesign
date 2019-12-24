package com.blues

import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.blues.base.BaseActivity
import com.blues.database.user.User
import com.blues.database.user.UserManager

import com.example.blues.myapplication.R
import kotlinx.android.synthetic.main.register_main.*

class RegisterActivity : BaseActivity(), TextWatcher {


    private var username: String? = null
    private var password: String? = null
    private var email: String? = null
    private var phone: String? = null


    override fun setLayoutId(): Int {
        return R.layout.register_main
    }

    override fun initLayout() {

    }

    override fun setListener() {
        etUsername.addTextChangedListener(this@RegisterActivity)
        etPassword.addTextChangedListener(this@RegisterActivity)
        etEmail.addTextChangedListener(this@RegisterActivity)
        etPhone.addTextChangedListener(this@RegisterActivity)

        btnRegister.setOnClickListener {

            //四个EditText 的值不能为空
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone)) {
                Log.i("Blues", "username:$username|password:$password|email:$email|phone:$phone")
                //提示注册失败
                Toast.makeText(this@RegisterActivity, "注册失败！", Toast.LENGTH_SHORT).show()
            } else {

                if (UserManager.getInstance().userExits(username)) {
                    //提示用户已存在
                    Toast.makeText(this@RegisterActivity, "用户已存在！", Toast.LENGTH_SHORT).show()

                } else {
                    //执行插入操作，并返回注册页面
                    UserManager.getInstance().insertUser(User().also {
                        it.username = username
                        it.password = password
                        it.email = email
                        it.phone = phone
                    })

                    Toast.makeText(this@RegisterActivity, "注册成功,两秒后跳转到登陆界面！", Toast.LENGTH_SHORT).show()

                    startActivity(Intent().apply {
                        setClass(this@RegisterActivity, LoginActivity::class.java)
                        this.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//关掉所要到的界面中间的activity
                        addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)//设置不要刷新将要跳转的界面
                    })
                }

            }
            finish()
        }

        //返回按钮，返回登陆界面
        btnBack.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        username = etUsername.text.toString().trim { it <= ' ' }
        password = etPassword.text.toString().trim { it <= ' ' }
        email = etEmail.text.toString().trim { it <= ' ' }
        phone = etPhone.text.toString().trim { it <= ' ' }
    }
}
