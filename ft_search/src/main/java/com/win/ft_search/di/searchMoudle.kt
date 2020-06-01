package com.win.ft_search.di

import com.win.ft_search.ui.HotKeyViewModel
import com.win.ft_search.ui.SearchRepository
import com.win.ft_search.ui.SearchResultViewModel
import com.win.ft_search.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Create by liwen on 2020/6/1
 */

val searchRepoModule = module {
    single {
        SearchRepository()
    }
}

val searchViewModelModule = module {

    viewModel {
        SearchViewModel(get())
    }
    viewModel {
        HotKeyViewModel(get())
    }

    viewModel { SearchResultViewModel(get()) }
}