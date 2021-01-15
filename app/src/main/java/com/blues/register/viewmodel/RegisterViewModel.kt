package com.blues.register.viewmodel

import androidx.lifecycle.MutableLiveData
import com.blues.base.BaseViewModel
import com.blues.register.RegisterImpl

class RegisterViewModel : BaseViewModel() {

    var isRegister = MutableLiveData<Boolean>()
    var registering = MutableLiveData<Boolean>()

    fun register(username: String, password: String, repassword: String, tips: ((errorMsg:String) -> Unit)?) {
        registering.value = true
        launch(
                block = {
                    val httpResponse = RegisterImpl.doRegister(username, password, repassword)
                    isRegister.value = httpResponse.errorCode == 0
                    if (httpResponse.errorCode == -1 && tips != null) {
                        tips(httpResponse.errorMsg)
                    }
                    registering.value = false
                },
                error = {
                    isRegister.value = false
                    registering.value = false
                }
        )
    }
}