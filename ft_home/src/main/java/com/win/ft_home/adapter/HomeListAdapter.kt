package com.win.ft_home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.win.ft_home.databinding.LayoutHomeListBinding
import com.win.lib_base.service.webview.warp.WebViewWarpService
import com.win.lib_base.model.DatasBean

/**
 * Create by liwen on 2020-05-19
 */
class HomeListAdapter(context: Context) :

    PagedListAdapter<DatasBean, HomeListAdapter.ViewHolder>(object :

        DiffUtil.ItemCallback<DatasBean>() {
        override fun areContentsTheSame(
            oldItem: DatasBean,
            newItem: DatasBean
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(
            oldItem: DatasBean,
            newItem: DatasBean
        ): Boolean {
            return oldItem.id == newItem.id
        }


    }) {


    private val inflater = LayoutInflater.from(context)
    private val mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = LayoutHomeListBinding.inflate(inflater, parent, false)

        return ViewHolder(binding.root, binding, mContext)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setData(getItem(position)!!)

    }


    class ViewHolder(
        itemView: View,
        binding: LayoutHomeListBinding,
        context: Context
    ) : RecyclerView.ViewHolder(itemView) {

        private var mBinding = binding
        private var mContext = context

        fun setData(datasBean: DatasBean) {
            mBinding.feed = datasBean

            mBinding.parentItem.setOnClickListener {
                WebViewWarpService.instance.start(mContext, datasBean.title, datasBean.link)
            }
        }

    }
}