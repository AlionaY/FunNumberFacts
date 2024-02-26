package com.example.funnumberfacts.network.service.factory

import com.example.funnumberfacts.data.ApiUrl
import com.example.funnumberfacts.network.ApiClient
import com.example.funnumberfacts.network.service.factservice.NumberFactService
import com.example.funnumberfacts.network.service.factservice.NumberFactServiceImpl
import retrofit2.create

class ApiServiceFactoryImpl(apiUrl: ApiUrl) : ApiServiceFactory {

    private val apiClient = ApiClient(apiUrl)

    override fun numberService(): NumberFactService = NumberFactServiceImpl(apiClient.create())
}