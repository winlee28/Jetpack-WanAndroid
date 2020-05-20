package com.win.lib_base.datasource

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList

/**
 * Create by liwen on 2020-05-20
 */
class MutablePageKeyedDataSource<Value> : PageKeyedDataSource<Int, Value>() {


    val data = mutableListOf<Value>()

    fun buildNewPageList(config: PagedList.Config): PagedList<Value> {

        return PagedList.Builder<Int, Value>(this, config)
            .setFetchExecutor(ArchTaskExecutor.getIOThreadExecutor())
            .setNotifyExecutor(ArchTaskExecutor.getMainThreadExecutor())
            .build()
    }


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Value>
    ) {
        callback.onResult(data, null, null)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Value>) {
        callback.onResult(emptyList(), null)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Value>) {
        callback.onResult(emptyList(), null)
    }

}