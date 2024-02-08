package com.example.funnumberfacts.ui.screen.home

import com.example.funnumberfacts.data.NumberFact
import com.example.funnumberfacts.data.ScreenState

data class HomeViewState(
    val textInput: String = "",
    val isValidInput: Boolean = true,
    val screenState: ScreenState = ScreenState.Idle,
    val number: Int = 0,
    val history: MutableList<NumberFact> = mutableListOf(),
)

sealed class HomeScreenAction {
    class OnNumberEntered(val number: String): HomeScreenAction()
    class OnHistoryItemClick(val itemId: Int): HomeScreenAction()
    object OnGetNumberFactClick: HomeScreenAction()
    object OnGetRandomNumberFactClick: HomeScreenAction()
    object OnClearNumberInputClick: HomeScreenAction()
    object OnClearHistoryClick: HomeScreenAction()
}