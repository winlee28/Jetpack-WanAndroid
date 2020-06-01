package com.win.lib_base.service.search.wrap

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.win.lib_base.service.ConstantsPath
import com.win.lib_base.service.search.SearchService

/**
 * Create by liwen on 2020/6/1
 */
class SearchWrapService private constructor() {

    @Autowired(name = ConstantsPath.SEARCH_SERVICE_PATH)
    lateinit var service: SearchService

    init {
        ARouter.getInstance().inject(this)
    }

    fun start(context: Context) {
        service.start(context)
    }


    companion object {

        val instance = Singleton.holder

        object Singleton {
            val holder = SearchWrapService()
        }

    }


}