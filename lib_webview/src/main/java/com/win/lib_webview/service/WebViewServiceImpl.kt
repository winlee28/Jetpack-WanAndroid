package com.win.lib_webview.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.win.lib_base.base.service.ConstantsPath
import com.win.lib_base.base.service.webview.WebViewService
import com.win.lib_webview.WebViewActivity

/**
 * Create by liwen on 2020/5/26
 */
@Route(path = ConstantsPath.WEB_VIEW_SERVICE_PATH)
class WebViewServiceImpl : WebViewService {

    override fun start(context: Context, title: String, url: String) {
        WebViewActivity.start(context, title, url)
    }

    override fun init(context: Context?) {

    }

}