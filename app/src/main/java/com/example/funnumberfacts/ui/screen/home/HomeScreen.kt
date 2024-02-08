package com.example.funnumberfacts.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.funnumberfacts.ui.navigation.Routes
import com.example.funnumberfacts.util.navigate

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val history = viewModel.historyFlow.collectAsLazyPagingItems()
    val viewState by viewModel.viewState.collectAsState()
    val (text, isValidInput) = viewState

    val focusManager = LocalFocusManager.current

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