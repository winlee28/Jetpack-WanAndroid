package com.win.ft_home.ui.navi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.win.ft_home.model.navigation.NavigationModel
import com.win.lib_net.model.NetResult
import kotlinx.coroutines.launch

class NavigationViewModel(private val navigationRepo: NavigationRepository) : ViewModel() {

    private val tabDataLiveData = MutableLiveData<MutableList<NavigationModel>>()

    fun getTabData(): MutableLiveData<MutableList<NavigationModel>> {
        viewModelScope.launch {
            val tabData = navigationRepo.getTabData()
            if (tabData is NetResult.Success) {
                tabDataLiveData.postValue(tabData.data)
            } else if (tabData is NetResult.Error) {
//                Toast.makeText(, "", Toast.LENGTH_SHORT).show();
            }
        }
        return tabDataLiveData
    }

}