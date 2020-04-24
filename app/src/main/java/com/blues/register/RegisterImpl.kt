package com.blues.register

import com.blues.http.RetrofitManager

object RegisterImpl {

    suspend fun doRegister(username: String, password: String, repassword: String) =
            RetrofitManager.instance.create(RegisterService::class.java).register(username, password, repassword)
}