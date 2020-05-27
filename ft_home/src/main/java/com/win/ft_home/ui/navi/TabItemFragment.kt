package com.win.ft_home.ui.navi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.win.ft_home.adapter.NavigationPageAdapter
import com.win.ft_home.model.navigation.NavigationItemSub
import com.win.ft_home.model.navigation.NavigationModel
import com.win.lib_base.base.AbsListFragment
import com.win.lib_base.datasource.MutablePageKeyedDataSource

/**
 * Create by liwen on 2020/5/26
 */
class TabItemFragment : AbsListFragment<NavigationItemSub, TabItemViewModel>() {

    companion object {
        fun newInstance(id: Int): TabItemFragment {
            val args = Bundle()
            args.putInt("id", id)
            val fragment = TabItemFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateViewAfter() {
        super.onCreateViewAfter()
        hiddenActionBar()
        val arguments = requireArguments()
        val id = arguments.getInt("id")
        mViewModel.setId(id)

    }


    override fun generateAdapter(): PagedListAdapter<NavigationItemSub, RecyclerView.ViewHolder> {
        return NavigationPageAdapter(requireContext()) as PagedListAdapter<NavigationItemSub, RecyclerView.ViewHolder>
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mViewModel.getDataSource()!!.invalidate()
        finishRefresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {

        val currentList = mAdapter.currentList
        if (currentList != null && currentList.size <= 0) {
            return
        }

        val config = currentList!!.config

        val count = currentList.size / 15

        mViewModel.getTabPageData(count,
            object : PageKeyedDataSource.LoadCallback<Int, NavigationItemSub>() {
                override fun onResult(data: MutableList<NavigationItemSub>, adjacentPageKey: Int?) {

                    val dataSource = MutablePageKeyedDataSource<NavigationItemSub>()

                    dataSource.data.addAll(currentList)
                    dataSource.data.addAll(data)

                    val pageList = dataSource.buildNewPageList(config)

                    mAdapter.submitList(pageList)

                }

            })

        finishRefresh()

    }

}