package com.win.ft_home.ui.navi

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.win.ft_home.model.navigation.NavigationItem
import com.win.lib_base.utils.BaseContext
import com.win.lib_net.model.NetResult
import kotlinx.coroutines.launch

class NavigationViewModel(private val navigationRepo: NavigationRepository) : ViewModel() {


    private val navigationLiveData = MutableLiveData<MutableList<NavigationItem>>()

    fun getNavigationData(): MutableLiveData<MutableList<NavigationItem>> {

        viewModelScope.launch {

            val data = navigationRepo.getNavigationData()
            if (data is NetResult.Success) {

                navigationLiveData.postValue(data.data)

            } else if (data is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    data.exception.msg,
                    Toast.LENGTH_SHORT
                ).show();
            }

        }

        return navigationLiveData
    }


}