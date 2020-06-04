package com.win.ft_home.ui.home

import android.util.Log
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.win.ft_home.adapter.HomeListAdapter
import com.win.lib_base.base.AbsListFragment
import com.win.lib_base.datasource.MutablePageKeyedDataSource
import com.win.lib_base.model.DatasBean

/**
 * Create by liwen on 2020/6/4
 */
class HomeListFragment : AbsListFragment<DatasBean, HomeListViewModel>() {


    override fun onCreateViewAfter() {
        super.onCreateViewAfter()
        hiddenActionBar()
    }

    override fun generateAdapter(): PagedListAdapter<DatasBean, RecyclerView.ViewHolder> {
        return HomeListAdapter(requireContext()) as PagedListAdapter<DatasBean, RecyclerView.ViewHolder>
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mViewModel.getDataSource()!!.invalidate()
        finishRefresh()

    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {

        //当加载更多的时候返回了空数据 需要手动接管数据加载否则paging框架将不会继续加载数据

        val currentList = mAdapter.currentList

        if (currentList == null || currentList.size <= 0) {
            return
        }

        val key = currentList.size / 20

        mViewModel.getHomeList(
            key,
            key + 1,
            object : PageKeyedDataSource.LoadCallback<Int, DatasBean>() {
                override fun onResult(data: MutableList<DatasBean>, adjacentPageKey: Int?) {

                    //把data转成pageList
                    val dataSource = MutablePageKeyedDataSource<DatasBean>()

                    dataSource.data.addAll(currentList)
                    dataSource.data.addAll(data)

                    val pageList =
                        dataSource.buildNewPageList(mAdapter.currentList!!.config)

                    mAdapter.submitList(pageList)

                }
            })

        finishRefresh()
    }

}