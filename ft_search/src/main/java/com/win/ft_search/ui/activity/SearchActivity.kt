package com.win.ft_search.ui.activity

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.win.ft_search.R
import com.win.ft_search.databinding.ActivitySearchBinding
import com.win.ft_search.ui.fragment.HotKeyFragment
import com.win.ft_search.ui.fragment.SearchResultFragment
import com.win.ft_search.ui.viewmodel.SearchViewModel
import com.win.lib_base.base.BaseActivity
import com.win.lib_base.utils.KeyBoardUtils

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

                    //隐藏光标和软键盘
                    mViewBinding.search.clearFocus()
                    KeyBoardUtils.hideKeyboard(mViewBinding.search)

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

    fun setHotKeyInputSearch(hotKey: String) {
        mViewBinding.search.setText(hotKey)
    }

}