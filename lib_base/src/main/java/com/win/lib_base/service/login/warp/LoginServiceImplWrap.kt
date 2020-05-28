package com.win.lib_base.service.login.warp

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.win.lib_base.model.User
import com.win.lib_base.service.ConstantsPath
import com.win.lib_base.service.login.LoginService

/**
 * Create by liwen on 2020/5/27
 */
object LoginServiceImplWrap {

    @Autowired(name = ConstantsPath.LOGIN_SERVICE_PATH)
    lateinit var service: LoginService

    init {
        ARouter.getInstance().inject(this)
    }

    fun isLogin(v:Int): Boolean {
        Log.e("liwen","======$v")
        return service.isLogin()
    }


    fun getUserInfo(): User? {
        return service.getUserInfo()
    }

    fun removeUserInfo() {
        service.removeUserInfo()
    }

    fun start(context: Context): LiveData<User> {
        return service.start(context)
    }


    fun getLiveData():LiveData<User>{
        return service.getLiveData()
    }

}