package com.example.funnumberfacts.repository

import android.util.Log
import androidx.paging.PagingSource
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
    fun getHistory(): PagingSource<Int, FactItem>
    suspend fun getFactById(id: Int): NumberFact?
    fun addFactToHistory(item: NumberFact)
    fun clearHistory()
}

class NumberFactRepositoryImpl(private val numberFactDao: NumberFactDao) : NumberFactRepository {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var fact: NumberFact? = null

    override fun getHistory(): PagingSource<Int, FactItem> =
        numberFactDao.getHistory()

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
//            todo: add item to paging list
        }
    }

    override fun clearHistory() {
        scope.launch {
            numberFactDao.clearHistory()
        }
    }
}