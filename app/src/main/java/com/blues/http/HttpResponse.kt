package com.blues.http

import android.accounts.NetworkErrorException

data class HttpResponse<T>(val errorCode: Int, val errorMsg: String, private val data: T) {

    fun fetchData(): T {
        if (errorCode == 0) {
            return data
        } else {
            throw NetworkErrorException()
        }
    }
}