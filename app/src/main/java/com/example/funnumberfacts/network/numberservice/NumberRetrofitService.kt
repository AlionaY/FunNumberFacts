package com.example.funnumberfacts.network.numberservice

import retrofit2.http.GET
import retrofit2.http.Path

interface NumberRetrofitService {
    @GET("{number}")
    fun getFact(@Path("number") number: Int): String

    @GET("random/math")
    fun getRandomFact(): String
}