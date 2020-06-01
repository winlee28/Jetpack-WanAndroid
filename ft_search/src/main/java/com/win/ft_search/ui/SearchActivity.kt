package com.win.ft_search.ui

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.win.ft_search.R
import com.win.ft_search.adapter.SearchHotKeyAdapter
import com.win.ft_search.databinding.ActivitySearchBinding
import com.win.lib_base.base.BaseActivity

/**
 * Create by liwen on 2020/6/1
 */
class SearchActivity : BaseActivity<SearchViewModel, ActivitySearchBinding>() {

    override fun getLayoutResId(): Int =
        R.layout.activity_search


    override fun initData() {

    }

    override fun initView() {

        addFragment(HotKeyFragment.newInstance(), "HotKeyFragment")

        mViewBinding.search.requestFocus()
        mViewBinding.cancel.setOnClickListener {
            finish()
        }

        mViewBinding.search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    if (getInputData() == null) {
                        return true
                    }

                    addFragment(
                        SearchResultFragment.newInstance(getInputData()!!),
                        "SearchResultFragment"
                    )
                    return true
                }

                return false
            }

        })
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.container, fragment, tag)
        beginTransaction.commit()
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, SearchActivity::class.java))
        }
    }

    private fun getInputData(): String? {
        val data = mViewBinding.search.text.toString().trim()
        if (TextUtils.isEmpty(data)) {
            Toast.makeText(this, "请输入后再查询", Toast.LENGTH_SHORT).show()
            return null
        }
        return data
    }

}