package com.diavolo.payqr.presentation.feature.payment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.diavolo.payqr.data.local.transaction.TransactionHistory
import com.diavolo.payqr.presentation.viewModel.PaymentViewModel
import com.diavolo.payqr.util.showToast
import com.diavolo.payqr.util.toRupiah

/**
 * Written with passion by Ikhsan Hidayat on 02/09/2023.
 */
@Composable
fun PaymentScreen(
    balance: String,
    code: String,
    navigateBackToHome: (String) -> Unit,
    viewModel:PaymentViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val splitCode = code.split(".")
    val merchantName = splitCode[2]
    val nominalTransaction = splitCode[3]

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text("Your Balance = ${balance.toRupiah()}")
            Spacer(modifier = Modifier.height(28.dp))
            Text("Bank = ${splitCode[0]}")
            Text("Transaction ID = ${splitCode[1]}")
            Text("Merchant = $merchantName")
            Text("Nominal Transaction = ${nominalTransaction.toRupiah()}")
            Spacer(modifier = Modifier.height(64.dp))
            Button(
                onClick = {
                    if (balance.toInt() <= nominalTransaction.toInt()) {
                        showToast(context, "Insufficient Balance")

                    } else {

                        viewModel.addTransactionHistory(TransactionHistory(
                            merchantName = merchantName,
                            nominal = nominalTransaction,
                        ))

                        showToast(context, "Payment Success")
                        navigateBackToHome(nominalTransaction)
                    }
                },
                modifier = Modifier
                    .wrapContentWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Pay")
            }
        }
    }
}