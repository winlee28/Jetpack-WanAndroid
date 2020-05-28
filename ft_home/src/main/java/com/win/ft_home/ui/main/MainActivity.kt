package com.win.ft_home.ui.main

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.iterator
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.get
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.win.ft_home.R
import com.win.ft_home.databinding.ActivityMainBinding
import com.win.lib_base.base.BaseActivity
import com.win.lib_base.service.login.warp.LoginServiceImplWrap

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>()
//    , NavController.OnDestinationChangedListener
{

    private lateinit var navController: NavController

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView: BottomNavigationView = mViewBinding.navView

        navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)

//        navController.addOnDestinationChangedListener(this)

    }

    override fun initData() {

    }

    override fun initView() {

    }

//    override fun onDestinationChanged(
//        controller: NavController,
//        destination: NavDestination,
//        arguments: Bundle?
//    ) {
//        val currentDestination = controller.currentDestination
//
//        if (destination.id == R.id.navigation_mine) {
//            if (!LoginServiceImplWrap.isLogin()) {
//                LoginServiceImplWrap.start(this)
//            }
//        }
//
//    }

}
