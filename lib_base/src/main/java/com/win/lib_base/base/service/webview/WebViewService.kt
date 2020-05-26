package com.win.lib_base.base.service.webview

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Create by liwen on 2020/5/26
 */
interface WebViewService : IProvider {

    fun start(
        context: Context, title: String
        , url: String
    )
}