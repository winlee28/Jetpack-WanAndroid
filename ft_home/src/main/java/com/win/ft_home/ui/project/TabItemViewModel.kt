package com.win.ft_home.ui.project

import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.win.ft_home.model.project.ProjectItemSub
import com.win.lib_base.base.AbsListViewModel
import com.win.lib_base.utils.BaseContext
import com.win.lib_net.model.NetResult
import kotlinx.coroutines.launch
import java.util.*

/**
 * Create by liwen on 2020/5/26
 */
class TabItemViewModel(private val repository: ProjectRepository) :
    AbsListViewModel<ProjectItemSub>() {

    private var mId: Int = 0

    fun setId(id: Int) {
        mId = id
    }

    override fun createDataSource(): DataSource<Int, ProjectItemSub> {

        return object : PageKeyedDataSource<Int, ProjectItemSub>() {
            override fun loadInitial(
                params: LoadInitialParams<Int>,
                callback: LoadInitialCallback<Int, ProjectItemSub>
            ) {
                getTabPageData(1, callback)
            }

            override fun loadAfter(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, ProjectItemSub>
            ) {
                getTabPageData(params.key, callback)
            }

            override fun loadBefore(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, ProjectItemSub>
            ) {
                callback.onResult(Collections.emptyList(), null)
            }

        }
    }

    fun getTabPageData(
        count: Int,
        callback: PageKeyedDataSource.LoadCallback<Int, ProjectItemSub>
    ) {
        viewModelScope.launch {
            val data = repository.getTabItemPageData(count, mId)
            if (data is NetResult.Success) {
                callback.onResult(data.data.datas, count + 1)
            } else if (data is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    data.exception.msg,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun getTabPageData(
        count: Int,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, ProjectItemSub>
    ) {
        viewModelScope.launch {
            val data = repository.getTabItemPageData(count, mId)
            if (data is NetResult.Success) {
                callback.onResult(data.data.datas, 0, 2)
            } else if (data is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    data.exception.msg,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}