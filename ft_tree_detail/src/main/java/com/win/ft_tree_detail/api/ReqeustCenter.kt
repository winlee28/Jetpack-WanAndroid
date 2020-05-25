package com.win.ft_tree_detail.api

import com.win.ft_tree_detail.model.TreeDetailModel
import com.win.lib_net.model.BaseModel
import retrofit2.http.*

/**
 * Create by liwen on 2020-05-21
 */
interface ReqeustCenter {

    @GET(value = "/article/list/{count}/json")
    suspend fun getTreeDetailList(
        @Path("count") count: Int,
        @Query("cid") cid: Int
    ): BaseModel<TreeDetailModel>

    @POST(value = "/sdk/hackness/selfie_hack_detect")
    suspend fun testLinkface(
        @Body id: String,
        @Body secret: String,
        @Body es:String,
        @Body file: ByteArray
    ): BaseModel<Any>


}