package com.example.funnumberfacts.ui.screen.details

import com.example.funnumberfacts.data.NumberFact
import com.example.funnumberfacts.data.ScreenState

data class FactDetailsScreenState(
    val screenState: ScreenState = ScreenState.Idle,
    val fact: NumberFact? = null
)