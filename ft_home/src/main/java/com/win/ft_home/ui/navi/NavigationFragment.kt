package com.win.ft_home.ui.navi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.win.ft_home.R
import com.win.ft_home.databinding.FragmentNavigationBinding
import com.win.lib_base.base.BaseFragment
import com.win.lib_common_ui.flowlayout.TagFlowLayout
import com.win.lib_common_ui.flowlayout.adapter.TagAdapter

class NavigationFragment : BaseFragment<NavigationViewModel, FragmentNavigationBinding>() {


    override fun getLayoutResId(): Int = R.layout.fragment_navigation

    override fun initData() {

        mViewModel.getNavigationData().observe(this, Observer {

        })

    }

    override fun initView() {

    }


}