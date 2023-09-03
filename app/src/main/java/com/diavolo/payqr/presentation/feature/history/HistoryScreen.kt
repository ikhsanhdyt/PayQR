package com.diavolo.payqr.presentation.feature.history

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.diavolo.payqr.presentation.feature.base.EmptyView
import com.diavolo.payqr.presentation.viewModel.HistoryViewModel

/**
 * Written with passion by Ikhsan Hidayat on 03/09/2023.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryScreen(
    modifier: Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.getTransactionHistory()
    }

    val transactionHistory by viewModel.historyList.collectAsState(
        initial = emptyList()
    )

    if (transactionHistory.isEmpty()) {
        EmptyView()
    } else {
        Box(modifier = modifier) {
            val listState = rememberLazyListState()
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {

                items(transactionHistory) { history ->
                    HistoryListItem(
                        transactionHistory = history,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(tween(durationMillis = 100)),
                    )
                }
            }
        }
    }
}