package com.blues.register

import com.blues.http.HttpResponse
import com.blues.login.mvvm.model.UserInfo
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterService {

    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
            @Field("username") username: String,
            @Field("password") password: String,
            @Field("repassword") repassword: String
    ): HttpResponse<UserInfo>
}