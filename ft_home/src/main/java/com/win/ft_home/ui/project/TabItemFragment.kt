package com.win.ft_home.ui.project

import android.os.Bundle
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.win.ft_home.adapter.PeojectPageAdapter
import com.win.ft_home.model.project.ProjectItemSub
import com.win.lib_base.base.AbsListFragment
import com.win.lib_base.datasource.MutablePageKeyedDataSource

/**
 * Create by liwen on 2020/5/26
 */
class TabItemFragment : AbsListFragment<ProjectItemSub, TabItemViewModel>() {

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


    override fun generateAdapter(): PagedListAdapter<ProjectItemSub, RecyclerView.ViewHolder> {
        return PeojectPageAdapter(requireContext()) as PagedListAdapter<ProjectItemSub, RecyclerView.ViewHolder>
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mViewModel.getDataSource()!!.invalidate()
        finishRefresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {

        val currentList = mAdapter.currentList
        if (currentList == null || currentList.size <= 0) {
            return
        }

        val config = currentList!!.config

        val count = currentList.size / 15

        mViewModel.getTabPageData(count,
            object : PageKeyedDataSource.LoadCallback<Int, ProjectItemSub>() {
                override fun onResult(data: MutableList<ProjectItemSub>, adjacentPageKey: Int?) {

                    val dataSource = MutablePageKeyedDataSource<ProjectItemSub>()

                    dataSource.data.addAll(currentList)
                    dataSource.data.addAll(data)

                    val pageList = dataSource.buildNewPageList(config)

                    mAdapter.submitList(pageList)

                }

            })

        finishRefresh()

    }

}