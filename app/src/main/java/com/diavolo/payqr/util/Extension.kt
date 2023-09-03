package com.diavolo.payqr.util

import android.content.Context
import android.widget.Toast
import java.text.NumberFormat
import java.util.Locale

/**
 * Written with passion by Ikhsan Hidayat on 03/09/2023.
 */

fun String.toRupiah(): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    val amount = this.toDoubleOrNull() ?: 0.0
    return numberFormat.format(amount).toString()
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}