package com.example.funnumberfacts.repository

import com.example.funnumberfacts.data.NumberFact
import com.example.funnumberfacts.db.FactItem
import com.example.funnumberfacts.db.NumberFactDao
import com.example.funnumberfacts.db.toNumberFact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

interface NumberFactRepository {
    fun getHistory(): MutableList<NumberFact>
    fun getFactById(id: Int): NumberFact?
    fun addFactToHistory(item: NumberFact)
    fun clearHistory()
}

class NumberFactRepositoryImpl(private val numberFactDao: NumberFactDao) : NumberFactRepository {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var history: MutableList<NumberFact> = mutableListOf()
    private var fact: NumberFact? = null
    override fun getHistory(): MutableList<NumberFact> {
        scope.launch {
            async {
                history = numberFactDao.getFactHistory()
                    .map { it.toNumberFact() }
                    .toMutableList()
            }.await()
        }
        return history
    }

    override fun getFactById(id: Int): NumberFact? {
        scope.launch {
            async {
                fact = numberFactDao.getFactById(id).toNumberFact()
            }.await()
        }
        return fact
    }

    override fun addFactToHistory(item: NumberFact) {
        scope.launch {
            numberFactDao.addToHistory(
                FactItem(
                    number = item.number,
                    text = item.fact
                )
            )
            history.add(item)
        }
    }

    override fun clearHistory() {
        scope.launch {
            numberFactDao.clearHistory()
        }
    }
}