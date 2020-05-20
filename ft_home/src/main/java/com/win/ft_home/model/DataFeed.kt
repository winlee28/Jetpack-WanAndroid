package com.win.ft_home.model

/**
 * Create by liwen on 2020-05-19
 */
data class DataFeed(
    val curPage: Int,
    val offset: Int,
    val pageCount: Int,
    val size: Int,
    val total: Int,
    val over: Boolean,
    val datas:MutableList<DatasBean>
    )