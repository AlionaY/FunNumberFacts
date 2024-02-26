package com.example.funnumberfacts.ui.screen.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.funnumberfacts.R
import com.example.funnumberfacts.db.FactItem
import com.example.funnumberfacts.ui.theme.FunNumberFactsTheme
import com.example.funnumberfacts.util.orInvalidId
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

private const val HISTORY_ANIMATION_DURATION = 400

@Composable
fun FactsHistory(
    history: LazyPagingItems<FactItem>,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit,
    onClearHistoryClick: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    var isHistoryVisible by remember { mutableStateOf(false) }
    val showToTopButton by remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 0 } }

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
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                    shape = shape
                )
                .shadow(elevation = 2.dp, shape = shape)
                .background(color = MaterialTheme.colorScheme.onPrimaryContainer, shape = shape),
            state = lazyListState,
            contentPadding = PaddingValues(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.history),
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 10.dp)
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                    TextButton(
                        onClick = onClearHistoryClick,
                        modifier = Modifier.align(Alignment.CenterEnd),
                        contentPadding = PaddingValues(horizontal = 6.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.clear_history),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.outline,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            when {
                history.itemSnapshotList.isNotEmpty() -> {
                    items(history.itemSnapshotList) { item ->
                        NumberFactItem(
                            item = item,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { onItemClick(item?.id.orInvalidId()) }
                        )
                    }
                }

                else -> {
                    item {
                        NoHistory(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 20.dp)
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }
        }

        GoToTopFloatingActionButton(
            showButton = showToTopButton,
        ) {
            scope.launch {
                lazyListState.animateScrollToItem(0)
            }
        }
    }
}

@Composable
private fun GoToTopFloatingActionButton(
    showButton: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = showButton,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            FloatingActionButton(
                onClick = onClick,
                modifier = Modifier
                    .padding(16.dp)
                    .size(50.dp)
                    .align(Alignment.BottomEnd),
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun NoHistory(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.there_are_no_facts),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun HistoryPreview(
    @PreviewParameter(HistoryPreviewParameterProvider::class) history: List<FactItem>
) {
    val data = flowOf(PagingData.from(history)).collectAsLazyPagingItems()
    FunNumberFactsTheme {
        FactsHistory(history = data, onItemClick = {}) {}
    }
}