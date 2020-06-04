package com.win.lib_base.utils

import android.content.Context

/**
 * Create by liwen on 2020/6/4
 */
class BaseContext private constructor() {


    private lateinit var mContext: Context

    fun init(context: Context) {
        mContext = context
    }

    fun getContext(): Context {
        return mContext
    }

    companion object {

        val instance = Singleton.holder

        object Singleton {
            val holder = BaseContext()
        }

    }

}