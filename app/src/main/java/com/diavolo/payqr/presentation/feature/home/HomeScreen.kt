package com.diavolo.payqr.presentation.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diavolo.payqr.util.Util.BALANCE
import com.diavolo.payqr.util.toRupiah

/**
 * Written with passion by Ikhsan Hidayat on 01/09/2023.
 */
@Composable
fun HomeScreen(
    nominalTransaction: String,
    navigateToQrScanner: (String) -> Unit
) {
    val remainingBalance = "${BALANCE.toInt() - nominalTransaction.toInt()}"
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Row {
                Text("Saldo Anda = ")
                Text(remainingBalance.toRupiah())
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navigateToQrScanner(remainingBalance)
                },
                modifier = Modifier
                    .wrapContentWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Payment with QRIS")
            }
        }
    }
}