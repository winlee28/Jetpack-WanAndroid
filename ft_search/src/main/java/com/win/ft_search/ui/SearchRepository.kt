package com.win.ft_search.ui

import com.win.ft_search.api.RequestCenter
import com.win.ft_search.model.HotKeyModel
import com.win.ft_search.model.SearchResultModel
import com.win.lib_net.model.NetResult
import com.win.lib_net.net.BaseRepository
import com.win.lib_net.net.RetrofitClient

/**
 * Create by liwen on 2020/6/1
 */
class SearchRepository : BaseRepository() {

    suspend fun getHotKey(): NetResult<MutableList<HotKeyModel>> {
        return safeApiCall(call = { requestHotKey() })
    }

    suspend fun search(page: Int, key: String): NetResult<SearchResultModel> {
        return safeApiCall(call = { requestSearch(page, key) })
    }

    private suspend fun requestHotKey() =
        executeResponse(RetrofitClient.getService(RequestCenter::class.java).getHotKey())


    private suspend fun requestSearch(page: Int, key: String) =
        executeResponse(RetrofitClient.getService(RequestCenter::class.java).search(page, key))


}