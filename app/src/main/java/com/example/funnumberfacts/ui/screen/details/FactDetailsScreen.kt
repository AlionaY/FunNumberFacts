package com.example.funnumberfacts.ui.screen.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.funnumberfacts.R
import com.example.funnumberfacts.data.NumberFact
import com.example.funnumberfacts.ui.theme.Eggplant
import com.example.funnumberfacts.ui.theme.FunNumberFactsTheme
import com.example.funnumberfacts.ui.theme.LavenderMagenta
import com.example.funnumberfacts.ui.theme.Melanzane
import com.example.funnumberfacts.ui.theme.Orchid
import com.example.funnumberfacts.ui.theme.WhiteLilac
import com.example.funnumberfacts.ui.theme.WispPink

@Composable
fun FactDetailsScreen(
    navController: NavController,
    viewModel: FactDetailsViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    val (screenState, fact) = viewState

    BackHandler {
        navController.popBackStack()
    }

    ScreenContent(
        fact = fact,
        onBackClick = { navController.popBackStack() },
        modifier = Modifier
            .fillMaxSize()
            .background(brush = getScreenBrush())
            .systemBarsPadding()
    )
}

@Composable
private fun ScreenContent(
    fact: NumberFact?,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            BackButton(onBackClick = onBackClick)
            Text(
                text = stringResource(id = R.string.fact_details),
                modifier = Modifier.padding(start = 10.dp),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 18.sp
            )
        }

        FactDetails(
            fact = fact,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 10.dp)
        )
    }
}

@Composable
private fun getScreenBrush() = Brush.linearGradient(
    listOf(
        WhiteLilac,
        WispPink,
        LavenderMagenta,
        Orchid,
        Eggplant,
        Melanzane
    )
)

@Preview
@Composable
private fun ScreenContentPreview() {
    FunNumberFactsTheme {
        ScreenContent(fact = NumberFact(1, "sljhdgflbsdbcsd")) {}
    }
}