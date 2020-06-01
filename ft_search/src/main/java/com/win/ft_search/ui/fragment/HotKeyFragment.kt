package com.win.ft_search.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.win.ft_search.R
import com.win.ft_search.adapter.SearchHotKeyAdapter
import com.win.ft_search.databinding.HotkeyPageBinding
import com.win.ft_search.ui.viewmodel.HotKeyViewModel
import com.win.lib_base.base.BaseFragment

/**
 * Create by liwen on 2020/6/1
 */
class HotKeyFragment : BaseFragment<HotKeyViewModel, HotkeyPageBinding>() {


    private lateinit var mRecycleView: RecyclerView

    override fun getLayoutResId(): Int = R.layout.hotkey_page

    companion object {
        fun newInstance(): HotKeyFragment {
            return HotKeyFragment()
        }
    }

    override fun initData() {
        mViewModel.getHotKey().observe(this, Observer {
            val adapter = SearchHotKeyAdapter(requireContext())
            adapter.dataList = it
            mRecycleView.adapter = adapter
        })

    }

    override fun initView() {
        mRecycleView = mViewBinding.recycleView
        mRecycleView.layoutManager =
            GridLayoutManager(requireContext(), 2)

    }

}