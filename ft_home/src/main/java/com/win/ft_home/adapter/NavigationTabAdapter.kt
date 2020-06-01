package com.win.ft_home.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.win.ft_home.R
import com.win.ft_home.`interface`.NavigationTabItemSelectedListener
import com.win.ft_home.databinding.NavigationTabItemBinding
import com.win.ft_home.model.navigation.NavigationItem
import java.nio.file.attribute.PosixFileAttributeView

/**
 * Create by liwen on 2020/5/29
 */
class NavigationTabAdapter(context: Context, data: MutableList<NavigationItem>) :
    RecyclerView.Adapter<NavigationTabAdapter.ViewHolder>() {

    private val mContext = context
    private val inflater = LayoutInflater.from(mContext)
    private val mData = data

    private lateinit var mListener: NavigationTabItemSelectedListener

    fun setItemSelectedListener(listener: NavigationTabItemSelectedListener) {
        this.mListener = listener
    }

    private var isSelectedPosition = 0

    fun setItemPositionSelected(position: Int) {
        isSelectedPosition = position
    }

    fun getItemPositionSelected(): Int {
        return isSelectedPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<NavigationTabItemBinding>(
            inflater,
            R.layout.navigation_tab_item,
            parent,
            false
        )

        return ViewHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(mData[position])
    }


    inner class ViewHolder(
        itemView: View,
        binding: NavigationTabItemBinding
    ) : RecyclerView.ViewHolder(itemView) {

        private var mBinding = binding

        fun setData(
            item: NavigationItem
        ) {

            mBinding.nameTxt = item.name
            if (adapterPosition == getItemPositionSelected()) {
                mBinding.name.isSelected = true
                mBinding.name.typeface = Typeface.DEFAULT_BOLD
            } else {
                mBinding.name.isSelected = false
                mBinding.name.typeface = Typeface.DEFAULT
            }

            mBinding.executePendingBindings() //防止 notifyDataSetChanged 的时候页面闪烁 重要！！！

            mBinding.name.setOnClickListener {
                mListener.onItemSelected(item, adapterPosition)
            }
        }
    }


}