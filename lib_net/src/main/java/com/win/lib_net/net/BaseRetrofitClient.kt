package com.win.lib_net.net


import com.win.lib_net.BuildConfig
import com.win.lib_net.interceptor.CommonInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by luyao
 * on 2018/3/13 14:58
 */
abstract class BaseRetrofitClient {

    companion object {
        private const val TIME_OUT = 5
    }

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.BASIC
            }

            builder.addInterceptor(logging)
                .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor(CommonInterceptor())

            handleBuilder(builder)

            return builder.build()
        }

    protected abstract fun handleBuilder(builder: OkHttpClient.Builder)

    fun <S> getService(serviceClass: Class<S>): S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.wanandroid.com")
            .build().create(serviceClass)
    }
}
