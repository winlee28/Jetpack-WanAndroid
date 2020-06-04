package com.win.ft_home.ui.project

import android.graphics.Typeface
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.win.ft_home.R
import com.win.ft_home.databinding.FragmentProjectBinding
import com.win.ft_home.model.project.ProjectTabItem
import com.win.lib_base.base.BaseFragment

class ProjectFragment : BaseFragment<ProjectViewModel, FragmentProjectBinding>() {

    private var mData: MutableList<ProjectTabItem>? = null
    private lateinit var mediator: TabLayoutMediator
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager2

    override fun getLayoutResId(): Int = R.layout.fragment_project

    override fun initData() {
        mViewModel.getTabData().observe(this, Observer {
            mData = it
            initView()
        })
    }

    override fun initView() {

        mViewPager = mViewBinding.viewPager
        mTabLayout = mViewBinding.tabLayout

        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView as TextView
                customView.textSize = 14f
                customView.typeface = Typeface.DEFAULT
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView as TextView
                customView.textSize = 16f
                customView.typeface = Typeface.DEFAULT_BOLD
            }

        })


        mViewPager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT

        mViewPager.adapter = object : FragmentStateAdapter(childFragmentManager, lifecycle) {
            override fun getItemCount(): Int {
                if (mData != null && mData!!.size > 0) {
                    return mData!!.size
                }
                return 0
            }

            override fun createFragment(position: Int): Fragment {
                val item = mData!![position]
                return createItemFragment(item.id)
            }

        }

        mediator = TabLayoutMediator(
            mTabLayout,
            mViewPager,
            true,
            TabLayoutMediator.TabConfigurationStrategy { tab, position -> //创建tab
                tab.customView = createTabView(position)
            })

        mediator.attach()

    }

    private fun createItemFragment(id: Int): Fragment {
        return TabItemFragment.newInstance(id)
    }

    private fun createTabView(position: Int): View {

        if (mData != null && mData!!.size > 0) {
            val item = mData!![position]
            val textView = TextView(requireContext())

//            val states = arrayOfNulls<IntArray>(2)
//            states[0] = intArrayOf(android.R.attr.state_selected)
//            states[1] = intArrayOf()
//
//            val colors = intArrayOf(
//                resources.getColor(R.color.tab_text_selected_color),
//                resources.getColor(R.color.tab_text_default_color)
//            )
//            val stateList = ColorStateList(states, colors)
//            textView.setTextColor(stateList)

            textView.text = Html.fromHtml(item.name)
            return textView
        }

        return TextView(requireContext())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediator.detach()
    }

}