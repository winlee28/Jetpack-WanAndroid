package com.win.lib_base.service.search

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Create by liwen on 2020/6/1
 */
interface SearchService : IProvider {

    fun start(context: Context)

}