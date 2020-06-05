package com.win.ft_search.ui.repository

import com.win.ft_search.api.RequestCenter
import com.win.ft_search.model.HotKeyModel
import com.win.ft_search.model.SearchResultModel
import com.win.lib_net.model.NetResult
import com.win.lib_net.net.BaseRepository
import com.win.lib_net.net.RetrofitClient

/**
 * Create by liwen on 2020/6/1
 */
class SearchRepository(private val service: RetrofitClient) : BaseRepository() {

    suspend fun getHotKey(): NetResult<MutableList<HotKeyModel>> {
        return callRequest(call = { requestHotKey() })
    }

    suspend fun search(page: Int, key: String): NetResult<SearchResultModel> {
        return callRequest(call = { requestSearch(page, key) })
    }

    private suspend fun requestHotKey() =
        handleResponse(service.create(RequestCenter::class.java).getHotKey())


    private suspend fun requestSearch(page: Int, key: String) =
        handleResponse(service.create(RequestCenter::class.java).search(page, key))


}