package com.example.funnumberfacts.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funnumberfacts.data.ScreenState
import com.example.funnumberfacts.network.service.NumberFactService
import com.example.funnumberfacts.repository.NumberFactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val numberFactService: NumberFactService,
    private val numberFactRepository: NumberFactRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(HomeViewState())
    val viewState: StateFlow<HomeViewState> = _viewState.asStateFlow()

    init {
        getHistory()
    }

    fun onNumberEntered(text: String) {
        _viewState.update { it.copy(textInput = text) }
    }

    fun onGetNumberFactClick() {
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
                updateScreenState(ScreenState.Loading)
                numberFactService.getFactAboutNumber(enteredNumber)
            }.onSuccess { fact ->
                _viewState.update {
                    it.history.add(fact)
                    it.copy(screenState = ScreenState.Idle)
                }
                numberFactRepository.addFactToHistory(fact)
            }.onFailure { error ->
                updateScreenState(ScreenState.Error)
            }
        }
    }

    fun onGetRandomNumberFactClick() {
        viewModelScope.launch {
            runCatching {
                updateScreenState(ScreenState.Loading)
                numberFactService.getRandomFact()
            }.onSuccess { fact ->
                _viewState.update {
                    it.history.add(fact)
                    it.copy(screenState = ScreenState.Idle)
                }
                numberFactRepository.addFactToHistory(fact)
            }.onFailure { error ->
                updateScreenState(ScreenState.Error)
            }
        }
    }

    private fun getHistory() {
//        todo: cache data in splash screen
        viewModelScope.launch {
            runCatching {
                updateScreenState(ScreenState.Loading)
                numberFactRepository.getHistory()
            }.onSuccess { history ->
                _viewState.update {
                    it.copy(
                        screenState = ScreenState.Idle,
                        history = history.toMutableList()
                    )
                }
            }.onFailure {
                updateScreenState(ScreenState.Error)
            }
        }
    }

    private fun updateScreenState(state: ScreenState) {
        _viewState.update { it.copy(screenState = state) }
    }
}