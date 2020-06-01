package com.win.ft_search.ui

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.win.ft_search.model.HotKeyModel
import com.win.ft_search.model.SearchResultModel
import com.win.lib_net.model.NetResult
import kotlinx.coroutines.launch

/**
 * Create by liwen on 2020/6/1
 */
class SearchViewModel(private val repo: SearchRepository) : ViewModel()