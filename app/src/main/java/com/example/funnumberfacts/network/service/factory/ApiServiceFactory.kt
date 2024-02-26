package com.example.funnumberfacts.network.service.factory

import com.example.funnumberfacts.network.service.factservice.NumberFactService

interface ApiServiceFactory {
    fun numberService(): NumberFactService
}