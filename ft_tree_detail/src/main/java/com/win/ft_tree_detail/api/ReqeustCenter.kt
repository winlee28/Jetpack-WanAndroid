package com.win.ft_tree_detail.api

import com.win.ft_tree_detail.model.TreeDetailModel
import com.win.lib_net.model.BaseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Create by liwen on 2020-05-21
 */
interface ReqeustCenter {

    @GET(value = "/article/list/{count}/json")
    suspend fun getTreeDetailList(@Path("count") count: Int, @Query("cid") cid: Int): BaseModel<TreeDetailModel>
}