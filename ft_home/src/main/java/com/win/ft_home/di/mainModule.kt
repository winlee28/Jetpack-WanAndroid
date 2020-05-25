package com.win.ft_home.di

import com.win.ft_home.ui.tree.TreeRepository
import com.win.ft_home.ui.tree.TreeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Create by liwen on 2020/5/25
 */

val treeRepoModule = module {
    factory {
        TreeRepository()
    }
}


val treeViewModelModule = module {
    viewModel {
        TreeViewModel(get())
    }
}

