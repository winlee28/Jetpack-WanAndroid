package com.win.ft_search.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.win.ft_search.R
import com.win.ft_search.databinding.SearchResultItemBinding
import com.win.lib_base.model.DatasBean

/**
 * Create by liwen on 2020/6/1
 */
class SearchResultAdapter(context: Context) :
    PagedListAdapter<DatasBean, SearchResultAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<DatasBean>() {
            override fun areItemsTheSame(oldItem: DatasBean, newItem: DatasBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DatasBean, newItem: DatasBean): Boolean {
                return oldItem == newItem
            }

        }
    ) {

    private val mContext = context
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = DataBindingUtil.inflate<SearchResultItemBinding>(
            inflater,
            R.layout.search_result_item,
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
        binding: SearchResultItemBinding
    ) : RecyclerView.ViewHolder(itemView) {

        private val mBinding = binding

        fun setData(item: DatasBean?) {
            mBinding.data = item
        }

    }
}