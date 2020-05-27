package com.win.lib_base.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * Create by liwen on 2020-05-22
 */
abstract class AbsListViewModel<T> : ViewModel() {


    private var dataSource: DataSource<Int, T>? = null
    private var pageData: LiveData<PagedList<T>>

    fun getPageData(): LiveData<PagedList<T>> {
        return pageData
    }

    fun getDataSource(): DataSource<Int, T>? {
        return dataSource
    }

    init {

        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .build()

        val factory = object : DataSource.Factory<Int, T>() {
            override fun create(): DataSource<Int, T> {
                //使用 || 或者会导致刷新的时候 create会被不停的调用
                if (dataSource == null || dataSource?.isInvalid != false) {
                    dataSource = createDataSource()
                }
                return dataSource!!
            }

        }

        pageData = LivePagedListBuilder<Int, T>(factory, config).build()


    }

    abstract fun createDataSource(): DataSource<Int, T>

}