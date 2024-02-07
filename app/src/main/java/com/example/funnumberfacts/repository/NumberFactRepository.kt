package com.example.funnumberfacts.repository

import android.util.Log
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
    suspend fun getHistory(): MutableList<NumberFact>
    suspend fun getFactById(id: Int): NumberFact?
    fun addFactToHistory(item: NumberFact)
    fun clearHistory()
}

class NumberFactRepositoryImpl(private val numberFactDao: NumberFactDao) : NumberFactRepository {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var history: MutableList<NumberFact> = mutableListOf()
    private var fact: NumberFact? = null
    override suspend fun getHistory(): MutableList<NumberFact> {
        val job = scope.async {
            numberFactDao.getFactHistory()
                .map { it.toNumberFact() }
                .toMutableList()
        }
        history = job.await()
        return history
    }

    override suspend fun getFactById(id: Int): NumberFact? {
        val job = scope.async {
                numberFactDao.getFactById(id).toNumberFact()
            }
        fact = job.await()
        return fact
    }

    override fun addFactToHistory(item: NumberFact) {
        scope.launch {
            runCatching {
                numberFactDao.addToHistory(
                    FactItem(
                        number = item.number,
                        text = item.fact
                    )
                )
            }.onFailure {
//                todo: handle error
                Log.d("$$$", "error $it")
            }
            history.add(item)
        }
    }

    override fun clearHistory() {
        scope.launch {
            numberFactDao.clearHistory()
        }
    }
}