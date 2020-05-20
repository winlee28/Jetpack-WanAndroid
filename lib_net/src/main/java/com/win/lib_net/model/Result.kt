package com.win.lib_net.model

import com.win.lib_net.exception.ResultException


/**
 * Created by luyao
 * on 2019/10/12 11:08
 */
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val dataa: T) : Result<T>()
    data class Error(val exception: ResultException) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[dataa=$dataa]"
            is Error -> "Error[exception=$exception]"
        }
    }
}