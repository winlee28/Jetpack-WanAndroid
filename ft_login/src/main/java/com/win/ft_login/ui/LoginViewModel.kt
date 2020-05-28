package com.win.ft_login.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.win.lib_base.model.User
import com.win.lib_net.model.NetResult
import kotlinx.coroutines.launch

/**
 * Create by liwen on 2020/5/27
 */
class LoginViewModel(private val loginRepo: LoginRepository) : ViewModel() {


    private val loginLiveData = MutableLiveData<User>()

    fun login(username: String, password: String): MutableLiveData<User> {
        viewModelScope.launch {
            val user = loginRepo.login(username, password)
            if (user is NetResult.Success) {
                loginLiveData.postValue(user.data)
            } else if (user is NetResult.Error) {
//                Toast.makeText(, "", Toast.LENGTH_SHORT).show();
            }

        }

        return loginLiveData
    }

    fun register(username: String, password: String, surePassword: String): MutableLiveData<User> {
        viewModelScope.launch {
            val user = loginRepo.register(username, password,surePassword)
            if (user is NetResult.Success) {
                loginLiveData.postValue(user.data)
            } else if (user is NetResult.Error) {
//                Toast.makeText(, "", Toast.LENGTH_SHORT).show();
            }

        }

        return loginLiveData
    }

}