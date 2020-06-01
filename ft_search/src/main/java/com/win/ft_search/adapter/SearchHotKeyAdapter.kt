package com.win.ft_search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.win.ft_search.R
import com.win.ft_search.databinding.HotkeyItemBinding
import com.win.ft_search.model.HotKeyModel
import com.win.lib_base.adapter.BaseRecyclerViewAdapter

/**
 * Create by liwen on 2020/6/1
 */
class SearchHotKeyAdapter(context: Context) :
    BaseRecyclerViewAdapter<HotKeyModel, SearchHotKeyAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<HotkeyItemBinding>(
            LayoutInflater.from(mContext),
            R.layout.hotkey_item, parent, false
        )

        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.setData(dataList[position])
    }


    class ViewHolder(
        itemView: View,
        binding: HotkeyItemBinding
    ) : RecyclerView.ViewHolder(itemView) {

        private val mBinding = binding

        fun setData(hotKeyModel: HotKeyModel) {

            mBinding.hotkey.text = hotKeyModel.name
        }

    }

}