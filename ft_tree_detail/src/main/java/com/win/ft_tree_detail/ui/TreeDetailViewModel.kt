package com.win.ft_tree_detail.ui

//import com.win.lib_base.App
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.win.ft_tree_detail.model.TreeDetailItem
import com.win.lib_base.base.AbsListViewModel
import com.win.lib_base.model.DatasBean
import com.win.lib_net.model.NetResult
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import java.util.*

/**
 * Create by liwen on 2020-05-21
 */
class TreeDetailViewModel(private val dataRepos: TreeDetailRepository) :
    AbsListViewModel<DatasBean>() {

    private var mCid: Int = 0

    fun setCid(cid: Int) {
        mCid = cid
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
                loadData(params.key, callback)
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
        key: Int,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, DatasBean>
    ) {

        viewModelScope.launch {
            val result = dataRepos.getTreeDetailList(key, mCid)
            if (result is NetResult.Success) {
                callback.onResult(result.data.datas, null, 1)
            } else if (result is NetResult.Error) {
//                Toast.makeText(App.CONTEXT, result.exception.msg, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun loadData(
        key: Int,
        callback: PageKeyedDataSource.LoadCallback<Int, DatasBean>
    ) {
        viewModelScope.launch {
            val result = dataRepos.getTreeDetailList(key, mCid)
            if (result is NetResult.Success) {
                callback.onResult(result.data.datas, key + 1)
            } else if (result is NetResult.Error) {
//                Toast.makeText(App.CONTEXT, result.exception.msg, Toast.LENGTH_SHORT).show()
            }
        }

    }


}