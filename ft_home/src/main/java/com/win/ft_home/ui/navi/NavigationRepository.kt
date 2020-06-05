package com.win.ft_home.ui.navi

import com.win.ft_home.api.RequestCenter
import com.win.ft_home.model.navigation.NavigationItem
import com.win.lib_net.model.NetResult
import com.win.lib_net.net.BaseRepository
import com.win.lib_net.net.RetrofitClient

/**
 * Create by liwen on 2020/5/28
 */
class NavigationRepository(private val service: RetrofitClient) : BaseRepository() {

    suspend fun getNavigationData(): NetResult<MutableList<NavigationItem>> {
        return callRequest(call = { requestNavigationData() })
    }

    private suspend fun requestNavigationData(): NetResult<MutableList<NavigationItem>> =
        handleResponse(service.create(RequestCenter::class.java).getNavigationData())

}