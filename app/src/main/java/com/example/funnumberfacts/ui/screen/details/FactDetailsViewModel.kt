package com.example.funnumberfacts.ui.screen.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funnumberfacts.data.ScreenState
import com.example.funnumberfacts.repository.NumberFactRepository
import com.example.funnumberfacts.ui.navigation.FACT_ID
import com.example.funnumberfacts.util.orInvalidId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val factRepository: NumberFactRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(FactDetailsScreenState())
    val viewState: StateFlow<FactDetailsScreenState> = _viewState.asStateFlow()

    private val factId = savedStateHandle.get<Int>(FACT_ID).orInvalidId()

    init {
        getItemDetails()
    }

    private fun getItemDetails() {
        viewModelScope.launch {
            runCatching {
                _viewState.update { it.copy(screenState = ScreenState.Loading) }
                factRepository.getFactById(factId)
            }.onSuccess { item ->
                _viewState.update { it.copy(fact = item, screenState = ScreenState.Idle) }
            }.onFailure {
                _viewState.update { state -> state.copy(screenState = ScreenState.Error(it)) }
            }
        }
    }

    fun onAcceptErrorDialog() {
        _viewState.update { it.copy(screenState = ScreenState.Idle) }
    }
}