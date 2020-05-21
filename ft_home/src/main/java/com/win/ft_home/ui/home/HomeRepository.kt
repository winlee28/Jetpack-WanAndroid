package com.win.ft_home.ui.home

import com.win.ft_home.api.RequestCenter
import com.win.ft_home.model.home.Banner
import com.win.ft_home.model.home.DataFeed
import com.win.lib_net.net.BaseRepository
import com.win.lib_net.net.RetrofitClient
import com.win.lib_net.model.NetResult

/**
 * Create by liwen on 2020-05-18
 */
class HomeRepository : BaseRepository() {

    suspend fun getBanner(): NetResult<List<Banner>> {
        return safeApiCall(call = { requestBanner() })
    }

    suspend fun getHomeList(count: Int): NetResult<DataFeed> {
        return safeApiCall(call = { requestHomeList(count) })
    }

    private suspend fun requestBanner() =
        executeResponse(RetrofitClient.getService(RequestCenter::class.java).getBanner())

    private suspend fun requestHomeList(count: Int) =
        executeResponse(RetrofitClient.getService(RequestCenter::class.java).getHomeList(count))


}