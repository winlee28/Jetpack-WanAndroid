package com.win.ft_home.ui.home

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PageKeyedDataSource
import com.win.ft_home.model.Banner
import com.win.ft_home.model.DatasBean
import com.win.lib_base.App
import com.win.lib_net.model.Result
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val bannerLiveData = MutableLiveData<List<Banner>>()

    fun getBannerLiveData(): MutableLiveData<List<Banner>> {
        return bannerLiveData
    }

    private val homeRepository = HomeRepository()

    fun getBanner() {

        viewModelScope.launch {
            val banner = homeRepository.getBanner()
            if (banner is Result.Success) {
                bannerLiveData.postValue(banner.dataa)
            } else if (banner is Result.Error) {
                Toast.makeText(App.CONTEXT, banner.exception.msg, Toast.LENGTH_LONG).show()
            }
        }

    }

    fun getHomeList(
        count: Int,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, DatasBean>
    ) {

        viewModelScope.launch {
            val homeFeed = homeRepository.getHomeList(count)
            if (homeFeed is Result.Success) {
                callback.onResult(homeFeed.dataa.datas, null, 1)
            } else if (homeFeed is Result.Error) {
                Toast.makeText(App.CONTEXT, homeFeed.exception.msg, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun getHomeList(
        count: Int,
        key: Int,
        callback: PageKeyedDataSource.LoadCallback<Int, DatasBean>
    ) {



        viewModelScope.launch {
            val homeFeed = homeRepository.getHomeList(count)
            if (homeFeed is Result.Success) {
                callback.onResult(homeFeed.dataa.datas, key)
            } else if (homeFeed is Result.Error) {
                Toast.makeText(App.CONTEXT, homeFeed.exception.msg, Toast.LENGTH_LONG).show()
            }
        }
    }
}