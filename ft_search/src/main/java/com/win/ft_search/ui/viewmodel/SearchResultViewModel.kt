package com.win.ft_search.ui.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.win.ft_search.ui.repository.SearchRepository
import com.win.lib_base.base.AbsListViewModel
import com.win.lib_base.model.DatasBean
import com.win.lib_net.model.NetResult
import kotlinx.coroutines.launch
import java.util.*


/**
 * Create by liwen on 2020/6/1
 */
class SearchResultViewModel(private val repo: SearchRepository) :
    AbsListViewModel<DatasBean>() {

    private var mHotKey: String = ""

    fun setHotKey(hotKey: String) {
        mHotKey = hotKey
    }

    private fun getHotKey(): String {
        return mHotKey
    }

    override fun createDataSource(): DataSource<Int, DatasBean> {

        return object : PageKeyedDataSource<Int, DatasBean>() {
            override fun loadInitial(
                params: LoadInitialParams<Int>,
                callback: LoadInitialCallback<Int, DatasBean>
            ) {
                loadData(0, callback)
            }

            override fun loadAfter(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, DatasBean>
            ) {
                loadDataAfter(params.key, callback)
            }

            override fun loadBefore(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, DatasBean>
            ) {
                callback.onResult(Collections.emptyList(), null)
            }

        }
    }

    private fun loadData(
        page: Int,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, DatasBean>
    ) {

        viewModelScope.launch {
            val search = repo.search(page, getHotKey())
            if (search is NetResult.Success) {
                callback.onResult(search.data.datas, null, 1)
            } else if (search is NetResult.Error) {
//                Toast.makeText(, "", Toast.LENGTH_SHORT).show();
            }
        }
    }


    fun loadDataAfter(
        key: Int,
        callback: PageKeyedDataSource.LoadCallback<Int, DatasBean>
    ) {

        viewModelScope.launch {

            val search = repo.search(key, getHotKey())
            if (search is NetResult.Success) {
                callback.onResult(search.data.datas, key + 1)
            } else if (search is NetResult.Error) {
//                Toast.makeText(, "", Toast.LENGTH_SHORT).show();
            }
        }
    }

}