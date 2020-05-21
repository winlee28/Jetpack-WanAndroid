package com.win.lib_common_ui.flowlayout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.win.lib_common_ui.flowlayout.adapter.TagAdapter
import com.win.lib_common_ui.flowlayout.`interface`.NotifyDataSetChangedListener

/**
 * Create by liwen on 2020-05-21
 */
class TagFlowLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : FlowLayout(context, attributeSet, defStyle),
    NotifyDataSetChangedListener {

    private var maxSelectedCount: Int = 1



    private lateinit var mAdapter: TagAdapter

    fun setMaxSelectedCount(count: Int) {
        maxSelectedCount = count
    }


    fun setAdapter(adapter: TagAdapter) {

        mAdapter = adapter

        mAdapter.setNotifyDataSetChangedListener(this)

        onDataChanged()

    }


    override fun onDataChanged() {

        removeAllViews()

        val tagAdapter = mAdapter

        for (i in 0 until tagAdapter.getItemCount()) {
            val view = tagAdapter.createView(LayoutInflater.from(context), this, i)
            tagAdapter.bindView(view, i)

            addView(view)

            bindViewMethod(view, i)
        }
    }

    private fun bindViewMethod(view: View, position: Int) {

        view.setOnClickListener {

            mAdapter.onItemViewClick(view, position)

            if (maxSelectedCount <= 0) {
                return@setOnClickListener
            }

            //没有被选中
            if (!view.isSelected) {
                if (getSelectedViewCount() >= maxSelectedCount) {
                    //单选
                    if (maxSelectedCount == 1) {
                        val selectView = getSelectedView()
                        selectView?.isSelected = false
                    } else {
                        //多选
                        //提示
                        mAdapter.toastForMultiple(context)
                        return@setOnClickListener
                    }
                }
            }

            view.isSelected = !view.isSelected

        }
    }

    fun getSelectedViewIndex(): MutableList<Int> {

        val list = mutableListOf<Int>()
        val itemCount = mAdapter.getItemCount()
        for (i in 0 until itemCount) {
            val view = getChildAt(i)
            if (view.isSelected) {
                list.add(i)
            }
        }

        return list
    }

    private fun getSelectedView(): View? {
        val itemCount = mAdapter.getItemCount()
        for (i in 0 until itemCount) {
            val view = getChildAt(i)
            if (view.isSelected) {
                return view
            }
        }

        return null
    }

    private fun getSelectedViewCount(): Int {

        val itemCount = mAdapter.getItemCount()
        var count = 0
        for (i in 0 until itemCount) {
            val view = getChildAt(i)
            if (view.isSelected) {
                count++
            }
        }

        return count
    }


}