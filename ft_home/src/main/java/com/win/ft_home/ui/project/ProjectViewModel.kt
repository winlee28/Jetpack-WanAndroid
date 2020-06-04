package com.win.ft_home.ui.project

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.win.ft_home.model.project.ProjectTabItem
import com.win.lib_base.utils.BaseContext
import com.win.lib_net.model.NetResult
import kotlinx.coroutines.launch

class ProjectViewModel(private val navigationRepo: ProjectRepository) : ViewModel() {

    private val tabDataLiveData = MutableLiveData<MutableList<ProjectTabItem>>()

    fun getTabData(): MutableLiveData<MutableList<ProjectTabItem>> {
        viewModelScope.launch {
            val tabData = navigationRepo.getTabData()
            if (tabData is NetResult.Success) {
                tabDataLiveData.postValue(tabData.data)
            } else if (tabData is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    tabData.exception.msg,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return tabDataLiveData
    }

}