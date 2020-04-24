package com.blues.login

import com.blues.http.HttpResponse
import com.blues.login.mvvm.model.UserInfo
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun doLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): HttpResponse<UserInfo>
}