package com.blues.login.mvvm.vm

import androidx.lifecycle.MutableLiveData
import com.blues.base.BaseViewModel
import com.blues.login.LoginImpl

class LoginViewModel : BaseViewModel() {
    var isLogin = MutableLiveData<Boolean>()
    val logining = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {
        logining.value = true
        launch(
                block = {
                    val httpResponse = LoginImpl.doLogin(username, password)
                    isLogin.value = httpResponse.errorCode == 0
                    logining.value = false
                },
                error = {
                    isLogin.value = false
                    logining.value = false
                }
        )
    }
}