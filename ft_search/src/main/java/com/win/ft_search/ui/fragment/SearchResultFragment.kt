package com.win.ft_search.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.win.ft_search.adapter.SearchResultAdapter
import com.win.ft_search.ui.viewmodel.SearchResultViewModel
import com.win.lib_base.base.AbsListFragment
import com.win.lib_base.datasource.MutablePageKeyedDataSource
import com.win.lib_base.model.DatasBean

/**
 * Create by liwen on 2020/6/1
 */
class SearchResultFragment : AbsListFragment<DatasBean, SearchResultViewModel>() {

    private lateinit var mPagedListAdapter: PagedListAdapter<DatasBean, RecyclerView.ViewHolder>

    companion object {
        fun newInstance(hotKey: String): SearchResultFragment {
            val fragment = SearchResultFragment()
            val bundle = Bundle()
            bundle.putString("hotkey", hotKey)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateViewAfter() {
        super.onCreateViewAfter()

        hiddenActionBar()

        val hotKey = requireArguments().getString("hotkey")
        mViewModel.setHotKey(hotKey!!)

    }

    override fun generateAdapter(): PagedListAdapter<DatasBean, RecyclerView.ViewHolder> {
        mPagedListAdapter =
            SearchResultAdapter(requireContext()) as PagedListAdapter<DatasBean, RecyclerView.ViewHolder>
        return mPagedListAdapter
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mViewModel.getDataSource()!!.invalidate()
        finishRefresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {

        val currentList = mPagedListAdapter.currentList

        if (currentList == null || currentList.size <= 0) {
            return
        }

        val count = currentList.size / 20

        mViewModel.loadDataAfter(
            count,
            object : PageKeyedDataSource.LoadCallback<Int, DatasBean>() {
                override fun onResult(data: MutableList<DatasBean>, adjacentPageKey: Int?) {

                    val dataSource = MutablePageKeyedDataSource<DatasBean>()

                    dataSource.data.addAll(currentList)
                    dataSource.data.addAll(data)

                    val buildNewPageList = dataSource.buildNewPageList(currentList.config)

                    mPagedListAdapter.submitList(buildNewPageList)
                }

            })

        finishRefresh()

    }

}