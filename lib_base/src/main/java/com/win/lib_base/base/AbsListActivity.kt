package com.win.lib_base.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.win.lib_base.R
import com.win.lib_base.databinding.ActivityAbsBinding
import java.lang.reflect.ParameterizedType

/**
 * Create by liwen on 2020-05-21
 *
 * 作为列表布局的父类
 */
abstract class AbsListActivity<T, V : AbsListViewModel<T>> : AppCompatActivity(),
    OnLoadMoreListener,
    OnRefreshListener {

    private lateinit var mAdapter: PagedListAdapter<T, RecyclerView.ViewHolder>

    private lateinit var mViewModel: V

    private lateinit var mRefreshLayout: SmartRefreshLayout
    private lateinit var mRecycleView: RecyclerView

    private lateinit var mBinding: ActivityAbsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_abs)


        mRefreshLayout = mBinding.refreshLayout
        mRecycleView = mBinding.recycleView

        mRecycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.home_list_divier)!!)
        mRecycleView.addItemDecoration(decoration)

        mAdapter = generateAdapter()
        mRecycleView.adapter = mAdapter

        mRefreshLayout.setEnableRefresh(true)
        mRefreshLayout.setEnableLoadMore(true)

        mRefreshLayout.setOnRefreshListener(this)
        mRefreshLayout.setOnLoadMoreListener(this)


        initViewModel()

    }


    private fun initViewModel() {
        val types = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        mViewModel = ViewModelProvider(this).get(types[1] as Class<V>)

        mViewModel.getPageData().observe(this, Observer {
            submitPageList(it)
        })

    }


    fun submitPageList(pagedList: PagedList<T>) {
        mAdapter.submitList(pagedList)
    }

    abstract fun generateAdapter(): PagedListAdapter<T, RecyclerView.ViewHolder>


}