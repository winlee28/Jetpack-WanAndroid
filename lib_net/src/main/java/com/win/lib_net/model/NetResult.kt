package com.win.lib_net.model

import com.win.lib_net.exception.ResultException


/**
 * Created by luyao
 * on 2019/10/12 11:08
 */
sealed class NetResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : NetResult<T>()
    data class Error(val exception: ResultException) : NetResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}