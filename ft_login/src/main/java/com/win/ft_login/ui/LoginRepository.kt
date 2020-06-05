package com.win.ft_login.ui

import com.win.ft_login.api.RequestCenter
import com.win.lib_base.model.User
import com.win.lib_net.model.NetResult
import com.win.lib_net.net.BaseRepository
import com.win.lib_net.net.RetrofitClient

/**
 * Create by liwen on 2020/5/27
 */
class LoginRepository(private val service: RetrofitClient) : BaseRepository() {

    suspend fun login(username: String, password: String): NetResult<User> {
        return safeApiCall(call = { requestLogin(username, password) })
    }

    suspend fun register(
        username: String,
        password: String,
        surePassword: String
    ): NetResult<User> {
        return safeApiCall(call = { requestRegister(username, password, surePassword) })
    }

    private suspend fun requestLogin(username: String, password: String): NetResult<User> =
        executeResponse(
            service.create(RequestCenter::class.java).login(username, password)
        )

    private suspend fun requestRegister(
        username: String,
        password: String,
        surePassword: String
    ): NetResult<User> =
        executeResponse(
            service.create(RequestCenter::class.java).register(username, password, surePassword)
        )


}