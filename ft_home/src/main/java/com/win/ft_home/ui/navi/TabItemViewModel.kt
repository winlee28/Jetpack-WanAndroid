package com.win.ft_home.ui.navi

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.win.ft_home.model.navigation.NavigationItemSub
import com.win.lib_base.base.AbsListViewModel
import com.win.lib_net.model.NetResult
import kotlinx.coroutines.launch
import java.util.*

/**
 * Create by liwen on 2020/5/26
 */
class TabItemViewModel(private val repository: NavigationRepository) :
    AbsListViewModel<NavigationItemSub>() {

    private var mId: Int = 0

    fun setId(id: Int) {
        mId = id
    }

    override fun createDataSource(): DataSource<Int, NavigationItemSub> {
        Log.e("liwen","=====>>>>>>>>>")
        return object : PageKeyedDataSource<Int, NavigationItemSub>() {
            override fun loadInitial(
                params: LoadInitialParams<Int>,
                callback: LoadInitialCallback<Int, NavigationItemSub>
            ) {
                getTabPageData(0, callback)
            }

            override fun loadAfter(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, NavigationItemSub>
            ) {
                getTabPageData(params.key, callback)
            }

            override fun loadBefore(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, NavigationItemSub>
            ) {
                callback.onResult(Collections.emptyList(), null)
            }

        }
    }

    fun getTabPageData(
        count: Int,
        callback: PageKeyedDataSource.LoadCallback<Int, NavigationItemSub>
    ) {
        viewModelScope.launch {
            val data = repository.getTabItemPageData(count, mId)
            if (data is NetResult.Success) {
                callback.onResult(data.data.datas, count + 1)
            } else if (data is NetResult.Error) {
                // Toast.makeText(, "", Toast.LENGTH_SHORT).show();
            }
        }
    }

    fun getTabPageData(
        count: Int,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, NavigationItemSub>
    ) {
        viewModelScope.launch {
            val data = repository.getTabItemPageData(count, mId)
            if (data is NetResult.Success) {
                callback.onResult(data.data.datas, 0, 1)
            } else if (data is NetResult.Error) {
                //Toast.makeText(, "", Toast.LENGTH_SHORT).show();
            }
        }
    }

}