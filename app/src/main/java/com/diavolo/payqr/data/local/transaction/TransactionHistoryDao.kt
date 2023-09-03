package com.diavolo.payqr.data.local.transaction

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.diavolo.payqr.util.Util.TRANSACTION_HISTORY_TABLE_NAME

/**
 * Written with passion by Ikhsan Hidayat on 03/09/2023.
 */
@Dao
interface TransactionHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTransactionHistory(transactionHistory: TransactionHistory)


    @Query("SELECT * FROM $TRANSACTION_HISTORY_TABLE_NAME ORDER BY createdDate ASC")
    fun getTransactionHistory(): List<TransactionHistory>
}