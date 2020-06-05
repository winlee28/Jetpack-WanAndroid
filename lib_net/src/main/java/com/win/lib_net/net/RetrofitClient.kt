package com.win.lib_net.net


import com.win.lib_net.BuildConfig
import com.win.lib_net.interceptor.CommonInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitClient private constructor() {

    private var retrofit: Retrofit

    companion object {
        val instance: RetrofitClient by lazy { RetrofitClient() }
    }


    init {

        retrofit = Retrofit.Builder()
            .client(initClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.wanandroid.com")
            .build()

    }

    private fun initClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(initLogInterceptor())
            .addInterceptor(CommonInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    private fun initLogInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }
        return loggingInterceptor
    }


    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }


}