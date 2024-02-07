package com.example.funnumberfacts.network

import com.example.funnumberfacts.data.ApiUrl
import com.example.funnumberfacts.network.numberservice.NumberService
import com.example.funnumberfacts.network.numberservice.NumberServiceImpl
import retrofit2.create

interface ApiServiceFactory {
    fun numberService(): NumberService
}

class ApiServiceFactoryImpl(apiUrl: ApiUrl) : ApiServiceFactory {

    private val apiClient = ApiClient(apiUrl)
    override fun numberService(): NumberService = NumberServiceImpl(apiClient.create())
}