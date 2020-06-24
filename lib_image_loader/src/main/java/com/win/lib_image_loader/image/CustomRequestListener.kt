package com.win.lib_image_loader.image

import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

interface CustomRequestListener : RequestListener<Any?> {
//    override fun onLoadFailed(
//        e: GlideException?,
//        model: Any,
//        target: Target<*>?,
//        isFirstResource: Boolean
//    ): Boolean
//
//    override fun onResourceReady(
//        resource: Any?,
//        model: Any,
//        target: Target<*>?,
//        dataSource: DataSource,
//        isFirstResource: Boolean
//    ): Boolean
}