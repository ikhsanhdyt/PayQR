package com.diavolo.payqr.presentation.feature.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.diavolo.payqr.presentation.feature.base.BottomNavigationBar
import com.diavolo.payqr.presentation.feature.history.HistoryScreen
import com.diavolo.payqr.presentation.feature.home.HomeScreen
import com.diavolo.payqr.presentation.feature.payment.PaymentScreen
import com.diavolo.payqr.presentation.feature.qrScanner.QrCodeScanner
import com.diavolo.payqr.presentation.feature.splash.SplashScreen
import com.diavolo.payqr.presentation.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(bottomBar = {
        if (currentRoute != Screen.Splash.route && currentRoute != Screen.QrScanner.route && currentRoute != Screen.Payment.route) {
            BottomNavigationBar(navController)
        }
    }, topBar = {
        if (currentRoute != Screen.Splash.route && currentRoute != Screen.QrScanner.route) {
            TopAppBar(
                title = {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = when (currentRoute) {
                                Screen.Payment.route -> "Payment"
                                Screen.History.route -> "Transaction History"
                                else -> {
                                    "Home"
                                }
                            }

                        )
                    }
                }, colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.secondary
                )

            )
        }

    }, modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(navController = navController)
            }
            composable(
                route = Screen.Home.route,
                arguments = listOf(
                    navArgument("nominalTransaction") { type = NavType.StringType },
                ),
            ) {

                val nominalTransaction: String = it.arguments?.getString("nominalTransaction") ?: ""

                HomeScreen(nominalTransaction = nominalTransaction, navigateToQrScanner = {
                    navController.navigate(Screen.QrScanner.createRoute(it))
                })
            }
            composable(
                route = Screen.QrScanner.route,
                arguments = listOf(
                    navArgument("balance") { type = NavType.StringType },
                ),
            ) {
                val balance: String = it.arguments?.getString("balance") ?: ""

                QrCodeScanner(balance = balance, navigateToPayment = { balances, code ->
                    navController.navigate(Screen.Payment.createRoute(balances, code))
                })
            }
            composable(
                route = Screen.Payment.route,
                arguments = listOf(
                    navArgument("balance") { type = NavType.StringType },
                    navArgument("code") { type = NavType.StringType },
                ),
            ) {
                val balance: String = it.arguments?.getString("balance") ?: ""
                val code: String = it.arguments?.getString("code") ?: ""

                PaymentScreen(balance = balance,
                    code = code,
                    navigateBackToHome = { nominalTransaction ->
                        navController.navigate(Screen.Home.createRoute(nominalTransaction)) {
                            popUpTo(Screen.Payment.route) {
                                inclusive = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    })
            }
            composable(Screen.History.route) {
                HistoryScreen(
                    modifier = modifier
                )
            }
        }
    }
}
