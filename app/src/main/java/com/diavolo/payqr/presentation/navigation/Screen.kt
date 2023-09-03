package com.diavolo.payqr.presentation.navigation

/**
 * Written with passion by Ikhsan Hidayat on 01/09/2023.
 */
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home/{nominalTransaction}"){
        fun createRoute(nominalTransaction: String) = "home/$nominalTransaction"

    }
    object QrScanner : Screen("qrScanner/{balance}") {
        fun createRoute(balance: String) = "qrScanner/$balance"
    }

    object Payment : Screen("qrScanner/{balance}/{code}") {
        fun createRoute(balance: String, code: String) = "qrScanner/$balance/$code"
    }

    object History : Screen("history")
}