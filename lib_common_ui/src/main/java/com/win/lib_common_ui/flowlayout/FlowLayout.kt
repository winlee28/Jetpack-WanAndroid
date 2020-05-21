package com.win.lib_common_ui.flowlayout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup

/**
 * Create by liwen on 2020-05-21
 *
 * 也可以使用FlexBoxLayout来完成流式布局 本例使用的是自定义View
 */
open class FlowLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) :
    ViewGroup(context, attributeSet, defStyle) {


    private val mAllViews = mutableListOf<MutableList<View>>()

    private val mLineHeight = mutableListOf<Int>()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        mAllViews.clear()
        mLineHeight.clear()

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        var linesWidth = 0
        var linesHeight = 0 //每行最高的childview

        var maxHeight = 0 // 各行累加的height

        val childCount = childCount

        var curViewList = mutableListOf<View>()

        for (i in 0 until childCount) {

            val child = getChildAt(i)

            if (child.visibility == View.GONE) {
                continue
            }

            measureChild(child, widthMeasureSpec, heightMeasureSpec)

            val lp = child.layoutParams as MarginLayoutParams

            val cWidth = child.measuredWidth + lp.leftMargin + lp.rightMargin
            val cHeight = child.measuredHeight + lp.topMargin + lp.bottomMargin

            if (linesWidth + cWidth > widthSize - (paddingLeft + paddingRight)) {
                //换行
                maxHeight += linesHeight
                mLineHeight.add(linesHeight)

                mAllViews.add(curViewList)
                curViewList = mutableListOf()
                curViewList.add(child)

                linesWidth = cWidth
                linesHeight = cHeight

            } else {
                //未换行
                linesWidth += cWidth
                linesHeight = linesHeight.coerceAtLeast(cHeight)

                curViewList.add(child)

            }

            if (i == childCount - 1) {
                maxHeight += linesHeight
                mLineHeight.add(linesHeight)
                mAllViews.add(curViewList)
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            maxHeight = heightSize
        } else if (heightMode == MeasureSpec.AT_MOST) {
            maxHeight = maxHeight.coerceAtLeast(heightSize)
            maxHeight += paddingBottom + paddingTop
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            maxHeight += paddingBottom + paddingTop
        }
        setMeasuredDimension(widthSize, maxHeight)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {


        var left = paddingLeft
        var top = paddingTop

        val size = mAllViews.size

        for (i in 0 until size) {
            val lineViews = mAllViews[i]
            val lineHeight = mLineHeight[i]

            for (j in 0 until lineViews.size) {

                val childView = lineViews[j]

                val lp = childView.layoutParams as MarginLayoutParams

                val lc = left + lp.leftMargin
                val tc = top + lp.topMargin
                val rc = lc + childView.measuredWidth
                val bc = tc + childView.measuredHeight


                childView.layout(lc, tc, rc, bc)

                left += childView.measuredWidth + lp.leftMargin + lp.rightMargin

            }

            left = paddingLeft
            top += lineHeight

        }
    }


    //child没有设置LayoutParams  给默认的  一般是new的时候调用
    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    //xml中生成布局 调用
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    //addview 的时候将LayoutParams转换为MarginLayoutParams
    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }

    //addview 的时候 检测是否是MarginLayoutParams
    override fun checkLayoutParams(p: LayoutParams?): Boolean {
        return p is MarginLayoutParams
    }

}