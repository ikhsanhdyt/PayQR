package com.diavolo.payqr.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diavolo.payqr.data.local.transaction.TransactionHistory
import com.diavolo.payqr.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Written with passion by Ikhsan Hidayat on 03/09/2023.
 */
@HiltViewModel
class PaymentViewModel @Inject constructor(private val repository: TransactionRepository) :
    ViewModel() {

    fun addTransactionHistory(transactionHistory: TransactionHistory) = viewModelScope.launch(Dispatchers.IO) {
        repository.addTransactionHistory(transactionHistory)
    }
}