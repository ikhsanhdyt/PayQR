package com.diavolo.payqr.data.local.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diavolo.payqr.util.Util.TRANSACTION_HISTORY_TABLE_NAME
import com.diavolo.payqr.util.toRupiah

/**
 * Written with passion by Ikhsan Hidayat on 03/09/2023.
 */
@Entity(tableName = TRANSACTION_HISTORY_TABLE_NAME)
data class TransactionHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val merchantName: String,
    val nominal: String = "".toRupiah(),
    val createdDate: String = "",
)