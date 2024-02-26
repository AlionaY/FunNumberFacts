package com.example.funnumberfacts.ui.screen.home

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.funnumberfacts.db.FactItem
import com.example.funnumberfacts.repository.NumberFactRepository

private const val LIMIT = 15

class FactsPagingSource(
    private val repository: NumberFactRepository,
    private val defaultLimit: Int = LIMIT,
) : PagingSource<Int, FactItem>() {
    override fun getRefreshKey(state: PagingState<Int, FactItem>): Int? {
        val anchorPosition = state.anchorPosition ?: 0
        val closestPage = state.closestPageToPosition(anchorPosition)
        return closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FactItem> {
        val currentOffset = params.key ?: 0
        val data = repository.getHistory()

        val nextKey = when {
            data.isEmpty() -> null
            data.size < defaultLimit -> null
            else -> currentOffset.plus(data.size)
        }
        Log.d(
            "$$$",
            "offset $currentOffset, params key ${params.key}, next key $nextKey, data size ${data.size}"
        )

        val result = try {
            LoadResult.Page(
                data = data,
                nextKey = nextKey,
                prevKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
        return result
    }
}