package com.win.ft_home.model.navigation

/**
 * Create by liwen on 2020/5/28
 */
data class NavigationItem(
    val cid: Int,
    val name: String,
    val articles: MutableList<NavigationItemDetail>
)