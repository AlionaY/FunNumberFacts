package com.example.funnumberfacts.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funnumberfacts.network.numberservice.NumberService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
        //        todo: check if only numbers or show error (is valid = false)
//        todo: make request, handle error, save to history
        val enteredNumber = _viewState.value.textInput.toIntOrNull()
        _viewState.update {
            it.copy(
                number = enteredNumber ?: 0,
                isValidInput = enteredNumber != null
            )
        }

        if (enteredNumber != null) {
            getFactAboutNumber(enteredNumber)
        }
    }

    private fun getFactAboutNumber(enteredNumber: Int) {
        viewModelScope.launch {
            runCatching {
                numberService.getFactAboutNumber(enteredNumber)
            }.onSuccess { fact ->
                _viewState.update { it.copy(fact = fact) }
                Log.d("$$$", "fact $fact")
                //                    todo: save to room
            }.onFailure { error ->
                Log.d("$$$", "error $error")
                //                    todo: handle error
            }
        }
    }

    fun onGetRandomNumberFactClick() {
        viewModelScope.launch {
            runCatching {
                numberService.getRandomFact()
            }.onSuccess { fact ->
                _viewState.update { it.copy(fact = fact) }
                Log.d("$$$", "fact $fact")
//                    todo: save to room
            }.onFailure { error ->
                Log.d("$$$", "error $error")
//                    todo: handle error
            }
        }
    }
}