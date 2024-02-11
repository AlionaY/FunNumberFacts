package com.example.funnumberfacts.ui.screen.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.funnumberfacts.db.FactItem
import com.example.funnumberfacts.repository.NumberFactRepository

class FactsPagingSource(
    private val repository: NumberFactRepository
) : PagingSource<Int, FactItem>() {
    override fun getRefreshKey(state: PagingState<Int, FactItem>): Int? {
        val anchorPosition = state.anchorPosition ?: 0
        val closestPage = state.closestPageToPosition(anchorPosition)
        return closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FactItem> {
        val nextPage = params.key ?: 1
        val result = try {
            LoadResult.Page(
                data = repository.getHistory(),
                nextKey = nextPage.plus(1),
                prevKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
        return result
    }
}