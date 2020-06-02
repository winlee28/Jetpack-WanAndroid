package com.win.ft_search.ui.fragment

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.win.ft_search.R
import com.win.ft_search.adapter.SearchHotKeyAdapter
import com.win.ft_search.databinding.HotkeyPageBinding
import com.win.ft_search.model.HotKeyModel
import com.win.ft_search.ui.activity.SearchActivity
import com.win.ft_search.ui.viewmodel.HotKeyViewModel
import com.win.lib_base.adapter.BaseRecyclerViewAdapter
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

            adapter.setOnItemClickListener(object :
                BaseRecyclerViewAdapter.OnItemClickListener<HotKeyModel> {
                override fun onItemClick(item: HotKeyModel, position: Int) {
                    (requireActivity() as SearchActivity).setHotKeyInputSearch(item.name)
                }
            })
        })

    }

    override fun initView() {
        mRecycleView = mViewBinding.recycleView
        mRecycleView.layoutManager =
            GridLayoutManager(requireContext(), 2)

    }

}