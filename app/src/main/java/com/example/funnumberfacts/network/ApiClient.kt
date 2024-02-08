package com.example.funnumberfacts.network

import com.example.funnumberfacts.data.ApiUrl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
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

private val json = Json {
    isLenient = true
    ignoreUnknownKeys = true
    prettyPrint = true
}

fun ApiClient(
    apiUrl: ApiUrl,
): Retrofit = Retrofit.Builder()
    .baseUrl(apiUrl.url)
    .addConverterFactory(getFactory())
    .client(okHttpClient)
    .build()

fun getFactory(): Converter.Factory {
    val media : MediaType = "application/json".toMediaType()
    return json.asConverterFactory(media)
}