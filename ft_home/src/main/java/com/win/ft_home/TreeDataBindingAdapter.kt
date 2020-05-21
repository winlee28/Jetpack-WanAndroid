package com.win.ft_home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.win.ft_home.model.tree.TreeDataItem
import com.win.lib_common_ui.flowlayout.TagFlowLayout
import com.win.lib_common_ui.flowlayout.adapter.TagAdapter

/**
 * Create by liwen on 2020-05-21
 */
object TreeDataBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["data"])
    fun setTreeDataBindingAdapter(flowLayout: TagFlowLayout, data: MutableList<TreeDataItem>) {
//        flowLayout.setMaxSelectedCount(4)
        flowLayout.setAdapter(object : TagAdapter() {
            override fun getItemCount(): Int {
                return data.size
            }

            override fun createView(
                inflater: LayoutInflater,
                parent: ViewGroup,
                position: Int
            ): View {
                return inflater.inflate(R.layout.flow_layout_item, parent, false)
            }

            override fun bindView(view: View, position: Int) {

                (view as TextView).text = data[position].name

            }

        })

    }


}