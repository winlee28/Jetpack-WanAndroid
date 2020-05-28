package com.win.ft_login.api

import com.win.lib_base.model.User
import com.win.lib_net.model.BaseModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Create by liwen on 2020/5/27
 */
interface RequestCenter {

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseModel<User>

    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): BaseModel<User>


}