package com.win.ft_home.api

import com.win.ft_home.model.home.Banner
import com.win.ft_home.model.home.DataFeed
import com.win.ft_home.model.navigation.NavigationItem
import com.win.ft_home.model.project.ProjectPageItem
import com.win.ft_home.model.project.ProjectTabItem
import com.win.ft_home.model.tree.TreeData
import com.win.lib_net.model.BaseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Create by liwen on 2020-05-18
 */
interface RequestCenter {

    @GET("/banner/json")
    suspend fun getBanner(): BaseModel<MutableList<Banner>>

    @GET("/article/list/{count}/json")
    suspend fun getHomeList(@Path("count") count: Int): BaseModel<DataFeed>

    @GET("/tree/json")
    suspend fun getTreeList(): BaseModel<MutableList<TreeData>>

    @GET("/project/tree/json")
    suspend fun getTabData(): BaseModel<MutableList<ProjectTabItem>>

    @GET("/project/list/{count}/json")
    suspend fun getTabItemPageData(
        @Path("count") count: Int,
        @Query("cid") cid: Int
    ): BaseModel<ProjectPageItem>


    @GET("/navi/json")
    suspend fun getNavigationData(): BaseModel<MutableList<NavigationItem>>

}