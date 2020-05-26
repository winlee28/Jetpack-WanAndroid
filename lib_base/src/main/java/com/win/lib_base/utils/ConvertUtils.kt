package com.win.lib_base.utils

import android.text.TextUtils
import com.win.lib_base.model.DatasBean

/**
 * Create by liwen on 2020-05-19
 */
object ConvertUtils {

    @JvmStatic
    fun convertName(feed: DatasBean): String {

        return if (TextUtils.isEmpty(feed.author)) {
            "推荐者：${feed.shareUser}"
        } else {
            "作者：${feed.author}"
        }

    }

}