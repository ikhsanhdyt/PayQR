package com.diavolo.payqr.data.di

import android.content.Context
import androidx.room.Room
import com.diavolo.payqr.data.local.PayQrDB
import com.diavolo.payqr.data.local.transaction.TransactionHistoryDao
import com.diavolo.payqr.data.repository.TransactionRepository
import com.diavolo.payqr.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Written with passion by Ikhsan Hidayat on 03/09/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Provides
    fun providePayQrDB(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        PayQrDB::class.java,
        Util.TRANSACTION_HISTORY_TABLE_NAME
    ).build()

    @Provides
    fun provideTransactionHistoryDao(
        payQrDB: PayQrDB
    ) = payQrDB.transactionHistoryDao()

    @Provides
    fun provideTransactionRepository(
        transactionHistoryDao: TransactionHistoryDao
    ): TransactionRepository = TransactionRepository(
        transactionHistoryDao = transactionHistoryDao
    )

}