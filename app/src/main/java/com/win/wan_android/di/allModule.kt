package com.win.wan_android.di

import com.win.ft_home.di.treeRepoModule
import com.win.ft_home.di.treeViewModelModule
import com.win.ft_login.di.loginRepoModule
import com.win.ft_login.di.loginViewModelModule
import com.win.ft_search.di.searchRepoModule
import com.win.ft_search.di.searchViewModelModule
import com.win.ft_tree_detail.di.detailRepoModule
import com.win.ft_tree_detail.di.detailViewModelModule

/**
 * Create by liwen on 2020/5/25
 */

val allModule = listOf(
    treeRepoModule, treeViewModelModule,
    detailRepoModule, detailViewModelModule,
    loginRepoModule, loginViewModelModule,
    searchRepoModule,searchViewModelModule

)