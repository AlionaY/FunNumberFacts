package com.example.funnumberfacts.util

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.funnumberfacts.data.ScreenState

@Composable
fun HandleProgressBar(screenState: ScreenState) {
    var showProgressBar by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = screenState) {
        showProgressBar = screenState == ScreenState.Loading
    }

    if (showProgressBar) CircularProgressIndicator()
}