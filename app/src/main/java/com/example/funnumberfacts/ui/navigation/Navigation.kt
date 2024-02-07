package com.example.funnumberfacts.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.funnumberfacts.ui.screen.histoty.HistoryScreen
import com.example.funnumberfacts.ui.screen.home.HomeScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
        composable(Routes.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(Routes.HistoryScreen.route) {
            HistoryScreen(navController)
        }
    }
}