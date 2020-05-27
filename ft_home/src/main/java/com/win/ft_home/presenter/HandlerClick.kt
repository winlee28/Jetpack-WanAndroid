package com.win.ft_home.presenter

import android.content.Context
import com.win.lib_base.service.webview.warp.WebViewWarpService

/**
 * Create by liwen on 2020/5/27
 */
object HandlerClick {

    fun navigationItemClick(context: Context, title: String, url: String) {
        WebViewWarpService.instance.start(context, title, url)
    }

}