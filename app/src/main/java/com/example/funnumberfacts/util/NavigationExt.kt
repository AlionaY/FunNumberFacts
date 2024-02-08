package com.example.funnumberfacts.util

import androidx.navigation.NavController
import com.example.funnumberfacts.ui.navigation.Routes

fun NavController.navigate(
    destination: Routes,
    argument: Int? = null
) {
    runCatching {
        val arg = argument?.let { "/$argument" }.orEmpty()
        val route = "${destination.route}$arg"
        navigate(route)
    }.onFailure {
        it.printStackTrace()
    }
}