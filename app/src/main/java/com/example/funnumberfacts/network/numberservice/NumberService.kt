package com.example.funnumberfacts.network.numberservice

interface NumberService {
    suspend fun getFact(number: Int): String
    suspend fun getRandomFact(): String
}

class NumberServiceImpl(private val service: NumberRetrofitService) : NumberService {
    override suspend fun getFact(number: Int): String =
        service.getFact(number)

    override suspend fun getRandomFact(): String =
        service.getRandomFact()
}