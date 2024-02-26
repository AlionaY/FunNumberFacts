package com.example.funnumberfacts.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.funnumberfacts.ui.navigation.Navigation
import com.example.funnumberfacts.ui.theme.FunNumberFactsTheme
import dagger.hilt.android.AndroidEntryPoint

private const val SCRIM_COLOR = "#801b1b1b"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(scrim = Color.parseColor(SCRIM_COLOR)),
            navigationBarStyle = SystemBarStyle.dark(scrim = Color.parseColor(SCRIM_COLOR))
        )
        super.onCreate(savedInstanceState)

        setContent {
            FunNumberFactsTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}