package com.example.funnumberfacts.ui.screen.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.funnumberfacts.R
import com.example.funnumberfacts.db.FactItem
import com.example.funnumberfacts.ui.screen.home.bigHistory
import com.example.funnumberfacts.ui.screen.home.emptyHistory
import com.example.funnumberfacts.ui.screen.home.smallHistory
import com.example.funnumberfacts.ui.theme.Bouquet
import com.example.funnumberfacts.ui.theme.FunNumberFactsTheme
import com.example.funnumberfacts.ui.theme.LightGray
import com.example.funnumberfacts.ui.theme.Thunder
import com.example.funnumberfacts.util.orInvalidId

private const val HISTORY_ANIMATION_DURATION = 400

@Composable
fun FactsHistory(
    history: LazyPagingItems<FactItem>,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit,
    onClearHistoryClick: () -> Unit
) {
    val shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    var isHistoryVisible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        isHistoryVisible = true
    }

    AnimatedVisibility(
        visible = isHistoryVisible,
        enter = slideInVertically(
            animationSpec = tween(HISTORY_ANIMATION_DURATION),
            initialOffsetY = { it / 2 }),
        exit = slideOutVertically(
            animationSpec = tween(HISTORY_ANIMATION_DURATION),
            targetOffsetY = { it / 2 })
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, Bouquet),
                    shape = shape
                )
                .shadow(elevation = 2.dp, shape = shape)
                .background(color = MaterialTheme.colorScheme.onPrimaryContainer, shape = shape),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.history),
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 30.sp,
                        color = Thunder
                    )
                    TextButton(
                        onClick = onClearHistoryClick,
                        modifier = Modifier.align(Alignment.CenterEnd),
                        contentPadding = PaddingValues(horizontal = 6.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.clear_history),
                            style = MaterialTheme.typography.labelMedium,
                            color = Thunder,
                            fontSize = 12.sp
                        )
                    }
                }
            }
            items(history.itemSnapshotList) { item ->
                NumberFactItem(
                    item = item,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onItemClick(item?.id.orInvalidId()) }
                )
            }
        }
    }
}

@Preview
@Composable
fun EmptyFactsHistoryPreview() {
    val history = emptyHistory.collectAsLazyPagingItems()
    FunNumberFactsTheme {
        FactsHistory(
            history = history,
            modifier = Modifier.background(LightGray),
            onItemClick = {}) {}
    }
}

@Preview
@Composable
fun SmallFactsHistoryPreview() {
    val history = smallHistory.collectAsLazyPagingItems()
    FunNumberFactsTheme {
        FactsHistory(
            history = history,
            modifier = Modifier.background(LightGray),
            onItemClick = {}) {}
    }
}

@Preview
@Composable
fun BigFactsHistoryPreview() {
    val history = bigHistory.collectAsLazyPagingItems()
    FunNumberFactsTheme {
        FactsHistory(
            history = history,
            modifier = Modifier.background(LightGray),
            onItemClick = {}) {}
    }
}