package com.win.ft_home.ui.navi

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.win.ft_home.R
import com.win.ft_home.`interface`.NavigationTabItemSelectedListener
import com.win.ft_home.adapter.NavigationTabAdapter
import com.win.ft_home.databinding.FragmentNavigationBinding
import com.win.ft_home.model.navigation.NavigationItem
import com.win.ft_home.model.navigation.NavigationItemDetail
import com.win.lib_base.base.BaseFragment

class NavigationFragment : BaseFragment<NavigationViewModel, FragmentNavigationBinding>() {

    private lateinit var mTabAdapter: NavigationTabAdapter
    private lateinit var mViewPager2: ViewPager2
    private lateinit var mTabRecycleView: RecyclerView

    override fun getLayoutResId(): Int = R.layout.fragment_navigation

    override fun initView() {

        mTabRecycleView = mViewBinding.tabRecycleView

        mTabRecycleView.itemAnimator = null
        mTabRecycleView.animation = null
        mTabRecycleView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        mViewPager2 = mViewBinding.viewPager2
        mViewPager2.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        mViewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL

        mViewPager2.registerOnPageChangeCallback(viewPager2Callback)

    }

    override fun initData() {

        mViewModel.getNavigationData().observe(this, Observer {

            performTabRecycleView(it)
            performViewPagerData(it)

        })

    }

    private fun performTabRecycleView(item: MutableList<NavigationItem>) {

        mTabAdapter = NavigationTabAdapter(requireContext(), item)

        mTabAdapter.setItemSelectedListener(object : NavigationTabItemSelectedListener {
            override fun onItemSelected(itemSub: NavigationItem, position: Int) {
                mTabAdapter.setItemPositionSelected(position)
                mTabAdapter.notifyDataSetChanged()

                mViewPager2.setCurrentItem(position, false)

            }
        })

        mTabRecycleView.adapter = mTabAdapter

    }

    private fun performViewPagerData(
        item: MutableList<NavigationItem>
    ) {

        mViewPager2.adapter =
            object : FragmentStateAdapter(requireActivity().supportFragmentManager, lifecycle) {
                override fun getItemCount(): Int {
                    return item.size
                }

                override fun createFragment(position: Int): Fragment {
                    return NavigationDataPageFragment.newInstance(item[position].articles)
                }

            }

    }

    private val viewPager2Callback = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            mTabAdapter.setItemPositionSelected(position)
            mTabAdapter.notifyDataSetChanged()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewPager2.unregisterOnPageChangeCallback(viewPager2Callback)
    }

}