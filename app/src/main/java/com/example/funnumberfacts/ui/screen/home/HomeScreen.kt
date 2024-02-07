package com.example.funnumberfacts.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.funnumberfacts.R
import com.example.funnumberfacts.ui.component.NumberFactButton
import com.example.funnumberfacts.ui.component.NumberTextField

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val viewState by viewModel.viewState.collectAsState()
    val (text, isValidInput) = viewState

    HomeScreenContent(
        text = text,
        isValidInput = isValidInput,
        onAction = { action ->
            handleScreenAction(action, viewModel)
        }
    )
}

private fun handleScreenAction(
    action: HomeScreenAction,
    viewModel: HomeViewModel
) {
    when (action) {
        is HomeScreenAction.OnNumberEntered -> viewModel.onNumberEntered(action.number)
        HomeScreenAction.OnGetNumberFactClick -> viewModel.onGetNumberFactClick()
        HomeScreenAction.OnGetRandomNumberFactClick -> viewModel.onGetRandomNumberFactClick()
        HomeScreenAction.OnHistoryItemClick -> {
//            todo: navigate to history screen
        }
    }
}

