package com.win.ft_home.di

import com.win.ft_home.ui.home.HomeRepository
import com.win.ft_home.ui.home.HomeViewModel
import com.win.ft_home.ui.tree.TreeRepository
import com.win.ft_home.ui.tree.TreeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Create by liwen on 2020/5/25
 */

val treeRepoModule = module {
    factory { TreeRepository() }
    factory { HomeRepository() }
}


val treeViewModelModule = module {
    viewModel { TreeViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}

