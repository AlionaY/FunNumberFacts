package com.example.funnumberfacts.ui.navigation

sealed class Routes(val route: String) {
    object HomeScreen: Routes("home")

    object FactDetailsScreen: Routes("fact_details")
}