package com.win.ft_tree_detail.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.win.ft_tree_detail.adapter.TreeDetailListAdapter
import com.win.ft_tree_detail.model.TreeDetailItem
import com.win.lib_base.base.AbsListActivity
import com.win.lib_base.model.DatasBean
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TreeDetailActivity : AbsListActivity<DatasBean, TreeDetailViewModel>() {

    override fun initActionBar() {

            mBinding.mTvTitle.text = "Android studio"
    }


    override fun generateAdapter(): PagedListAdapter<DatasBean, RecyclerView.ViewHolder> {
        return TreeDetailListAdapter(this) as PagedListAdapter<DatasBean, RecyclerView.ViewHolder>
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        finishRefresh()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mViewModel.getDataSource()?.invalidate()
        finishRefresh()
    }


}
