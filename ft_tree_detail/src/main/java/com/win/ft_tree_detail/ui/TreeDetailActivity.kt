package com.win.ft_tree_detail.ui

import android.os.Bundle
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.win.ft_tree_detail.adapter.TreeDetailListAdapter
import com.win.ft_tree_detail.model.TreeDetailItem
import com.win.lib_base.base.AbsListActivity

class TreeDetailActivity : AbsListActivity<TreeDetailItem, TreeDetailViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun generateAdapter(): PagedListAdapter<TreeDetailItem, RecyclerView.ViewHolder> {
        return TreeDetailListAdapter(this) as PagedListAdapter<TreeDetailItem, RecyclerView.ViewHolder>
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
    }


}
