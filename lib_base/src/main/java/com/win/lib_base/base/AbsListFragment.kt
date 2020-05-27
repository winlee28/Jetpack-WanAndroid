package com.win.lib_base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.win.lib_base.R
import com.win.lib_base.databinding.AbsListLayoutBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

/**
 * Create by liwen on 2020/5/26
 */
abstract class AbsListFragment<T, V : AbsListViewModel<T>> : Fragment(), OnRefreshListener,
    OnLoadMoreListener {

    lateinit var mAdapter: PagedListAdapter<T, RecyclerView.ViewHolder>

    lateinit var mViewModel: V

    private lateinit var mRefreshLayout: SmartRefreshLayout
    private lateinit var mRecycleView: RecyclerView
    private lateinit var mBinding: AbsListLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.abs_list_layout,
            container,
            false
        )

        mRefreshLayout = mBinding.refreshLayout
        mRecycleView = mBinding.recycleView

        mRecycleView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        decoration.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.home_list_divier
            )!!
        )
        mRecycleView.addItemDecoration(decoration)

        mAdapter = generateAdapter()
        mRecycleView.adapter = mAdapter

        mRefreshLayout.setEnableRefresh(true)
        mRefreshLayout.setEnableLoadMore(true)

        mRefreshLayout.setOnRefreshListener(this)
        mRefreshLayout.setOnLoadMoreListener(this)

        initViewModel()

        onCreateViewAfter()

        return mBinding.root
    }

    open fun onCreateViewAfter() {

    }

    fun hiddenActionBar() {
        mBinding.actionBar.visibility = View.GONE
    }


    private fun initViewModel() {

        //kotlin + koin
        val clazz =
            this.javaClass.kotlin.supertypes[0].arguments[1].type!!.classifier!! as KClass<V>
        mViewModel = getViewModel<V>(clazz)


        mViewModel.getPageData().observe(viewLifecycleOwner, Observer {
            submitPageList(it)
        })
    }


    fun submitPageList(pagedList: PagedList<T>) {
        mAdapter.submitList(pagedList)
    }

    fun finishRefresh() {

        val state = mRefreshLayout.state
        if (state.isOpening && state.isHeader) {
            mRefreshLayout.finishRefresh()
        } else if (state.isOpening && state.isFooter) {
            mRefreshLayout.finishLoadMore()
        }
    }

    abstract fun generateAdapter(): PagedListAdapter<T, RecyclerView.ViewHolder>


}