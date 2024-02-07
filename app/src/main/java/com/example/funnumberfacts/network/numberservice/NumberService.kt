package com.example.funnumberfacts.network.numberservice

import com.example.funnumberfacts.data.NumberFact
import com.example.funnumberfacts.network.response.toNumberFact

interface NumberService {
    suspend fun getFact(number: Int): NumberFact
    suspend fun getRandomFact(): NumberFact
}

class NumberServiceImpl(private val service: NumberRetrofitService) : NumberService {
    override suspend fun getFact(number: Int): NumberFact =
        service.getFact(number).toNumberFact()

    override suspend fun getRandomFact(): NumberFact =
        service.getRandomFact().toNumberFact()
}