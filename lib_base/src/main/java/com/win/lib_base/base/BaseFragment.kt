package com.win.lib_base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass


/**
 * Create by liwen on 2020-05-18
 */
abstract class BaseFragment<T : ViewModel, M : ViewDataBinding> : Fragment() {

    lateinit var mViewModel: T
    lateinit var mViewBinding: M

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mViewBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)

        return mViewBinding.root
    }

    abstract fun getLayoutResId(): Int

    abstract fun initData()

    abstract fun initView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initData()
        initView()
    }

    private fun initViewModel() {
//        val types = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
//        mViewModel = ViewModelProvider(this).get(types[0] as Class<T>)

        val clazz =
            this.javaClass.kotlin.supertypes[0].arguments[0].type!!.classifier!! as KClass<T>
        mViewModel = getViewModel<T>(clazz) //koin 注入

    }


}