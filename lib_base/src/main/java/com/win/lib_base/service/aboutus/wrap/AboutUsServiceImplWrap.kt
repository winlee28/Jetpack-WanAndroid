package com.win.lib_base.service.aboutus.wrap

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.win.lib_base.model.User
import com.win.lib_base.service.ConstantsPath
import com.win.lib_base.service.aboutus.AboutUsService
import com.win.lib_base.service.login.LoginService

/**
 * Create by liwen on 2020/5/27
 */
object AboutUsServiceImplWrap {

    @Autowired(name = ConstantsPath.ABOUT_US_SERVICE_PATH)
    lateinit var service: AboutUsService

    init {
        ARouter.getInstance().inject(this)
    }

    fun start(context: Context) {
        return service.start(context)
    }


}