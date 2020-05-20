package com.win.ft_home.api

import com.win.ft_home.model.Banner
import com.win.ft_home.model.DataFeed
import com.win.ft_home.model.HomeFeed
import com.win.lib_net.model.BaseModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Create by liwen on 2020-05-18
 */
interface RequestCenter {

    @GET("/banner/json")
    suspend fun getBanner(): BaseModel<List<Banner>>


    @GET("/article/list/{count}/json")
    suspend fun getHomeList(@Path("count") count: Int): BaseModel<DataFeed>

}