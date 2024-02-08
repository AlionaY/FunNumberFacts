package com.example.funnumberfacts.ui.screen.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.funnumberfacts.data.NumberFact
import com.example.funnumberfacts.ui.theme.FunNumberFactsTheme

@Composable
fun FactDetailsScreen(
    navController: NavController,
    viewModel: FactDetailsViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    val (fact) = viewState

    BackHandler {
        navController.popBackStack()
    }

    ScreenContent(
        fact = fact,
        onBackClick = navController::popBackStack
    )
}

@Composable
private fun ScreenContent(
    fact: NumberFact?,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(listOf(Color.Yellow, Color.Green, Color.Blue)))
            .padding(18.dp)
    ) {
        BackButton(
            modifier = Modifier.align(Alignment.TopStart),
            onBackClick = onBackClick
        )

        FactDetails(
            fact = fact,
            modifier = Modifier.Companion.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun ScreenContentPreview() {
    FunNumberFactsTheme {
        ScreenContent(fact = NumberFact(1, "sljhdgflbsdbcsd")) {}
    }
}