package com.win.ft_login.ui

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import com.win.ft_login.R
import com.win.ft_login.UserManager
import com.win.ft_login.databinding.ActivityLoginBinding
import com.win.ft_login.model.LoginLayoutBean
import com.win.lib_base.model.User
import com.win.lib_base.base.BaseActivity

/**
 * Create by liwen on 2020/5/27
 */
class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    private lateinit var mData: LoginLayoutBean

    override fun getLayoutResId(): Int = R.layout.activity_login

    override fun initData() {
        mData = LoginLayoutBean()
        mViewBinding.bean = mData
    }

    override fun initView() {

        mViewBinding.close.setOnClickListener {
            finish()
        }

        mViewBinding.featureName.setOnClickListener {
            mData.isLogin = !mData.isLogin
            initEditText()
        }

        mViewBinding.btnLogin.setOnClickListener {

            if (mData.isLogin) { //登录
                loginAction()
            } else { //注册
                registerAction()
            }
        }

    }

    private fun loginAction() {
        mViewModel.login(getUserName(), getPassword()).observe(this, Observer { user ->
            saveUserInfo(user)
        })
    }

    private fun saveUserInfo(user: User?) {
        UserManager.saveUser(user)
        finish()
    }

    private fun registerAction() {
        mViewModel.register(getUserName(), getPassword(), getSurePassword())
            .observe(this, Observer { user ->
                saveUserInfo(user)
            })
    }

    private fun getUserName(): String {
        return mViewBinding.userName.text.toString().trim()
    }

    private fun getPassword(): String {
        return mViewBinding.password.text.toString().trim()
    }

    private fun getSurePassword(): String {
        return mViewBinding.surePassword.text.toString().trim()
    }

    private fun initEditText() {
        mViewBinding.userName.text = null
        mViewBinding.password.text = null
        mViewBinding.surePassword.text = null
    }


}