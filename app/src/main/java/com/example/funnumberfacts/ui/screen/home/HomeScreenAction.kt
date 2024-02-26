package com.example.funnumberfacts.ui.screen.home

sealed class HomeScreenAction {
    class OnNumberEntered(val number: String): HomeScreenAction()
    class OnHistoryItemClick(val itemId: Int): HomeScreenAction()
    object OnGetNumberFactClick: HomeScreenAction()
    object OnGetRandomNumberFactClick: HomeScreenAction()
    object OnClearNumberInputClick: HomeScreenAction()
    object OnClearHistoryClick: HomeScreenAction()
}