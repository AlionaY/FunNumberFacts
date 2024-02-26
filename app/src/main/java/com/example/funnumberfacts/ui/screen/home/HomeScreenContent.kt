package com.example.funnumberfacts.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.funnumberfacts.db.FactItem
import com.example.funnumberfacts.ui.screen.home.component.FactsHistory
import com.example.funnumberfacts.ui.screen.home.component.GetFactBlock
import com.example.funnumberfacts.ui.screen.home.component.HistoryPreviewParameterProvider
import com.example.funnumberfacts.ui.theme.FunNumberFactsTheme
import com.example.funnumberfacts.ui.theme.LightGray
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreenContent(
    text: String,
    isValidInput: Boolean,
    history: LazyPagingItems<FactItem>,
    modifier: Modifier = Modifier,
    onAction: (HomeScreenAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        GetFactBlock(
            text = text,
            isValidInput = isValidInput,
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
                top = 40.dp,
                bottom = 70.dp
            ),
            onAction = onAction
        )

        Spacer(modifier = Modifier.weight(1f))

        FactsHistory(history = history,
            modifier = Modifier.fillMaxWidth(),
            onItemClick = { onAction(HomeScreenAction.OnHistoryItemClick(it)) },
            onClearHistoryClick = { onAction(HomeScreenAction.OnClearHistoryClick) }
        )
    }
}


@Preview
@Composable
private fun HomeEmptyInputScreenContentPreview(
    @PreviewParameter(HistoryPreviewParameterProvider::class) history: List<FactItem>
) {
    val data = flowOf(PagingData.from(history)).collectAsLazyPagingItems()

    FunNumberFactsTheme {
        HomeScreenContent(modifier = Modifier.background(LightGray),
            text = "",
            isValidInput = true,
            history = data,
            onAction = {})
    }
}