package com.example.funnumberfacts.network.service

import com.example.funnumberfacts.data.NumberFact
import com.example.funnumberfacts.network.response.toNumberFact

interface NumberFactService {
    suspend fun getFactAboutNumber(number: Int): NumberFact
    suspend fun getRandomFact(): NumberFact
}

class NumberFactServiceImpl(
    private val service: NumberRetrofitService,
) : NumberFactService {
    override suspend fun getFactAboutNumber(number: Int): NumberFact =
        service.getFactAboutNumber(number).toNumberFact()

    override suspend fun getRandomFact(): NumberFact =
        service.getRandomFact().toNumberFact()
}