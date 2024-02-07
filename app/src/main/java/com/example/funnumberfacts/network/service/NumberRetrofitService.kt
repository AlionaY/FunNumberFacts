package com.example.funnumberfacts.network.service

import com.example.funnumberfacts.network.response.FactResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NumberRetrofitService {
    @GET("{number}?json")
    fun getFactAboutNumber(@Path("number") number: Int): FactResponse

    @GET("random/math?json")
    fun getRandomFact(): FactResponse
}