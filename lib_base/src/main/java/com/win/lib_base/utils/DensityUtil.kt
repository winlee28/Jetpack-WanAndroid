package com.win.lib_base.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.DisplayMetrics

/**
 * Created by liwen on 2015/10/10.
 */
object DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    @JvmStatic
    fun dip2px(dpValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    @JvmStatic
    fun px2dip(pxValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }


    /**
     * 横竖屏判断
     *
     * @return
     */
    @JvmStatic
    fun isLandscape(): Boolean {

        if (Resources.getSystem().configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return true
        } else if (Resources.getSystem().configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            return false
        }
        return false
    }

    @JvmStatic
    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    @JvmStatic
    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    @JvmStatic
    private fun getDisplayMetrics(): DisplayMetrics {
        return Resources.getSystem().displayMetrics
    }
}
