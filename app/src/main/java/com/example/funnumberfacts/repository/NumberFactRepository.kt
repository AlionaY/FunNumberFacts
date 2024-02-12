package com.example.funnumberfacts.repository

import com.example.funnumberfacts.data.NumberFact
import com.example.funnumberfacts.db.FactItem
import com.example.funnumberfacts.db.NumberFactDao
import com.example.funnumberfacts.db.toNumberFact
import com.example.funnumberfacts.network.service.NumberFactService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface NumberFactRepository {
    suspend fun getHistory(limit: Int, offset: Int): List<FactItem>
    suspend fun getFactById(id: Int): NumberFact?
    suspend fun clearHistory()
    suspend fun getFactAboutNumber(number: Int)
    suspend fun getRandomFact()
}

class NumberFactRepositoryImpl(
    private val numberFactDao: NumberFactDao,
    private val service: NumberFactService
) : NumberFactRepository {

    override suspend fun getHistory(limit: Int, offset: Int): List<FactItem> =
        numberFactDao.getHistory(limit, offset)

    override suspend fun getFactById(id: Int): NumberFact =
        numberFactDao.getFactById(id).toNumberFact()

    private suspend fun addFactToHistory(item: NumberFact) {
        numberFactDao.addToHistory(
            FactItem(
                number = item.number,
                text = item.fact
            )
        )
    }

    override suspend fun clearHistory() = numberFactDao.clearHistory()

    override suspend fun getFactAboutNumber(number: Int) {
        withContext(Dispatchers.IO) {
            val fact = service.getFactAboutNumber(number)
            addFactToHistory(fact)
        }
    }

    override suspend fun getRandomFact() {
        withContext(Dispatchers.IO) {
            val fact = service.getRandomFact()
            addFactToHistory(fact)
        }
    }
}