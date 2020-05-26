package com.win.lib_webview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * Create by liwen on 2020/5/26
 */
class WebViewActivity : AppCompatActivity() {

    private var mTitle: String? = null
    private var mUrl: String? = null

    companion object {
        const val WEB_VIEW_URL = "web_view_url"
        const val WEB_VIEW_TITLE = "web_view_title"


        fun goToPage(context: Context, title: String, url: String) {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra(WEB_VIEW_TITLE, title)
            intent.putExtra(WEB_VIEW_URL, url)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        StatusBarKt.fitSystemBar(this)

        setContentView(R.layout.activity_webview)

        initIntent()
        initActionBar()
        initWebView()

    }

    private fun initActionBar() {
        mTvTitle.text = mTitle
        mIvBack.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("JavascriptInterface")
    private fun initWebView() {
        val settings = mWebView.settings
        settings.allowContentAccess = true
        settings.domStorageEnabled = true
        settings.allowFileAccess = true

        mWebView.addJavascriptInterface(this, "wan")

        mWebView.webChromeClient = object : WebChromeClient() {

        }

        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {

                return super.shouldOverrideUrlLoading(view, request)
            }
        }

        mWebView.loadUrl(mUrl)

    }

    private fun initIntent() {
        val intent = intent
        mUrl = intent.getStringExtra(WEB_VIEW_URL)
        mTitle = intent.getStringExtra(WEB_VIEW_TITLE)
    }


}