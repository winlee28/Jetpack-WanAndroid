package com.win.ft_login

import android.content.Context
import androidx.lifecycle.LiveData
import com.alibaba.android.arouter.facade.annotation.Route
import com.win.ft_login.ui.LoginActivity
import com.win.lib_base.model.User
import com.win.lib_base.service.ConstantsPath
import com.win.lib_base.service.login.LoginService

/**
 * Create by liwen on 2020/5/27
 */
@Route(path = ConstantsPath.LOGIN_SERVICE_PATH)
class LoginServiceImpl : LoginService {

    override fun isLogin(): Boolean {
        return UserManager.isLogin()
    }

    override fun getUserInfo(): User? {
        return UserManager.getUser()
    }

    override fun removeUserInfo() {
        UserManager.removeUser()
    }

    override fun start(context: Context): LiveData<User> {
        return UserManager.start(context)
    }

    override fun getLiveData(): LiveData<User> {
        return UserManager.getLoginLiveData()
    }

    override fun init(context: Context?) {

    }
}