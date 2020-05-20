package com.win.lib_base

import android.app.Application
import android.content.Context
import android.util.Log
import kotlin.properties.Delegates

/**
 * Create by liwen on 2020-05-18
 */
class App : Application() {

    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        Log.e("liwen","app onCreate")
    }
}