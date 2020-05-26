package com.win.ft_home.ui.home

//import com.win.lib_base.App
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PageKeyedDataSource
import com.win.ft_home.model.home.Banner
import com.win.lib_base.model.DatasBean
import com.win.lib_net.model.NetResult
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val bannerLiveData = MutableLiveData<List<Banner>>()

    fun getBannerLiveData(): MutableLiveData<List<Banner>> {
        return bannerLiveData
    }


    fun getBanner() {

        viewModelScope.launch {
            val banner = homeRepository.getBanner()
            if (banner is NetResult.Success) {
                bannerLiveData.postValue(banner.data)
            } else if (banner is NetResult.Error) {
//                Toast.makeText(App.CONTEXT, banner.exception.msg, Toast.LENGTH_LONG).show()
            }
        }

    }

    fun getHomeList(
        count: Int,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, DatasBean>
    ) {

        viewModelScope.launch {
            val homeFeed = homeRepository.getHomeList(count)
            if (homeFeed is NetResult.Success) {
                callback.onResult(homeFeed.data.datas, null, 1)
            } else if (homeFeed is NetResult.Error) {
//                Toast.makeText(App.CONTEXT, homeFeed.exception.msg, Toast.LENGTH_LONG).show()
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
            if (homeFeed is NetResult.Success) {
                callback.onResult(homeFeed.data.datas, key)
            } else if (homeFeed is NetResult.Error) {
//                Toast.makeText(App.CONTEXT, homeFeed.exception.msg, Toast.LENGTH_LONG).show()
            }
        }
    }
}