package com.example.funnumberfacts.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val history = viewModel.historyFlow.collectAsLazyPagingItems()
    val viewState by viewModel.viewState.collectAsState()
    val (text, isValidInput) = viewState

    HomeScreenContent(
        text = text,
        isValidInput = isValidInput,
        history = history,
        onAction = { action ->
            when (action) {
                is HomeScreenAction.OnNumberEntered -> viewModel.onNumberEntered(action.number)
                HomeScreenAction.OnGetNumberFactClick -> viewModel.onGetNumberFactClick()
                HomeScreenAction.OnGetRandomNumberFactClick -> viewModel.onGetRandomNumberFactClick()
                HomeScreenAction.OnHistoryItemClick -> {
//                    todo: navigate to history screen
                }
            }
        }
    )
}