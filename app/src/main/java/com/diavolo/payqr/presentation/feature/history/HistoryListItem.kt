package com.diavolo.payqr.presentation.feature.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diavolo.payqr.data.local.transaction.TransactionHistory
import com.diavolo.payqr.presentation.ui.theme.PayQRTheme

/**
 * Written with passion by Ikhsan Hidayat on 03/09/2023.
 */
@Composable
fun HistoryListItem(
    transactionHistory: TransactionHistory,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier

            .padding(16.dp)
    ) {
        Text(
            text = transactionHistory.merchantName,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        )
        Text(
            text = transactionHistory.nominal,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryListItemPreview() {
    PayQRTheme {
        HistoryListItem(
            transactionHistory = TransactionHistory(
                nominal = "10000",
                merchantName = "BNI STORE"
            )
        )
    }
}