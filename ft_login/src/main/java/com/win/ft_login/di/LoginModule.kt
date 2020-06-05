package com.win.ft_login.di

import com.win.ft_login.ui.LoginRepository
import com.win.ft_login.ui.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Create by liwen on 2020/5/27
 */

val loginRepoModule = module {
    single {
        LoginRepository(get())
    }
}


val loginViewModelModule = module {
    viewModel {
        LoginViewModel(get())
    }
}