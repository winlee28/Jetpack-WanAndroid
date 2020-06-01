package com.win.ft_home.di

import com.win.ft_home.ui.main.MainViewModel
import com.win.ft_home.ui.home.HomeRepository
import com.win.ft_home.ui.home.HomeViewModel
import com.win.ft_home.ui.mine.MineViewModel
import com.win.ft_home.ui.navi.NavigationDataPageViewModel
import com.win.ft_home.ui.navi.NavigationRepository
import com.win.ft_home.ui.navi.NavigationViewModel
import com.win.ft_home.ui.project.ProjectRepository
import com.win.ft_home.ui.project.ProjectViewModel
import com.win.ft_home.ui.project.TabItemViewModel
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
    factory { ProjectRepository() }
    factory { NavigationRepository() }
}


val treeViewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { TreeViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ProjectViewModel(get()) }
    viewModel { TabItemViewModel(get()) }
    viewModel { MineViewModel() }
    viewModel { NavigationViewModel(get()) }
    viewModel { NavigationDataPageViewModel() }
}

