package com.example.funnumberfacts.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.funnumberfacts.ui.screen.details.FactDetailsScreen
import com.example.funnumberfacts.ui.screen.home.HomeScreen

const val FACT_ID = "factId"
private val factIdNavArg = navArgument(FACT_ID) { type = NavType.IntType }

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
        composable(Routes.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(
            route = Routes.FactDetailsScreen.toRoute(),
            arguments = listOf(factIdNavArg)
        ) {
            FactDetailsScreen(navController)
        }
    }
}

fun Routes.FactDetailsScreen.toRoute() = "$route/{$FACT_ID}"