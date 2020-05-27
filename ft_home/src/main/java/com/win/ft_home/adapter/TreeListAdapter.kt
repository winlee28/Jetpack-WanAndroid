package com.win.ft_home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.win.ft_home.R
import com.win.ft_home.databinding.TreeListItemBinding
import com.win.ft_home.model.tree.TreeData
import com.win.lib_base.service.treedetail.warp.TreeDetailServiceImplWarp
import com.win.lib_common_ui.flowlayout.adapter.TagAdapter

/**
 * Create by liwen on 2020-05-21
 */
class TreeListAdapter(context: Context, data: MutableList<TreeData>) :
    RecyclerView.Adapter<TreeListAdapter.ViewHolder>() {

    private val mContext = context
    private var mData: MutableList<TreeData> = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<TreeListItemBinding>(
            LayoutInflater.from(mContext),
            R.layout.tree_list_item,
            parent,
            false
        )
        return ViewHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.setData(mData[position], mContext)
    }

    class ViewHolder(
        itemView: View,
        binding: TreeListItemBinding
    ) : RecyclerView.ViewHolder(itemView) {

        private val mBinding = binding

        fun setData(
            treeDataItem: TreeData,
            mContext: Context
        ) {

            mBinding.treeData = treeDataItem

            val tagChildren = treeDataItem.children

            mBinding.mFlowLayout.setAdapter(object : TagAdapter() {
                override fun getItemCount(): Int {
                    return tagChildren.size
                }

                override fun createView(
                    inflater: LayoutInflater,
                    parent: ViewGroup,
                    position: Int
                ): View {
                    return inflater.inflate(R.layout.flow_layout_item, parent, false)
                }

                override fun bindView(view: View, position: Int) {

                    (view as TextView).text = tagChildren[position].name

                }

                override fun onItemViewClick(view: View, position: Int) {

                    val item = tagChildren[position]

                    TreeDetailServiceImplWarp.instance.start(mContext, item.id, item.name)

                }

            })
        }

    }
}