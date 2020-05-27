package com.win.ft_home.ui.navi

import com.win.ft_home.api.RequestCenter
import com.win.ft_home.model.navigation.NavigationItem
import com.win.ft_home.model.navigation.NavigationModel
import com.win.lib_net.model.NetResult
import com.win.lib_net.net.BaseRepository
import com.win.lib_net.net.RetrofitClient

/**
 * Create by liwen on 2020/5/26
 */
class NavigationRepository : BaseRepository() {


    suspend fun getTabData(): NetResult<MutableList<NavigationModel>> {
        return safeApiCall(call = { requestTabData() })
    }

    private suspend fun requestTabData() =
        executeResponse(RetrofitClient.getService(RequestCenter::class.java).getTabData())


    suspend fun getTabItemPageData(count: Int, id: Int): NetResult<NavigationItem> {
        return safeApiCall(call = { requestTabItemPageData(count, id) })
    }

    private suspend fun requestTabItemPageData(count: Int, id: Int) =
        executeResponse(
            RetrofitClient.getService(RequestCenter::class.java).getTabItemPageData(count, id)
        )
}