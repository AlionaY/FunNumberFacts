package com.example.funnumberfacts.data

import java.lang.Exception

sealed class ScreenState {
    object Idle : ScreenState()
    object Loading : ScreenState()
    class Error(val error: Throwable) : ScreenState()
}
