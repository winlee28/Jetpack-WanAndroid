package com.win.ft_search.ui

import android.os.Build
import android.os.Bundle
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.win.ft_search.adapter.SearchResultAdapter
import com.win.lib_base.base.AbsListFragment
import com.win.lib_base.model.DatasBean

/**
 * Create by liwen on 2020/6/1
 */
class SearchResultFragment : AbsListFragment<DatasBean, SearchResultViewModel>() {

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

        val arguments = arguments
        val hotKey = requireArguments().getString("hotkey")
        mViewModel.setHotKey(hotKey!!)

    }

    override fun generateAdapter(): PagedListAdapter<DatasBean, RecyclerView.ViewHolder> {
        return SearchResultAdapter(requireContext()) as PagedListAdapter<DatasBean, RecyclerView.ViewHolder>
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {

    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {

    }

}