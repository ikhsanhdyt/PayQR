package com.diavolo.payqr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.diavolo.payqr.data.local.transaction.TransactionHistory
import com.diavolo.payqr.data.local.transaction.TransactionHistoryDao

/**
 * Written with passion by Ikhsan Hidayat on 03/09/2023.
 */
@Database(entities = [TransactionHistory::class], version = 1, exportSchema = false)
abstract class PayQrDB : RoomDatabase() {

    abstract fun transactionHistoryDao(): TransactionHistoryDao
}