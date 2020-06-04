package com.win.ft_home.ui.home

import androidx.lifecycle.Observer
import com.win.ft_home.R
import com.win.ft_home.adapter.HomeBannerAdapter
import com.win.ft_home.databinding.FragmentHomeBinding
import com.win.ft_home.model.home.Banner
import com.win.lib_base.base.BaseFragment


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_home

    override fun initView() {

        val supportFragmentManager = activity?.supportFragmentManager
        val beginTransaction = supportFragmentManager?.beginTransaction()
        beginTransaction?.replace(R.id.homeListContainer, HomeListFragment::class.java, null)
        beginTransaction?.commit()

    }

    override fun initData() {

        mViewModel.apply { getBanner() }

        mViewModel.getBannerLiveData()
            .observe(viewLifecycleOwner,
                Observer<List<Banner>> {
                    mViewBinding.banner.adapter = HomeBannerAdapter(it)
                })

    }

}