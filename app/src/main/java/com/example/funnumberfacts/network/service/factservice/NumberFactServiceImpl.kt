package com.example.funnumberfacts.network.service.factservice

import com.example.funnumberfacts.data.NumberFact
import com.example.funnumberfacts.network.response.toNumberFact
import com.example.funnumberfacts.network.service.NumberRetrofitService

class NumberFactServiceImpl(
    private val service: NumberRetrofitService,
) : NumberFactService {
    override suspend fun getFactAboutNumber(number: Int): NumberFact =
        service.getFactAboutNumber(number).toNumberFact()

    override suspend fun getRandomFact(): NumberFact =
        service.getRandomFact().toNumberFact()
}