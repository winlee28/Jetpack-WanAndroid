package com.win.ft_home.ui.home

import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.win.lib_base.base.AbsListViewModel
import com.win.lib_base.model.DatasBean
import com.win.lib_base.utils.BaseContext
import com.win.lib_net.model.NetResult
import kotlinx.coroutines.launch
import java.util.*

/**
 * Create by liwen on 2020/6/4
 */
class HomeListViewModel(private val repository: HomeRepository) : AbsListViewModel<DatasBean>() {

    override fun createDataSource(): DataSource<Int, DatasBean> {

        return object : PageKeyedDataSource<Int, DatasBean>() {

            override fun loadInitial(
                params: LoadInitialParams<Int>,
                callback: LoadInitialCallback<Int, DatasBean>
            ) {
                getHomeList(0, callback)
            }

            override fun loadAfter(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, DatasBean>
            ) {
                getHomeList(params.key, params.key + 1, callback)
            }

            override fun loadBefore(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, DatasBean>
            ) {
                callback.onResult(Collections.emptyList(), null)
            }


        }
    }


    fun getHomeList(
        count: Int,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, DatasBean>
    ) {

        viewModelScope.launch {
            val homeFeed = repository.getHomeList(count)
            if (homeFeed is NetResult.Success) {
                callback.onResult(homeFeed.data.datas, null, 1)
            } else if (homeFeed is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    homeFeed.exception.msg,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun getHomeList(
        count: Int,
        key: Int,
        callback: PageKeyedDataSource.LoadCallback<Int, DatasBean>
    ) {


        viewModelScope.launch {
            val homeFeed = repository.getHomeList(count)
            if (homeFeed is NetResult.Success) {
                callback.onResult(homeFeed.data.datas, key)
            } else if (homeFeed is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    homeFeed.exception.msg,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}