package com.win.ft_tree_detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.win.ft_tree_detail.R
import com.win.ft_tree_detail.databinding.TreeDetailListItemBinding
import com.win.ft_tree_detail.model.TreeDetailItem

/**
 * Create by liwen on 2020-05-22
 */
class TreeDetailListAdapter(context: Context) :
    PagedListAdapter<TreeDetailItem, TreeDetailListAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<TreeDetailItem>() {
            override fun areItemsTheSame(
                oldItem: TreeDetailItem,
                newItem: TreeDetailItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: TreeDetailItem,
                newItem: TreeDetailItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }

    ) {

    private val mContext = context
    private val inflater = LayoutInflater.from(mContext)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<TreeDetailListItemBinding>(
            inflater,
            R.layout.tree_detail_list_item,
            parent,
            false
        )
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class ViewHolder(
        itemView: View,
        binding: TreeDetailListItemBinding
    ) : RecyclerView.ViewHolder(itemView) {

        private val mBinding = binding

        fun setData(item: TreeDetailItem?) {
            mBinding.treeDetailItem = item
        }

    }
}