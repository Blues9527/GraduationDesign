package com.blues.register

import androidx.lifecycle.MutableLiveData
import com.blues.base.BaseViewModel

class RegisterViewModel : BaseViewModel() {

    var isRegister = MutableLiveData<Boolean>()

    fun register(username: String, password: String, repassword: String) {
        launch(
                block = {
                    val httpResponse = RegisterImpl.doRegister(username, password, repassword)
                    isRegister.value = httpResponse.errorCode == 0
                },
                error = {
                    isRegister.value = false
                }
        )
    }
}