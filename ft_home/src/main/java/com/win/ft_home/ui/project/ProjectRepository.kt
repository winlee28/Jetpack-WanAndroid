package com.win.ft_home.ui.project

import com.win.ft_home.api.RequestCenter
import com.win.ft_home.model.project.ProjectPageItem
import com.win.ft_home.model.project.ProjectTabItem
import com.win.lib_net.model.NetResult
import com.win.lib_net.net.BaseRepository
import com.win.lib_net.net.RetrofitClient

/**
 * Create by liwen on 2020/5/26
 */
class ProjectRepository : BaseRepository() {


    suspend fun getTabData(): NetResult<MutableList<ProjectTabItem>> {
        return safeApiCall(call = { requestTabData() })
    }

    private suspend fun requestTabData() =
        executeResponse(RetrofitClient.getService(RequestCenter::class.java).getTabData())


    suspend fun getTabItemPageData(count: Int, id: Int): NetResult<ProjectPageItem> {
        return safeApiCall(call = { requestTabItemPageData(count, id) })
    }

    private suspend fun requestTabItemPageData(count: Int, id: Int) =
        executeResponse(
            RetrofitClient.getService(RequestCenter::class.java).getTabItemPageData(count, id)
        )
}