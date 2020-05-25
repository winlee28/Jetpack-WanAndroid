package com.win.ft_home.ui.home

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.win.ft_home.R
import com.win.ft_home.adapter.HomeListAdapter
import com.win.ft_home.adapter.MyAdapter
import com.win.ft_home.databinding.FragmentHomeBinding
import com.win.ft_home.model.home.Banner
import com.win.ft_home.model.home.DatasBean
import com.win.lib_base.base.BaseFragment
import com.win.lib_base.datasource.MutablePageKeyedDataSource
import java.util.*


class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(), OnRefreshListener,
    OnLoadMoreListener {

    private lateinit var dataSource: PageKeyedDataSource<Int, DatasBean>
    private lateinit var dataSourceFactory: DataSource.Factory<Int, DatasBean>

    private lateinit var listAdapter: HomeListAdapter

    private lateinit var mRefreshLayout: SmartRefreshLayout
    private lateinit var mRecyclerView: RecyclerView

    override fun getLayoutResId(): Int = R.layout.fragment_home

    override fun initView() {

        mRecyclerView = mViewBinding.recyclerView

        mRefreshLayout = mViewBinding.refreshLayout


        mRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        val decoration = DividerItemDecoration(activity, LinearLayoutManager.VERTICAL)
        decoration.setDrawable(ContextCompat.getDrawable(requireActivity()!!, R.drawable.home_list_divier)!!)
        mRecyclerView.addItemDecoration(decoration)

        mRecyclerView.animation = null

        listAdapter = HomeListAdapter(requireActivity()!!)

        mRecyclerView.adapter = listAdapter

        mRefreshLayout.setEnableLoadMore(true)
        mRefreshLayout.setEnableRefresh(true)

        mRefreshLayout.setOnLoadMoreListener(this)
        mRefreshLayout.setOnRefreshListener(this)


        val conifg = PagedList.Config.Builder()
            .setInitialLoadSizeHint(12)//首次加载大小
            .setPageSize(10)//分页大小
            .setPrefetchDistance(10)
            .build()


        dataSourceFactory = object : DataSource.Factory<Int, DatasBean>() {

            override fun create(): DataSource<Int, DatasBean> {
                dataSource = object : PageKeyedDataSource<Int, DatasBean>() {

                    override fun loadInitial(
                        params: LoadInitialParams<Int>,
                        callback: LoadInitialCallback<Int, DatasBean>
                    ) {
                        mViewModel.getHomeList(0, callback)
                    }

                    override fun loadAfter(
                        params: LoadParams<Int>,
                        callback: LoadCallback<Int, DatasBean>
                    ) {
                        Log.e("liwen","=====loadAfter=====")
                        mViewModel.getHomeList(params.key, params.key + 1, callback)
                    }

                    override fun loadBefore(
                        params: LoadParams<Int>,
                        callback: LoadCallback<Int, DatasBean>
                    ) {
                        callback.onResult(Collections.emptyList(), null)
                    }


                }

                return dataSource
            }

        }


        val pagedList =
            LivePagedListBuilder(dataSourceFactory, conifg).build()

        pagedList.observe(this,
            Observer<PagedList<DatasBean>>
            { pageList ->
                listAdapter.submitList(pageList!!)
            })

    }

    override fun initData() {

        mViewModel.apply { getBanner() }

        mViewModel.getBannerLiveData()
            .observe(viewLifecycleOwner,
                Observer<List<Banner>> {
                    mViewBinding.banner.adapter = MyAdapter(it)
                })

    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {

        Log.e("liwen","=====onLoadMore=====")

        //当加载更多的时候返回了空数据 需要手动接管数据加载否则paging框架将不会继续加载数据

        val currentList = listAdapter.currentList

        if (currentList == null || currentList.size <= 0) {
            return
        }

        val key = currentList.size / 20
        Log.e("liwen","=====onLoadMore===${key}==")
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
                        dataSource.buildNewPageList(listAdapter.currentList!!.config)

                    listAdapter.submitList(pageList)

                }
            })

        finishRefresh()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        Log.e("liwen", "onRefresh")
        dataSource.invalidate()
        finishRefresh()
    }


    fun finishRefresh() {

        val state = mRefreshLayout.state
        if (state.isOpening && state.isHeader) {
            mRefreshLayout.finishRefresh()
        } else if (state.isFooter && state.isOpening) {
            mRefreshLayout.finishLoadMore()
        }

    }

}