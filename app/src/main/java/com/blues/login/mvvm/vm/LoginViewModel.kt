package com.blues.login.mvvm.vm

import androidx.lifecycle.MutableLiveData
import com.blues.base.BaseViewModel
import com.blues.login.LoginImpl

class LoginViewModel : BaseViewModel() {
    var isLogin = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {

        launch(
                block = {
                    val httpResponse = LoginImpl.doLogin(username, password)
                    isLogin.value = httpResponse.errorCode == 0
                },
                error = {
                    isLogin.value = false
                }
        )
    }
}