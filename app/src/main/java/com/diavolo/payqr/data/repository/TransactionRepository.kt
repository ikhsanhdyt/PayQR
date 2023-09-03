package com.diavolo.payqr.data.repository

import com.diavolo.payqr.data.local.transaction.TransactionHistory
import com.diavolo.payqr.data.local.transaction.TransactionHistoryDao
import javax.inject.Inject

/**
 * Written with passion by Ikhsan Hidayat on 03/09/2023.
 */
class TransactionRepository @Inject constructor(val transactionHistoryDao: TransactionHistoryDao) {

    fun addTransactionHistory(transactionHistory: TransactionHistory) =
        transactionHistoryDao.addTransactionHistory(
            transactionHistory
        )

    fun getTransactionHistory(): List<TransactionHistory> {
        return transactionHistoryDao.getTransactionHistory().map {
            it
        }
    }
}