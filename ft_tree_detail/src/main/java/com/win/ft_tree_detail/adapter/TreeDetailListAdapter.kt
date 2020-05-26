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
import com.win.lib_base.model.DatasBean
import com.win.lib_webview.WebViewActivity

/**
 * Create by liwen on 2020-05-22
 */
class TreeDetailListAdapter(context: Context) :
    PagedListAdapter<DatasBean, TreeDetailListAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<DatasBean>() {
            override fun areItemsTheSame(
                oldItem: DatasBean,
                newItem: DatasBean
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DatasBean,
                newItem: DatasBean
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
        holder.setData(getItem(position)!!)
    }

    inner class ViewHolder(
        itemView: View,
        binding: TreeDetailListItemBinding
    ) : RecyclerView.ViewHolder(itemView) {

        private val mBinding = binding

        fun setData(item: DatasBean) {
            mBinding.item = item

            mBinding.itemParent.setOnClickListener {
                WebViewActivity.start(mContext, item.title, item.link)
            }

        }

    }
}