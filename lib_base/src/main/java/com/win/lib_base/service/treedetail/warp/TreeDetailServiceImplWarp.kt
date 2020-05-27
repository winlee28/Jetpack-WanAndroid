package com.win.lib_base.service.treedetail.warp

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.win.lib_base.service.ConstantsPath
import com.win.lib_base.service.treedetail.TreeDetailService

/**
 * Create by liwen on 2020/5/26
 */
class TreeDetailServiceImplWarp private constructor() {

    @Autowired(name = ConstantsPath.TREE_DETAIL_SERVICE_PATH)
    lateinit var service: TreeDetailService


    init {
        ARouter.getInstance().inject(this)
    }

    companion object {

        val instance = SingletonHolder.holder

        object SingletonHolder {
            val holder = TreeDetailServiceImplWarp()
        }
    }


    fun start(context: Context, cid: Int, title: String) {
        service.start(context, cid, title)
    }

}