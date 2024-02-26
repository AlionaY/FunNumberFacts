package com.example.funnumberfacts.network.service.factservice

import com.example.funnumberfacts.data.NumberFact

interface NumberFactService {
    suspend fun getFactAboutNumber(number: Int): NumberFact
    suspend fun getRandomFact(): NumberFact
}