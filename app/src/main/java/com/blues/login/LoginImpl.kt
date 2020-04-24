package com.blues.login

import com.blues.http.RetrofitManager

object LoginImpl {

    suspend fun doLogin(username: String, password: String) =
            RetrofitManager.instance.create(LoginService::class.java).doLogin(username, password)


}

