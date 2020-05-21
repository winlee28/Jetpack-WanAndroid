package com.win.ft_home.ui.tree

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.win.ft_home.model.tree.TreeData
import com.win.lib_base.App
import com.win.lib_net.model.NetResult
import kotlinx.coroutines.launch

class TreeViewModel : ViewModel() {


    private val treeDataLiveData = MutableLiveData<MutableList<TreeData>>()

    fun getTreeDataLiveData(): MutableLiveData<MutableList<TreeData>> {
        return treeDataLiveData
    }


    private val treeRepository = TreeRepository()

    fun getTreeList() {

        viewModelScope.launch {
            val treeData = treeRepository.getTreeList()

            if (treeData is NetResult.Success) {
                treeDataLiveData.postValue(treeData.data)
            } else if (treeData is NetResult.Error) {
                Toast.makeText(App.CONTEXT, treeData.exception.msg, Toast.LENGTH_LONG).show()
            }
        }
    }


}