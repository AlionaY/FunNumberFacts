package com.example.funnumberfacts.repository

import com.example.funnumberfacts.data.NumberFact
import com.example.funnumberfacts.db.FactItem

interface NumberFactRepository {
    suspend fun getHistory(): List<FactItem>
    suspend fun getFactById(id: Int): NumberFact?
    suspend fun clearHistory()
    suspend fun getFactAboutNumber(number: Int)
    suspend fun getRandomFact()
}