package com.diavolo.payqr.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diavolo.payqr.data.local.transaction.TransactionHistory
import com.diavolo.payqr.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Written with passion by Ikhsan Hidayat on 03/09/2023.
 */
@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: TransactionRepository):ViewModel() {

    private val _historyList = MutableStateFlow<List<TransactionHistory>>(
        value = emptyList()
    )

    val historyList: StateFlow<List<TransactionHistory>> get() = _historyList

    fun getTransactionHistory() = viewModelScope.launch(Dispatchers.IO) {
        _historyList.value = repository.getTransactionHistory()
    }

}