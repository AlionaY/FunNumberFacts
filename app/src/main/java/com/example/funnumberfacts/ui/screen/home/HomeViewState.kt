package com.example.funnumberfacts.ui.screen.home

import androidx.paging.PagingData
import com.example.funnumberfacts.data.ScreenState
import com.example.funnumberfacts.db.FactItem
import kotlinx.coroutines.flow.Flow

data class HomeViewState(
    val history: Flow<PagingData<FactItem>>,
    val textInput: String = "",
    val isValidInput: Boolean = true,
    val screenState: ScreenState = ScreenState.Idle,
    val needToRefreshData: Boolean = false,
)