package com.win.lib_base.service.login

import android.content.Context
import androidx.lifecycle.LiveData
import com.alibaba.android.arouter.facade.template.IProvider
import com.win.lib_base.model.User

/**
 * Create by liwen on 2020/5/27
 */
interface LoginService : IProvider {

    fun isLogin(): Boolean

    fun getUserInfo(): User?

    fun removeUserInfo()

    fun start(context: Context): LiveData<User>

    fun getLiveData(): LiveData<User>

}