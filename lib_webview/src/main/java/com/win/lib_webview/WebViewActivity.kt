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
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * Create by liwen on 2020/5/26
 */
class WebViewActivity : AppCompatActivity() {

    @Autowired
    lateinit var title: String

    @Autowired
    lateinit var url: String

    companion object {

        fun start(context: Context, title: String, url: String) {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("url", url)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ARouter.getInstance().inject(this)

        StatusBarKt.fitSystemBar(this)

        setContentView(R.layout.activity_webview)

        initActionBar()
        initWebView()

    }

    private fun initActionBar() {
        mTvTitle.text = title
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

        mWebView.loadUrl(url)

    }

}