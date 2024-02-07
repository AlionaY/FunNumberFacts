package com.example.funnumberfacts.network

import com.example.funnumberfacts.data.ApiUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 50L

private val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
    .build()

fun ApiClient(
    apiUrl: ApiUrl,
): Retrofit = Retrofit.Builder()
    .baseUrl(apiUrl.url)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()