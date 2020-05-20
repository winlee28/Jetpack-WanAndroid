package com.win.ft_home.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.win.ft_home.MainViewModel
import com.win.ft_home.R
import com.win.ft_home.databinding.ActivityMainBinding
import com.win.lib_base.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val navView: BottomNavigationView = mViewBinding.navView

        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)
    }

}
