package com.example.funnumberfacts.repository

import com.example.funnumberfacts.data.NumberFact
import com.example.funnumberfacts.db.FactItem
import com.example.funnumberfacts.db.NumberFactDao
import com.example.funnumberfacts.db.toNumberFact

interface NumberFactRepository {
    suspend fun getHistory(): List<NumberFact>
    suspend fun getFactById(id: Int): NumberFact
    suspend fun addFactToHistory(item: NumberFact)
    suspend fun clearHistory()
}

class NumberFactRepositoryImpl(val numberFactDao: NumberFactDao) : NumberFactRepository {
    override suspend fun getHistory(): List<NumberFact> =
        numberFactDao.getFactHistory().map { it.toNumberFact() }

    override suspend fun getFactById(id: Int): NumberFact =
        numberFactDao.getFactById(id).toNumberFact()

    override suspend fun addFactToHistory(item: NumberFact) {
        numberFactDao.addToHistory(
            FactItem(
                number = item.number,
                text = item.fact
            )
        )
    }

    override suspend fun clearHistory() {
        numberFactDao.clearHistory()
    }
}