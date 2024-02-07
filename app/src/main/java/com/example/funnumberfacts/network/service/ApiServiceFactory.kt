package com.example.funnumberfacts.network.service

import com.example.funnumberfacts.data.ApiUrl
import com.example.funnumberfacts.network.ApiClient
import retrofit2.create

interface ApiServiceFactory {
    fun numberService(): NumberFactService
}

class ApiServiceFactoryImpl(apiUrl: ApiUrl) : ApiServiceFactory {

    private val apiClient = ApiClient(apiUrl)
    
    override fun numberService(): NumberFactService = NumberFactServiceImpl(apiClient.create())
}