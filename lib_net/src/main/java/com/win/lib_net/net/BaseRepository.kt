package com.win.lib_net.net

import com.win.lib_net.exception.DealException

import com.win.lib_net.exception.ResultException
import com.win.lib_net.model.BaseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import com.win.lib_net.model.Result

open class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> BaseModel<T>): BaseModel<T> {
        return call.invoke()
    }

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Result<T>
    ): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            //这里统一处理异常
            Result.Error(DealException.handlerException(e))
        }
    }

    suspend fun <T : Any> executeResponse(
        response: BaseModel<T>,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): Result<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                errorBlock?.let { it() }
                Result.Error(
                    ResultException(
                        response.errorCode.toString(),
                        response.errorMsg
                    )
                )
            } else {
                successBlock?.let { it() }
                Result.Success(response.data)
            }
        }
    }


}