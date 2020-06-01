package com.win.ft_home.`interface`

import com.win.ft_home.model.navigation.NavigationItem

/**
 * Create by liwen on 2020/5/29
 */
interface NavigationTabItemSelectedListener {
    fun onItemSelected(item: NavigationItem, position: Int)
}