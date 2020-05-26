package com.win.lib_base.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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
import com.win.lib_base.utils.StatusBarKt
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

/**
 * Create by liwen on 2020-05-21
 *
 * 作为列表布局的父类
 */
abstract class AbsListActivity<T, V : AbsListViewModel<T>> : AppCompatActivity(),
    OnLoadMoreListener,
    OnRefreshListener {

    lateinit var mAdapter: PagedListAdapter<T, RecyclerView.ViewHolder>

    lateinit var mViewModel: V

    private lateinit var mRefreshLayout: SmartRefreshLayout
    private lateinit var mRecycleView: RecyclerView

    lateinit var mBinding: ActivityAbsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        StatusBarKt.fitSystemBar(this)

        mBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_abs)

        initActionBar()

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

    abstract fun initActionBar()


    private fun initViewModel() {

        //java
//        val types = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
//        val clazz = types[1] as Class<V>
//        mViewModel = ViewModelProvider(this).get(clazz)


        //kotlin + koin
        val clazz =
            this.javaClass.kotlin.supertypes[0].arguments[1].type!!.classifier!! as KClass<V>
        mViewModel = getViewModel<V>(clazz)


        mViewModel.getPageData().observe(this, Observer {
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