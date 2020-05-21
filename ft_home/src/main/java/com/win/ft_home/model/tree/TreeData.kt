package com.win.ft_home.model.tree

/**
 * Create by liwen on 2020-05-21
 */
data class TreeData(
    val children: MutableList<TreeDataItem>,
    val name: String,
    val id: Int,
    val visible: Int
)