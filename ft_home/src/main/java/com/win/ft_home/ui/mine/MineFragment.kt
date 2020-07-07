package com.win.ft_home.ui.mine

import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.win.ft_home.R
import com.win.ft_home.databinding.FragmentMineBinding
import com.win.lib_base.base.BaseFragment
import com.win.lib_base.service.aboutus.wrap.AboutUsServiceImplWrap
import com.win.lib_base.service.login.warp.LoginServiceImplWrap
import com.win.lib_image_loader.app.ImageLoaderManager

class MineFragment : BaseFragment<MineViewModel, FragmentMineBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_mine

    override fun initData() {
        mViewBinding.user = LoginServiceImplWrap.getUserInfo()
    }

    override fun initView() {

        mViewBinding.name.setOnClickListener {
            if (!LoginServiceImplWrap.isLogin()) {
                LoginServiceImplWrap.start(requireContext())
            }
        }


        mViewBinding.aboutUs.setOnClickListener {
            AboutUsServiceImplWrap.start(requireContext())
        }

        LoginServiceImplWrap.getLiveData().observe(this, Observer { user ->
            mViewBinding.user = user
        })

        mViewBinding.exit.setOnClickListener {
            AlertDialog.Builder((requireContext())).setTitle("提示")
                .setMessage("确定退出吗？")
                .setPositiveButton(
                    "确定"
                ) { dialog, _ ->
                    dialog.dismiss()
                    LoginServiceImplWrap.removeUserInfo()
                    initData()
                }.setNegativeButton(
                    "取消"
                ) { dialog, _ ->
                    dialog.dismiss()
                }.show()
        }
    }


}