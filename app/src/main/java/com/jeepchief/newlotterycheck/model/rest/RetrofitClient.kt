package com.jeepchief.newlotterycheck.model.rest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private val BASE_URL = "https://www.dhlottery.co.kr/"
    private var instance: Retrofit? = null
    private val httpClient = OkHttpClient.Builder().apply {
        addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        connectTimeout(5, TimeUnit.SECONDS)
    }.build()

    @Synchronized
    fun getInstance() : Retrofit {
        return instance ?: run {
            instance = Retrofit.Builder().apply {
                baseUrl(BASE_URL)
                client(httpClient)
                addConverterFactory(GsonConverterFactory.create())
            }.build()
            instance!!
        }
    }
}