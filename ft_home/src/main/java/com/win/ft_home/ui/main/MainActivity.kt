package com.win.ft_home.ui.main

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.iterator
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.win.ft_home.R
import com.win.ft_home.databinding.ActivityMainBinding
import com.win.lib_base.base.BaseActivity
import com.win.lib_base.base.FixFragmentNavigator
import com.win.lib_base.service.login.warp.LoginServiceImplWrap

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private lateinit var navView: BottomNavigationView
    private lateinit var navController: NavController

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val navView: BottomNavigationView = mViewBinding.navView

//        navController = findNavController(R.id.nav_host_fragment)
//
//        navView.setupWithNavController(navController)


        navView = mViewBinding.navView

        //添加自定义的FixFragmentNavigator
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val fragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val fragmentNavigator =
            FixFragmentNavigator(this, supportFragmentManager, fragment!!.id)
        navController.navigatorProvider.addNavigator(fragmentNavigator)

        navController.setGraph(R.navigation.mobile_navigation)

        navView.setupWithNavController(navController)

    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun onBackPressed() {

//        如果调用 super.onBackPressed()，navigation会操作回退栈,切换到之前显示的页面，导致 页面叠加错乱
//        super.onBackPressed()

        val id = navController.currentDestination?.id!!
        val homeNavi = navController.graph[R.id.navigation_home].id
        if (id != homeNavi) {
            navView.selectedItemId = R.id.navigation_home
        } else {
            finish()
        }

    }

}
