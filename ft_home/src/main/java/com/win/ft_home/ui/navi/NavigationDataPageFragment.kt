package com.win.ft_home.ui.navi

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.win.ft_home.R
import com.win.ft_home.adapter.BaseRecyclerViewAdapter
import com.win.ft_home.adapter.NavigationTabPageAdapter
import com.win.ft_home.databinding.NavigationDataPageBinding
import com.win.ft_home.model.navigation.NavigationItemDetail
import com.win.lib_base.base.BaseFragment
import com.win.lib_base.service.webview.warp.WebViewWarpService

/**
 * Create by liwen on 2020/5/30
 */
class NavigationDataPageFragment :
    BaseFragment<NavigationDataPageViewModel, NavigationDataPageBinding>() {


    private var detailList: MutableList<NavigationItemDetail>? = null

    companion object {
        fun newInstance(articles: MutableList<NavigationItemDetail>): NavigationDataPageFragment {
            val fragment = NavigationDataPageFragment()
            val bundle = Bundle()
            bundle.putString("data", Gson().toJson(articles))
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutResId(): Int = R.layout.navigation_data_page

    override fun initData() {
        val arguments = requireArguments()
        val data = arguments.getString("data")

        detailList = Gson().fromJson<MutableList<NavigationItemDetail>>(
            data,
            object : TypeToken<MutableList<NavigationItemDetail>>() {}.type
        )
    }

    override fun initView() {


        mViewBinding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = NavigationTabPageAdapter(requireContext())
        adapter.dataList = detailList!!
        mViewBinding.recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object :
            BaseRecyclerViewAdapter.OnItemClickListener<NavigationItemDetail> {
            override fun onItemClick(item: NavigationItemDetail, position: Int) {
                WebViewWarpService.instance.start(requireContext(), item.title, item.link)
            }

        })
    }


}