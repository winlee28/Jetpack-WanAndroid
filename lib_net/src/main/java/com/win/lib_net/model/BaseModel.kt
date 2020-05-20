package com.win.lib_net.model

/**
 * Created by luyao
 * on 2018/3/13 14:38
 */
data class BaseModel<out T>(val errorCode: Int, val errorMsg: String, val data: T)