package com.example.funnumberfacts.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.funnumberfacts.ui.navigation.Routes
import com.example.funnumberfacts.util.HandleError
import com.example.funnumberfacts.util.HandleProgressBar
import com.example.funnumberfacts.util.navigate

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val viewState by viewModel.viewState.collectAsState()
    val (factsHistory, text, isValidInput, screenState, needToRefreshData) = viewState
    val history = factsHistory.collectAsLazyPagingItems()

    val focusManager = LocalFocusManager.current

    HandleProgressBar(screenState = screenState) {
        viewModel.onDismissRequest()
    }
    HandleError(screenState = screenState) {
        viewModel.onDismissRequest()
    }

    LaunchedEffect(key1 = needToRefreshData) {
        if (needToRefreshData) {
            history.refresh()
            viewModel.invalidate()
        }
    }

    HomeScreenContent(
        text = text,
        isValidInput = isValidInput,
        history = history,
        onAction = { action ->
            when (action) {
                is HomeScreenAction.OnNumberEntered -> viewModel.onNumberEntered(action.number)
                is HomeScreenAction.OnHistoryItemClick -> navigateToItemDetails(navController, action)
                HomeScreenAction.OnGetNumberFactClick -> {
                    viewModel.onGetNumberFactClick()
                    focusManager.clearFocus()
                }
                HomeScreenAction.OnGetRandomNumberFactClick -> viewModel.onGetRandomNumberFactClick()
                HomeScreenAction.OnClearNumberInputClick -> viewModel.onClearNumberInputClick()
                HomeScreenAction.OnClearHistoryClick -> viewModel.clearHistory()
            }
        }
    )
}

private fun navigateToItemDetails(
    navController: NavController,
    action: HomeScreenAction.OnHistoryItemClick
) {
    navController.navigate(
        destination = Routes.FactDetailsScreen,
        argument = action.itemId
    )
}