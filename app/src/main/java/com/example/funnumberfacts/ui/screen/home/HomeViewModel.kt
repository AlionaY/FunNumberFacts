package com.example.funnumberfacts.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.funnumberfacts.network.numberservice.NumberService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val numberService: NumberService
) : ViewModel() {

    private val _viewState = MutableStateFlow(HomeViewState())
    val viewState: StateFlow<HomeViewState> = _viewState.asStateFlow()

    fun onNumberEntered(text: String) {
        _viewState.update { it.copy(textInput = text) }
    }

    fun onGetNumberFactClick() {
//        todo: make request, handle error, save to history
    }

    fun onGetRandomFactClick() {
//        todo: make request, handle error, save to history
    }
}